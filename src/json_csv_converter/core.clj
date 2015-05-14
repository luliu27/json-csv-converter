(ns json-csv-converter.core
  (:use [clojure.java.io :only [file reader]])
  (:require [cheshire.core :as json])
  (:import (java.io FileInputStream)))

(defn -main
  [& args]
  (with-open [s (FileInputStream. (file (first args)))]
    (let [r (reader s)]
      (doall (map prn (json/parsed-seq r))))))
