(ns hcf.core)

(def input
  [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,19,6,23,2,6,23,27,2,27,9,31,1,5,31,35,1,35,10,39,2,39,9,43,1,5,43,47,2,47,10,51,1,51,6,55,1,5,55,59,2,6,59,63,2,63,6,67,1,5,67,71,1,71,9,75,2,75,10,79,1,79,5,83,1,10,83,87,1,5,87,91,2,13,91,95,1,95,10,99,2,99,13,103,1,103,5,107,1,107,13,111,2,111,9,115,1,6,115,119,2,119,6,123,1,123,6,127,1,127,9,131,1,6,131,135,1,135,2,139,1,139,10,0,99,2,0,14,0]
)


(def opcodes
  {:1 '+
   :2 '*
   :99 "EXIT"})


(defn eval-opcode
  [opcode]
  (eval ((keyword (str opcode)) opcodes)))


(defn run-this-program
  ([program]
   (run-this-program program 0))
  ([program location]
   (let [opcode (eval-opcode (nth program location))]
      (if (= "EXIT" opcode)
         (first program)
         (let [noun-location (nth program (+ 1 location))
               verb-location (nth program (+ 2 location))
               destination-location (nth program (+ 3 location))
               noun (nth program noun-location)
               verb (nth program verb-location)
               result (opcode noun verb)
               new-prog (assoc program destination-location result)]
           ; (println new-prog)
           (run-this-program new-prog (+ 4 location)))))))



(defn replace-noun-and-verb
  [program noun verb]
  (assoc (assoc program 1 noun) 2 verb))

(defn find-combo
  [program target]
  (doseq [noun (range 100)]
    (doseq [verb (range 100)]
      (let [this-program (replace-noun-and-verb program noun verb)]
        (if (= (run-this-program this-program) target)
          (println "Noun: " noun " Verb: " verb))))))


