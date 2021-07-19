(ns tp.core-test
  (:require [clojure.test :refer :all]
            [tp.core :refer :all]))
; Formato: [cod mem cont-prg pila-dat pila-llam]
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
    (is (= (a-mayusculas-salvo-strings "write ('Raiz cuadrada de ', n, ': ');")
          "WRITE ('Raiz cuadrada de ', N, ': ');"
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
(deftest test-expresion
  (testing "Expresion"
    (is (= (str (expresion ['- (list (symbol "(") 'X '* 2 '+ 1 (symbol ")") 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :error '[[0] [[X VAR 0]]] 1 []]))
          "[- (( X * 2 + 1 ) END .) [VAR X ; BEGIN X :=] :error [[0] [[X VAR 0]]] 1 []]"
        )
    )
    (is (= (str (expresion ['+ (list (symbol "(") 'X '* 2 '+ 1 (symbol ")") 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0]]] 1 []]))
          "[END (.) [VAR X ; BEGIN X := + ( X * 2 + 1 )] :sin-errores [[0] [[X VAR 0]]] 1 [[PFM 0] [PFI 2] MUL [PFI 1] ADD]]"
        )
    )
    (is (= (str (expresion ['- (list (symbol "(") 'X '* 2 '+ 1 (symbol ")") 'END (symbol ".")) ['VAR 'X (symbol ";") 'BEGIN 'X (symbol ":=")] :sin-errores '[[0] [[X VAR 0]]] 1 []]))
          "[END (.) [VAR X ; BEGIN X := - ( X * 2 + 1 )] :sin-errores [[0] [[X VAR 0]]] 1 [[PFM 0] [PFI 2] MUL [PFI 1] ADD NEG]]"
        )
    )
  )
)

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
    (is (= (aplicar-aritmetico + '[a b c])
           ['a 'b 'c]
        )
    )
    (is (= (aplicar-aritmetico count [1 2 4])
           [1 2 4]
        )
    )
    (is (= (aplicar-aritmetico max [1 2 4])
           [1 2 4]
        )
    )
    (is (= (aplicar-aritmetico min [1 2 4])
           [1 2 4]
        )
    )
    (is (= (aplicar-aritmetico + '[a b c])
           '[a b c]
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
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '>)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET GT]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '>=)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET GTE]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '<)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET LT]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '<=)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET LTE]]
        )
    )
    (is (= (generar-operador-relacional ['WRITELN (list 'END (symbol ".")) [] :sin-errores [[0 3] []] 6 '[[JMP ?] [JMP ?] [CAL 1] RET]] '<>)
          '[WRITELN (END .) [] :sin-errores [[0 3] []] 6 [[JMP ?] [JMP ?] [CAL 1] RET NEQ]]
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
    (is (= (generar-signo [nil () [] :sin-errores '[[0] [[X VAR 0]]] 1 '[MUL ADD]] '/)
          '[nil () [] :sin-errores [[0] [[X VAR 0]]] 1 [MUL ADD]]
        )
    )
  )
)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;