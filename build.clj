(require 'cljs.build.api)

(cljs.build.api/build "src/cljs" {:output-to "resources/js/json-csv-converter.js"})
