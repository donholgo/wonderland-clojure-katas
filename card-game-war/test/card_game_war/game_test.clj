(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(deftest first-card-better?-test
  (is (false? (first-card-better? [:spade 3] [:spade 8])))
  (is (true? (first-card-better? [:heart :queen] [:heart 10])))
  (is (false? (first-card-better? [:club :jack] [:diamond :jack])))
)

(deftest play-round-test
  (is (= (play-round [:spade 10] [:heart 5]) :player1))
  (is (= (play-round [:diamond 3] [:club 8]) :player2))
)

(deftest play-game-test
  (is (= :player1-wins (play-game [[:heart :queen]] [[:club 8]])))
  (is (= :player2-wins (play-game [[:club 8]] [[:heart :queen]])))
  (is (= :player1-wins (play-game [[:heart :king] [:spade :ace]] [[:club 6] [:club 10]])))
  (is (= :player2-wins (play-game [[:club 6] [:club 10]] [[:heart :king] [:spade :ace]])))
  (is (= :player1-wins (play-game [[:heart 8] [:diamond :king] [:spade :jack]] [[:diamond 2] [:spade 4] [:club 5]])))
  (is (= :player2-wins (play-game [[:diamond 2] [:spade 4] [:club 5]] [[:heart 8] [:diamond :king] [:spade :jack]])))
)



;; fill in  tests for your game
;(deftest test-play-round
;  (testing "the highest rank wins the cards in the round"
;    (is (= 0 1)))
;)

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

