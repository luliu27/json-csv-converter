;; java -cp ~/sources/cljs.jar:src/cljs clojure.main build.clj
(require 'cljs.build.api)

(cljs.build.api/watch "src/cljs"
                      {:output-to "out/json-csv-converter.js"
                       :main 'json-csv-converter.core})
