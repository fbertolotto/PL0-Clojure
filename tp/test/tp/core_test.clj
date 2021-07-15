(ns tp.core-test
  (:require [clojure.test :refer :all]
            [tp.core :refer :all]))
; Formato: [cod mem cont-prg pila-dat pila-llam]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-pop
  (testing "Funcionalidad de POP en interpretar"
    (is (= [[['POP 1] ['TEST]] [1 6 3 4] 1 [5] []] 
           (interpretar [['POP 1] ['TEST]] [1 2 3 4] 0 [5 6] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-pfm
  (testing "Funcionalidad de PFM en interpretar"
    (is (= [[['PFM 3] ['TEST]] [1 2 3 4 5] 1 [4] []]
           (interpretar [['PFM 3] ['TEST]] [1 2 3 4 5] 0 [] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-pfi
  (testing "Funcionalidad de PFI en interpretar"
    (is (= [[['PFI 6] ['TEST]] [1 2 3 4 5] 1 [6] []]
           (interpretar [['PFI 6] ['TEST]] [1 2 3 4 5] 0 [] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-add-1
  (testing "Funcionalidad de ADD en interpretar"
    (is (= [[['ADD] ['TEST]] [1 2 3] 1 [9] []]
           (interpretar [['ADD] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
(deftest test-add-2
  (testing "Funcionalidad de ADD en interpretar"
    (is (= [[['ADD] ['TEST]] [1 2 3] 1 [9] []]
           (interpretar [['ADD] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-sub-1
  (testing "Funcionalidad de SUB en interpretar"
    (is (= [[['SUB] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['SUB] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
(deftest test-sub-2
  (testing "Funcionalidad de SUB en interpretar"
    (is (= [[['SUB] ['TEST]] [1 2 3] 1 [-1] []]
           (interpretar [['SUB] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
) 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-mul-1
  (testing "Funcionalidad de MUL en interpretar"
    (is (= [[['MUL] ['TEST]] [1 2 3] 1 [20] []]
           (interpretar [['MUL] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
(deftest test-mul-2
  (testing "Funcionalidad de MUL en interpretar"
    (is (= [[['MUL] ['TEST]] [1 2 3] 1 [20] []]
           (interpretar [['MUL] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
) 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-div-1
  (testing "Funcionalidad de DIV en interpretar"
    (is (= [[['DIV] ['TEST]] [1 2 3] 1 [4] []]
           (interpretar [['DIV] ['TEST]] [1 2 3] 0 [20 5] [])
        )
    )
  )
)
(deftest test-div-2
  (testing "Funcionalidad de DIV en interpretar"
    (is (= [[['DIV] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['DIV] ['TEST]] [1 2 3] 0 [5 20] [])
        )
    )
  )
) 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-eq-1
  (testing "Funcionalidad de EQ en interpretar"
    (is (= [[['EQ] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['EQ] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-eq-2
  (testing "Funcionalidad de EQ en interpretar"
    (is (= [[['EQ] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['EQ] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
) 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-neq-1
  (testing "Funcionalidad de NEQ en interpretar"
    (is (= [[['NEQ] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['NEQ] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-neq-2
  (testing "Funcionalidad de NEQ en interpretar"
    (is (= [[['NEQ] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['NEQ] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-gt-1
  (testing "Funcionalidad de GT en interpretar"
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-gt-2
  (testing "Funcionalidad de GT en interpretar"
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
(deftest test-gt-3
  (testing "Funcionalidad de GT en interpretar"
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
) 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-gte-1
  (testing "Funcionalidad de GTE en interpretar"
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-gte-2
  (testing "Funcionalidad de GTE en interpretar"
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
(deftest test-gte-3
  (testing "Funcionalidad de GTE en interpretar"
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-lt-1
  (testing "Funcionalidad de LT en interpretar"
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-lt-2
  (testing "Funcionalidad de LT en interpretar"
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
(deftest test-lt-3
  (testing "Funcionalidad de LT en interpretar"
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-lte-1
  (testing "Funcionalidad de LTE en interpretar"
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
  )
)
(deftest test-lte-2
  (testing "Funcionalidad de LTE en interpretar"
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
(deftest test-lte-3
  (testing "Funcionalidad de LTE en interpretar"
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-neg-1
  (testing "Funcionalidad de NEG en interpretar"
    (is (= [[['NEG] ['TEST]] [1 2 3] 1 [5 -4] []]
           (interpretar [['NEG] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
(deftest test-neg-2
  (testing "Funcionalidad de NEG en interpretar"
    (is (= [[['NEG] ['TEST]] [1 2 3] 1 [5 4] []]
           (interpretar [['NEG] ['TEST]] [1 2 3] 0 [5 -4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-odd-1
  (testing "Funcionalidad de ODD en interpretar"
    (is (= [[['ODD] ['TEST]] [1 2 3] 1 [5 1] []]
           (interpretar [['ODD] ['TEST]] [1 2 3] 0 [5 3] [])
        )
    )
  )
)
(deftest test-odd-2
  (testing "Funcionalidad de ODD en interpretar"
    (is (= [[['ODD] ['TEST]] [1 2 3] 1 [5 0] []]
           (interpretar [['ODD] ['TEST]] [1 2 3] 0 [5 2] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-jmp
  (testing "Funcionalidad de JMP en interpretar"
    (is (= [[['JMP 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 3 [5 3] []]
           (interpretar [['JMP 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 0 [5 3] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-jc-1
  (testing "Funcionalidad de JC en interpretar"
    (is (= [[['JC 8] ['TEST]] [1 2 3] 1 [5] []]
           (interpretar [['JC 8] ['TEST]] [1 2 3] 0 [5 0] [])
        )
    )
  )
)

(deftest test-jc-2
  (testing "Funcionalidad de JC en interpretar"
    (is (= [[['JC 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 3 [4] []]
           (interpretar [['JC 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-cal
  (testing "Funcionalidad de CAL en interpretar"
    (is (= [[['CAL 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 3 [4 5] [1]]
           (interpretar [['CAL 3] ['ADD] ['ADD] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-ret
  (testing "Funcionalidad de RET en interpretar"
    (is (= [[['RET] ['ADD] ['ADD] ['TEST]] [1 2 3] 3 [4 5] []]
           (interpretar [['RET] ['ADD] ['ADD] ['TEST]] [1 2 3] 0 [4 5] [3])
        )
    )
  )
)