(ns alphabet-cipher.coder-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.coder :refer :all]))

(deftest char-to-index-test
  (is (= (char-to-index \a) 0))
  (is (= (char-to-index \j) 9))
)

(deftest shift-character-test
  (is (= (shift-character \a 0) \a))
  (is (= (shift-character \a 10) \k))
  (is (= (shift-character \a 30) \e))
)

(deftest nth-mod-count-test
  (is (= (nth-mod-count "string" 2) \r))
  (is (= (nth-mod-count "string" 10) \n))
)

(deftest encode-test
  (is (=
    "hmkbxebpxpmyllyrxiiqtoltfgzzv"
    (encode "vigilance" "meetmeontuesdayeveningatseven")))
  (is (=
    "egsgqwtahuiljgs"
    (encode "scones" "meetmebythetree")))
)

(deftest decode-test
  (is (=
    "meetmeontuesdayeveningatseven"
    (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
  (is (=
    "meetmebythetree"
    (decode "scones" "egsgqwtahuiljgs")))
)

(deftest decipher-test
  (is (=
    "vigilance"
    (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")))
  (is (=
    "scones"
    (decipher "hcqxqqtqljmlzhwiivgbsapaiwcenmyu" "packmyboxwithfivedozenliquorjugs")))
  (is (=
    "abcabcx"
    (decipher "hfnlphoontutufa" "hellofromrussia")))
)

; ---------------------------------------------------------------
; tests provided by gigasquid

(deftest test-encode
  (testing "can encode a message with a secret keyword"
    (is (= "hmkbxebpxpmyllyrxiiqtoltfgzzv"
           (encode "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "egsgqwtahuiljgs"
           (encode "scones" "meetmebythetree")))))

(deftest test-decode
  (testing "can decode a message given an encoded message and a secret keyword"
    (is (= "meetmeontuesdayeveningatseven"
           (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
    (is (= "meetmebythetree"
           (decode "scones" "egsgqwtahuiljgs")))))

(deftest test-decipher
  (testing "can extract the secret keyword given an encrypted message and the original message"
    (is (= "vigilance"
           (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")))
    (is (= "scones"
           (decipher "hcqxqqtqljmlzhwiivgbsapaiwcenmyu" "packmyboxwithfivedozenliquorjugs")))
    (is (= "abcabcx"
           (decipher "hfnlphoontutufa" "hellofromrussia")))))
