grammar AnalizadorParser;

// Regla principal que define la estructura base de CREDAC.
programa:
    (clase | funcion)* 'comenzar' sentencia* 'terminar'
    ;

// Reglas para las diferentes sentencia* ejecutables de CREDAC.
sentencia:
    'mostrar' lista_mostrar '::'
    | 'leer' (ID | acceso_objeto) '::'
    | 'mientras' '(' expresion ')' '{' sentencia* '}'
    | sentencia_si
    | ID 'igual' 'a' expresion'::'
    | para_sentencia
    | segun_sentencia
    | hacer_mientras_sentencia
    | ID '[' expresion ']' 'igual' 'a' expresion '::'
    | 'retornar' expresion '::'
    | acceso_objeto ('igual' 'a' expresion)? '::'
    | llamada_metodo '::'
    | llamada_funcion '::'
    | ID ('++' | '--') '::'
    | declaracion
    ;

lista_mostrar:
    expresion (',' expresion)*
    ;

// Reglas para las sentencias condicionales, 'si' y 'sino'.
sentencia_si:
    'si' '(' expresion ')' '{' siBlock=sentencias'}' #siOnly
    | 'si' '(' expresion ')' '{' siBlock=sentencias '}' 'sino' '{' sinoBlock=sentencias '}' #sinoOnly
;
sentencias: sentencia+;

// Reglas para declarar variables, constantes o arreglos.
declaracion:
    'crear' 'como' tipo id_item (',' id_item)*  '::'
    | 'constante' 'como' tipo CONSTANTEID 'igual' 'a' expresion '::'
    ;

id_item:
    ID
    | ID 'igual' 'a' expresion
    | ID '[' expresion ']'
    | ID '[' expresion ']' 'igual' 'a' '[' lista_expresiones ']'
    ;

lista_expresiones:
    expresion (',' expresion)*
    ;

// Tipos válidos que se pueden usar en variables, parámetros o funciones.
tipo:
    'entero'
    | 'decimal'
    | 'doble'
    | 'texto'
    | 'logico'
    | ID
    ;

// Reglas para construir cualquier tipo de expresión, aritmética, lógica o de acceso.
expresion:
    NUMENTERO
    | 'falso'
    | 'verdadero'
    | NUMDECIMAL
    | ID
    | TEXTO
    | expresion ('igual' 'a' | 'mayor' 'que' | 'menor' 'que' | 'mayor' 'o' 'igual' 'que' | 'menor' 'o' 'igual' 'que' | 'igual' 'que' | 
    NOES 'igual' 'a' | 'yy' | 'oo' | '+' | '-' | '*' | '/') expresion
    | ID '[' expresion ']'
    | '(' expresion ')'
    | NOES expresion
    | acceso_objeto
    | llamada_metodo
    | llamada_funcion
    | '[' lista_expresiones ']'
    | CONSTANTEID
    ;

lista_expresiones_opt:
     
    | lista_expresiones
    ;

// Estructura del ciclo para
para_sentencia:
    'para' '(' inicializar_expr '::' expresion '::' incre_decre ')' '{' sentencia* '}'
    ;

inicializar_expr:
    ID ('igual' 'a' expresion)? 
    | 'crear' ID 'como' tipo 'igual' 'a' expresion
    ;

incre_decre:
    ID ('igual' 'a' expresion)? 
    | ID '++'
    | ID '--'
    ;


// Estructura del ciclo según, que permite múltiples casos.
segun_sentencia:
    'segun' expresion '{' caso* caso_defecto '}'
    ;
caso:
    'caso' expresion ':' sentencia* 'romper' '::'
    ;
caso_defecto:
     
    | 'caso' 'otro' ':' sentencia* 'romper' '::'
    ;

// Estructura del ciclo hacer-mientras, que ejecuta al menos una vez.
hacer_mientras_sentencia:
    'hacer' '{' sentencia* '}' 'mientras' '(' expresion ')' '::'
    ;

// Definición de funciones, incluyendo parámetros y tipos de retorno.
funcion:
    'funcion' ID '(' parametros_opt ')' tipo_retorno '{' sentencia* '}'
    ;

parametros_opt:
    
    | lista_parametros
    ;

lista_parametros:
    parametro
    | lista_parametros ',' parametro
    ;

parametro:
    tipo ID
    | tipo NUMENTERO
    | tipo NUMDECIMAL
    | tipo TEXTO
    ;

llamada_funcion:
    ID '(' lista_expresiones_opt ')'
    ;

// Definición de clases, incluyendo herencia y miembro.
clase: 'clase' ID '{' miembro+ '}';

miembro:
    visibilidad 'atributo' ID 'como' tipo '::'
    | visibilidad 'atributo' ID 'como' tipo 'igual' 'a' expresion '::'
    | visibilidad 'metodo' ID '(' parametros_opt ')' tipo_retorno '{' sentencia* '}'
    ;

visibilidad:
    'publico'
    | 'privado'
    ;

tipo_retorno:
    /* vacio */
    | 'retornar' tipo
    ;

// Acceso a atributos o métodos desde un objeto o usando 'este'.
acceso_objeto:
    ID '.' ID
    | ID '.' ID '[' expresion ']'
    | acceso_objeto '.' ID
    | acceso_objeto '.' ID '[' expresion ']'
    | 'este' '.' ID
    | 'este' '.' ID '[' expresion ']'
    ;

llamada_metodo:
    acceso_objeto '(' lista_expresiones_opt ')'
    ;

// Literales e identificadores
NOES: 'noes';
NUMENTERO: [0-9]+;
NUMDECIMAL: [0-9]+ ('.' [0-9]+)?;
TEXTO: '"' (~["\r\n])* '"';
CONSTANTEID: [A-Z_][A-Z0-9_]*;
ID: [a-zA-Z_][a-zA-Z0-9_]*;

// Comentarios
COMENTARIO_LINEA: ':>' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '>>' .*? '<<' -> skip;

// Espacios en blanco
WS: [ \t\r\n]+ -> skip;