(ns passcount.core)


;; My range is the numbers 248345-746315
(def input (range 248345 746316))

(defn digits
  "to split it into a vector of digits"
  [x]
  (if (< x 10)
    [x]
    (conj (digits (quot x 10))
          (rem x 10))))

(defn two-adjacent-digits?
   [password]
   (let [digits (digits password)]
     (cond
       (= (first digits) (nth digits 1)) true
       (= (nth digits 1) (nth digits 2)) true
       (= (nth digits 2) (nth digits 3)) true
       (= (nth digits 3) (nth digits 4)) true
       (= (nth digits 4) (nth digits 5)) true
       :else false
       )))

(defn only-increase?
  [password]
  (let [digits (digits password)]
    (if (= 10 (reduce #(if (<= %1 %2) %2 10) digits)) false true)))


(defn solve
  [input]
    (count (filter two-adjacent-digits? (filter only-increase? input))))