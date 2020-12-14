(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


(deftest rank-index-test
  (is (< (rank-index 2) (rank-index 3)))
  (is (< (rank-index 4) (rank-index 7)))
  (is (< (rank-index 9) (rank-index 10)))
  (is (< (rank-index 10) (rank-index :jack)))
  (is (< (rank-index :jack) (rank-index :queen)))
  (is (< (rank-index :queen) (rank-index :king)))
  (is (< (rank-index :king) (rank-index :ace)))
)

(deftest suit-index-test
  (is (< (suit-index :spade) (suit-index :club)))
  (is (< (suit-index :club) (suit-index :diamond)))
  (is (< (suit-index :diamond) (suit-index :heart)))
)

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

