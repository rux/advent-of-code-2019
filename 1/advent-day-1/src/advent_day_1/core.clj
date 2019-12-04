(ns advent-day-1.core

 (:require [clojure.java.io :as io]
  [clojure.edn :as edn])
)

(def input (edn/read (java.io.PushbackReader. (io/reader "./resources/input.edn"))))


(defn fuel-required
  [mass]
  (let [fuel (- (Math/floor (/ mass 3)) 2)]
    (println "Mass " mass " needs fuel " fuel)
    (if (> fuel 0)
      (do
        (println "Need more")
        (+ fuel (fuel-required fuel)))
      0)))



(defn total-fuel
  [masses]
  (reduce + (map fuel-required masses)))


(def result (total-fuel input))