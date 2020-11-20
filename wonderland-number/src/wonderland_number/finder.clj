(ns wonderland-number.finder)

; =============================================
; number-to-sorted-vector

(defn number-to-sorted-vector [num]
  (sort (clojure.string/split (str num) #""))
)


; =============================================
; same-digits?

(defn same-digits? [num1 num2]
  (= (number-to-sorted-vector num1) (number-to-sorted-vector num2))
)


; =============================================
; possible-wonderland-number?

(defn possible-wonderland-number? [num]
  (and
    (= (count (str num)) 6)
    (same-digits? num (* num 2))
    (same-digits? num (* num 3))
    (same-digits? num (* num 4))
    (same-digits? num (* num 5))
    (same-digits? num (* num 6))
  )
)


; =============================================
; wonderland-number

(defn wonderland-number []
  (first (filter possible-wonderland-number? (range 100000 1000000)))
)

; → 142857
