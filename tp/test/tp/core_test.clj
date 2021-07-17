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
(deftest test-add
  (testing "Funcionalidad de ADD en interpretar"
    (is (= [[['ADD] ['TEST]] [1 2 3] 1 [9] []]
           (interpretar [['ADD] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
    (is (= [[['ADD] ['TEST]] [1 2 3] 1 [9] []]
           (interpretar [['ADD] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-sub
  (testing "Funcionalidad de SUB en interpretar"
    (is (= [[['SUB] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['SUB] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
    (is (= [[['SUB] ['TEST]] [1 2 3] 1 [-1] []]
           (interpretar [['SUB] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-mul
  (testing "Funcionalidad de MUL en interpretar"
    (is (= [[['MUL] ['TEST]] [1 2 3] 1 [20] []]
           (interpretar [['MUL] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
    (is (= [[['MUL] ['TEST]] [1 2 3] 1 [20] []]
           (interpretar [['MUL] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-div
  (testing "Funcionalidad de DIV en interpretar"
    (is (= [[['DIV] ['TEST]] [1 2 3] 1 [4] []]
           (interpretar [['DIV] ['TEST]] [1 2 3] 0 [20 5] [])
        )
    )
    (is (= [[['DIV] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['DIV] ['TEST]] [1 2 3] 0 [5 20] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-eq
  (testing "Funcionalidad de EQ en interpretar"
    (is (= [[['EQ] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['EQ] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['EQ] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['EQ] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-neq
  (testing "Funcionalidad de NEQ en interpretar"
    (is (= [[['NEQ] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['NEQ] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['NEQ] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['NEQ] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-gt
  (testing "Funcionalidad de GT en interpretar"
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
    (is (= [[['GT] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GT] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-gte
  (testing "Funcionalidad de GTE en interpretar"
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
    (is (= [[['GTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['GTE] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-lt
  (testing "Funcionalidad de LT en interpretar"
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
    (is (= [[['LT] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LT] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-lte
  (testing "Funcionalidad de LTE en interpretar"
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [4 4] [])
        )
    )
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [1] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [4 5] [])
        )
    )
    (is (= [[['LTE] ['TEST]] [1 2 3] 1 [0] []]
           (interpretar [['LTE] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-neg
  (testing "Funcionalidad de NEG en interpretar"
    (is (= [[['NEG] ['TEST]] [1 2 3] 1 [5 -4] []]
           (interpretar [['NEG] ['TEST]] [1 2 3] 0 [5 4] [])
        )
    )
    (is (= [[['NEG] ['TEST]] [1 2 3] 1 [5 4] []]
           (interpretar [['NEG] ['TEST]] [1 2 3] 0 [5 -4] [])
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-odd
  (testing "Funcionalidad de ODD en interpretar"
    (is (= [[['ODD] ['TEST]] [1 2 3] 1 [5 1] []]
           (interpretar [['ODD] ['TEST]] [1 2 3] 0 [5 3] [])
        )
    )
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
(deftest test-jc
  (testing "Funcionalidad de JC en interpretar"
    (is (= [[['JC 8] ['TEST]] [1 2 3] 1 [5] []]
           (interpretar [['JC 8] ['TEST]] [1 2 3] 0 [5 0] [])
        )
    )
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest test-a-mayusculas-salvo-strings
  (testing "A mayusculas salvo string"
    (is (= (a-mayusculas-salvo-strings "  const Y = 2;")
           "  CONST Y = 2;"
        )
    )
    (is (= (a-mayusculas-salvo-strings "  writeln ('Se ingresa un valor, se muestra su doble.');")
           "  WRITELN ('Se ingresa un valor, se muestra su doble.');"
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-palabra-reservada?
  (testing "Palabra reservada"
    (is (= (palabra-reservada? 'CALL)
           true
        )
    )
    (is (= (palabra-reservada? "CALL")
           true
        )
    )    
    (is (= (palabra-reservada? 'ASIGNAR)
           false
        )
    )
    (is (= (palabra-reservada? "ASIGNAR")
           false
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-identificador?
  (testing "Identificador"
    (is (= (identificador? 2)
           false
        )
    )
    (is (= (identificador? 'V2)
           true
        )
    )    
    (is (= (identificador? "V2")
           true
        )
    )
    (is (= (identificador? 'CALL)
           false
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-cadena?
  (testing "Cadena"
    (is (= (cadena? "'Hola'")
           true
        )
    )    
    (is (= (cadena? "Hola")
           false
        )
    )    
    (is (= (cadena? "'Hola")
           false
        )
    )
    (is (= (cadena? 'Hola)
           false
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-ya-declarado-localmente?
  (testing "Ya declarado localmente"
    (is (= (ya-declarado-localmente? 'Y '[[0] [[X VAR 0] [Y VAR 1]]])
           true
        )
    )    
    (is (= (ya-declarado-localmente? 'Z '[[0] [[X VAR 0] [Y VAR 1]]])
           false
        )
    )    
    (is (= (ya-declarado-localmente? 'Y '[[0 3 5] [[X VAR 0] [Y VAR 1] [INICIAR PROCEDURE 1] [Y CONST 2] [ASIGNAR PROCEDURE 2]]])
           false
        )
    )    
    (is (= (ya-declarado-localmente? 'Y '[[0 3 5] [[X VAR 0] [Y VAR 1] [INICIAR PROCEDURE 1] [Y CONST 2] [ASIGNAR PROCEDURE 2] [Y CONST 6]]])
           true
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-cargar-var-en-tabla
  (testing "Cargar var en tabla"
    (is (= (cargar-var-en-tabla '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]])
           '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]]
        )
    )    
    (is (= (cargar-var-en-tabla '[nil () [VAR X] :sin-errores [[0] []] 0 [[JMP ?]]])
           '[nil () [VAR X] :sin-errores [[0] [[X VAR 0]]] 1 [[JMP ?]]]
        )
    )    
    (is (= (cargar-var-en-tabla '[nil () [VAR X , Y] :sin-errores [[0] [[X VAR 0]]] 1 [[JMP ?]]])
           '[nil () [VAR X Y] :sin-errores [[0] [[X VAR 0] [Y VAR 1]]] 2 [[JMP ?]]]
        )
    )    
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-inicializar-contexto-local
  (testing "Iniciar contexto local"
    (is (= (inicializar-contexto-local '[nil () [] :error [[0] [[X VAR 0] [Y VAR 1] [INI PROCEDURE 1]]] 2 [[JMP ?]]])
           '[nil () [] :error [[0] [[X VAR 0] [Y VAR 1] [INI PROCEDURE 1]]] 2 [[JMP ?]]]
        )
    )    
    (is (= (inicializar-contexto-local '[nil () [] :sin-errores [[0] [[X VAR 0] [Y VAR 1] [INI PROCEDURE 1]]] 2 [[JMP ?]]])
           '[nil () [] :sin-errores [[0 3] [[X VAR 0] [Y VAR 1] [INI PROCEDURE 1]]] 2 [[JMP ?]]]
        )
    )   
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-declaracion-var
  (testing "Declaracion de variables"
    (is (= (str (declaracion-var ['VAR (list 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=") 7 (symbol ";") 'Y (symbol ":=") 12 (symbol ";") 'END (symbol ".")) [] :error [[0] []] 0 '[[JMP ?]]]))
          "[VAR (X , Y ; BEGIN X := 7 ; Y := 12 ; END .) [] :error [[0] []] 0 [[JMP ?]]]"
        )
    )
    (is (= (str (declaracion-var ['VAR (list 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=") 7 (symbol ";") 'Y (symbol ":=") 12 (symbol ";") 'END (symbol ".")) [] :sin-errores [[0] []] 0 '[[JMP ?]]]))
          "[BEGIN (X := 7 ; Y := 12 ; END .) [VAR X , Y ;] :sin-errores [[0] [[X VAR 0] [Y VAR 1]]] 2 [[JMP ?]]]"
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-procesar-signo-unario
  (testing "Procesar-signo-unario"
    (is (= (str (procesar-signo-unario ['+ (list 7 (symbol ";") 'Y ':= '- 12 (symbol ";") 'END (symbol ".")) ['VAR 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=")] :error '[[0] [[X VAR 0] [Y VAR 1]]] 2 []]))
          "[+ (7 ; Y := - 12 ; END .) [VAR X , Y ; BEGIN X :=] :error [[0] [[X VAR 0] [Y VAR 1]]] 2 []]"
        )
    )
    (is (= (str (procesar-signo-unario [7 (list (symbol ";") 'Y ':= '- 12 (symbol ";") 'END (symbol ".")) ['VAR 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0] [Y VAR 1]]] 2 []]))
          "[7 (; Y := - 12 ; END .) [VAR X , Y ; BEGIN X :=] :sin-errores [[0] [[X VAR 0] [Y VAR 1]]] 2 []]"
        )
    )
    (is (= (str (procesar-signo-unario ['+ (list 7 (symbol ";") 'Y ':= '- 12 (symbol ";") 'END (symbol ".")) ['VAR 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0] [Y VAR 1]]] 2 []]))
          "[7 (; Y := - 12 ; END .) [VAR X , Y ; BEGIN X := +] :sin-errores [[0] [[X VAR 0] [Y VAR 1]]] 2 []]"
        )
    )
    (is (= (str (procesar-signo-unario ['- (list 7 (symbol ";") 'Y ':= '- 12 (symbol ";") 'END (symbol ".")) ['VAR 'X (symbol ",") 'Y (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0] [Y VAR 1]]] 2 []]))
          "[7 (; Y := - 12 ; END .) [VAR X , Y ; BEGIN X := -] :sin-errores [[0] [[X VAR 0] [Y VAR 1]]] 2 []]"
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-termino
  (testing "Termino"
    (is (= (str (termino ['X (list '* 2 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :error '[[0] [[X VAR 0]]] 1 []]))
          "[X (* 2 END .) [VAR X ; BEGIN X :=] :error [[0] [[X VAR 0]]] 1 []]"
        )
    )
    (is (= (str (termino ['X (list '* 2 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0]]] 1 []]))
          "[END (.) [VAR X ; BEGIN X := X * 2] :sin-errores [[0] [[X VAR 0]]] 1 [[PFM 0] [PFI 2] MUL]]"
        )
    )
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;(deftest test-expresion
;  (testing "Expresion"
;    (is (= (str (expresion ['- (list (symbol "(") 'X '* 2 '+ 1 (symbol ")") 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0]]] 1 []]))
;          true
;        )
;    )
;  )
;)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-aplicar-aritmetico
  (testing "Aplicar aritmetico"
    (is (= (aplicar-aritmetico + [1 2])
           [3]
        )
    )
    (is (= (aplicar-aritmetico - [1 4 1])
           [1 3]
        )
    )
    (is (= (aplicar-aritmetico * [1 2 4])
           [1 8]
        )
    )
    (is (= (aplicar-aritmetico / [1 2 4])
           [1 0]
        )
    )
    (is (= (aplicar-aritmetico + nil)
           nil
        )
    )
    (is (= (aplicar-aritmetico + [])
           []
        )
    )
    (is (= (aplicar-aritmetico + [1])
           [1]
        )
    )
    (is (= (aplicar-aritmetico 'hola [1 2 4])
           [1 2 4]
        )
    )
    (is (= (aplicar-aritmetico count [1 2 4])
           [1 2 4]
        )
    )
    (is (= (aplicar-aritmetico + '[a b c])
           ['a 'b 'c]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-aplicar-relacional
  (testing "Aplicar relacional"
    (is (= (aplicar-relacional > [7 5])
           [1]
        )
    )
    (is (= (aplicar-relacional > [4 7 5])
           [4 1]
        )
    )
    (is (= (aplicar-relacional = [4 7 5])
           [4 0]
        )
    )
    (is (= (aplicar-relacional not= [4 7 5])
           [4 1]
        )
    )
    (is (= (aplicar-relacional < [4 7 5])
           [4 0]
        )
    )
    (is (= (aplicar-relacional <= [4 6 6])
           [4 1]
        )
    )
    (is (= (aplicar-relacional <= '[a b c])
           ['a 'b 'c]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-generar
  (testing "Generar"
    (is (= (generar '[nil () [VAR X] :sin-errores [[0] []] 0 [[JMP ?]]] 'HLT)
          '[nil () [VAR X] :sin-errores [[0] []] 0 [[JMP ?] HLT]]
      )
    )
    (is (= (generar '[nil () [VAR X] :sin-errores [[0] []] 0 [[JMP ?]]] 'PFM 0)
          '[nil () [VAR X] :sin-errores [[0] []] 0 [[JMP ?] [PFM 0]]]
        )
    )
    (is (= (generar '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]] 'HLT)
          '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]]
        )
    )
    (is (= (generar '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]] 'PFM 0)
          '[nil () [VAR X] :error [[0] []] 0 [[JMP ?]]]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-buscar-coincidencias
  (testing "Buscar coincidencias"
    (is (= (buscar-coincidencias '[nil () [CALL X] :sin-errores [[0 3] [[X VAR 0] [Y VAR 1] [A PROCEDURE 1] [X VAR 2] [Y VAR 3] [B PROCEDURE 2]]] 6 [[JMP ?] [JMP 4] [CAL 1] RET]])
          '([X VAR 0] [X VAR 2])
        )
    )
    (is (= (buscar-coincidencias '[nil () [CALL Y] :sin-errores [[0 3] [[X VAR 0] [X VAR 1] [A PROCEDURE 1] [X VAR 2] [X VAR 3] [B PROCEDURE 2]]] 6 [[JMP ?] [JMP 4] [CAL 1] RET]])
          '()
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-fixup
  (testing "Fix Up"
    (is (= (fixup ['WRITELN (list 'END (symbol ".")) [] :error [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] 1)
          '[WRITELN (END .) [] :error [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET]]
        )
    )
    (is (= (fixup ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] 1)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP 4] [CAL 1] RET]]
        )
    )
    (is (= (fixup ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP 4] [CAL 1] RET [PFM 2] OUT NL RET]] 0)
            '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP 8] [JMP 4] [CAL 1] RET [PFM 2] OUT NL RET]]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-generar-relacional
  (testing "Generar relacional"
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :error [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '=)
          '[WRITELN (END .) [] :error [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET]]
      )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '+)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '=)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET EQ]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '>=)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET GTE]]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest test-generar-signos
  (testing "Generar signos"
    (is (= (generar-signo [nil () [] :error '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '-)
          '[nil () [] :error [[0] [[X VAR 0]]] 1 [MUL ADD]]
        )
    )
    (is (= (generar-signo [nil () [] :error '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '+)
          '[nil () [] :error [[0] [[X VAR 0]]] 1 [MUL ADD]]
        )
    )
    (is (= (generar-signo [nil () [] :sin-errores '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '+)
          '[nil () [] :sin-errores [[0] [[X VAR 0]]] 1 [MUL ADD]]
        )
    )
    (is (= (generar-signo [nil () [] :sin-errores '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '*)
          '[nil () [] :sin-errores [[0] [[X VAR 0]]] 1 [MUL ADD]]
        )
    )    
    (is (= (generar-signo [nil () [] :sin-errores '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '-)
          '[nil () [] :sin-errores [[0] [[X VAR 0]]] 1 [MUL ADD NEG]]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;