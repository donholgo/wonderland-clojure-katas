(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))


; ================================================================
; are word1 and word2 of equal length
; and different by exactly one letter?
(defn one-letter-diff?
  [word1 word2]
  (and
    (= (count word1) (count word2))
    (= (count (filter true? (map #(not= %1 %2) word1 word2))) 1)
  )
)

; ================================================================
; does vector _vect_ contain element _element_?
(defn vector-contains? [vect element]
  (boolean (some #(= element %) vect))
)

; ================================================================
; for a vector _doublet_, find all words _w_ in the
; dictionary that
;  a) differ from the last word in _doublet_ by one letter
;  b) are not contained in _doublet_
; and return a list that for each such word _w_
; contains the conjunction of _doublet_ and _w_ 
; (see examples in tests)
(defn add-possible-continuations [doublet]
  (->> words
    (filter
      #(and
        (one-letter-diff? (last doublet) %)
        (not (vector-contains? doublet %))
      )
    )
    (map #(conj doublet %))
  )
)

; ================================================================
; starting at _word1_, build tree of possible doublets until one
; has _word2_ at the end
(defn search [word1 word2]
  (let [continue? #(not= (last %) word2)]
    (tree-seq continue? add-possible-continuations [word1])
  )
)

; ================================================================
; from all the doublets starting at _word1_ that "search" creates,
; get the first one that has _word2_ at the end
(defn doublets [word1 word2]
  (->> (search word1 word2)
    (filter #(= (last %) word2))
    (first)
    (vec)
  )
)
