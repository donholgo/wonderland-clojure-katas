(ns alphabet-cipher.coder)

; ------------------------------------------------------
; index of a character in the alphabet:
; a -> 0, b -> 1 etc.
(defn char-to-index [c]
  (- (int c) (int \a))
)

; ------------------------------------------------------
; shift character c by i positions in the alphabet
; (modulo 26)
(defn shift-character [c i]
  (char (+ (mod (+ (char-to-index c) i (char-to-index \a)) 26) (int \a)))
)

; ------------------------------------------------------
; get n-th element, modulo collection length
(defn nth-mod-count [coll index]
  (nth coll (mod index (count coll)))
)

; ------------------------------------------------------
; encode cleartext message using the given keyword
(defn encode [keyword cleartext]
  (apply str (for [i (range 0 (count cleartext))]
    (shift-character
      (nth cleartext i)
      (char-to-index (nth-mod-count keyword i))
    )
)))

; ------------------------------------------------------
; decode cipher using the given keyword
(defn decode [keyword cipher]
  (apply str (for [i (range 0 (count cipher))]
    (shift-character
      (nth cipher i)
      (- (char-to-index (nth-mod-count keyword i)))
    )
)))

; ------------------------------------------------------
; get the keyword from cipher and cleartext
(defn decipher [cipher cleartext]
  (let [candidate (decode cleartext cipher)]
    (loop [k 1]
      (if (= cipher (encode (subs candidate 0 k) cleartext))
        (subs candidate 0 k)
        (recur (inc k))
      )
    )
))
