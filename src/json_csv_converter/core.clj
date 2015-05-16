(ns json-csv-converter.core
  (:use [clojure.java.io :only [file reader]])
  (:require [cheshire.core :as json]
            [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]])
  (:import (java.io FileInputStream)))

(def cli-options
  [
   ["-i" "--input FILE" "Input file"]
   ["-c" "--col-spec COLUMN_SPEC" "Column spec"]
   ["-o" "--output FILE" "Output file"]
   ["-h" "--help"]])

(defn usage
  [options-summary & reminders]
  (->> ["Usage: program-name [options]"
        ""
        "Options:"
        options-summary
        ""]
       (concat reminders)
       (string/join \newline)))

(defn error-msg
  [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit
  [status msg]
  (println msg)
  (System/exit status))

(defn split-file [f] (string/split (slurp f) #"\n"))

(defn get-columns
  [column-file]
  (->> column-file
       (split-file)
       (map #(string/split % #"\."))))

(defn -main
  [& args]
  (let [{:keys [options arguments summary errors]} (parse-opts args cli-options)]
    (cond
     (:help options) (exit 0 (usage summary))
     (nil? (:input options)) (exit 1 (usage summary "Missing Input file"))
     (nil? (:col-spec options)) (exit 1 (usage summary "Missing Column spec"))
     errors (exit 1 (error-msg errors))) 
    (with-open [s (FileInputStream. (file (:input options)))]
      (let [r (reader s)
            cols (get-columns (:col-spec options))]
        (do
          (doall (map prn cols))
          (doall (map prn (json/parsed-seq r))))))))
