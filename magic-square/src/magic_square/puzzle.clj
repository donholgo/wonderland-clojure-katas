(ns magic-square.puzzle)


; --------------------------------------------------
; ok for squares of any size
(defn row-sums [square]
  (map #(reduce + %) square)
)

; --------------------------------------------------
; only for 3×3 squares!
(defn column-sums [square]
  (list
    (reduce + (map first square))
    (reduce + (map second square))
    (reduce + (map last square))
  )
)

; --------------------------------------------------
; only for 3×3 squares!
(defn diagonal-sums [square]
  (list
    (+
      (first (first square))
      (second (second square))
      (last (last square))
    )
    (+
      (first (last square))
      (second (second square))
      (last (first square))
    )
  )
)

; --------------------------------------------------
; only for 3×3 squares!
(defn magic? [square]
  (= 1
    (count (set (concat
      (row-sums square)
      (column-sums square)
      (diagonal-sums square)
    )))
  )
)

; ==================================================
; functions that work on number vectors

; --------------------------------------------------
; only for 9 numbers (3×3 squares)!
(defn to-square [values]
  (mapv vec (partition 3 values))
)

; --------------------------------------------------
(defn permutations [coll]
  (lazy-seq
    (if (next coll)
      (for [head coll
           tail (permutations (remove #{head} coll))]
        (cons head tail))
      [coll])
  )
)

; a set can be used as a function with one argument
; that returns true if the argument is in the set,
; therefore
;   (remove #{elem} coll)
; is the same as
;   (remove #(= elem %) coll)



; --------------------------------------------------
(defn magic-square [values]
  (->> values
    (permutations)
    (map to-square)
    (filter magic?)
    (first)
  )
)

; ==================================================
; generalized versions of the square functions
; (not restricted to 3×3 squares)


(defn column-sums* [square]
  (for [n (range (count (first square)))]
    (reduce + (map #(nth % n) square))
  )
)


; --------------------------------------------------
(defn diagonal-sums* [square]
  (list
    (reduce +
      (for [n (range (count (first square)))]
        (get-in square [n n])
      )
    )
    (reduce +
      (for [n (range (count (first square)))]
        (get-in square [n (- (count (first square)) n 1)])
      )
    )
  )
)


; --------------------------------------------------
(defn to-square* [values]
  (let [size (int (Math/sqrt (count values)))]
    (mapv vec (partition size values))
  )
)


; --------------------------------------------------
(defn magic?* [square]
  (= 1
    (count (set (concat
      (row-sums square)
      (column-sums* square)
      (diagonal-sums* square)
    )))
  )
)
