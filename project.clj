(defproject json-csv-converter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-beta3"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.csv "0.1.2"]
                 [cheshire "5.4.0"]
                 [org.clojure/clojurescript "0.0-3269"]]
  :plugins [[lein-cljsbuild "1.0.6"]]
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"],
                :compiler
                {:pretty-print true,
                 :output-dir "resources/js",
                 :output-to "resources/js/json-csv-converter.js",
                 :optimizations :simple},
                :jar true}]}
  :source-paths ["src"]
  :main json-csv-converter.core)
