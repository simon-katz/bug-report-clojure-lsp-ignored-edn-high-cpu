(ns create-edn-files
  (:require [clojure.pprint :as pp]))

(def output-dir "edn-files")

(defn create-edn-file [name n map-size]
  (.mkdirs (java.io.File. output-dir))
  (spit (str output-dir "/" name "-" n "-" map-size ".edn")
        (with-out-str
          (pp/pprint
           (for [_ (range n)]
             (apply sorted-map
                    (apply concat
                           (for [i (range map-size)]
                             [(-> i (+ 97) char str keyword)
                              (inc i)]))))))))

(comment
  (do
    (create-edn-file "small" 5 4)
    (create-edn-file "medium" 1500 4)
    (create-edn-file "big" 2000 4)
    (create-edn-file "very-big" 10000 26)))
