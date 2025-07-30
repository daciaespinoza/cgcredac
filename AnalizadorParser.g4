grammar AnalizadorParser;

options 
{
    tokenVocab = AnalizadorLexer;
}

// Regla principal que define la estructura base de CREDAC.
programa:
    clases_opt 'comenzar' instrucciones 'terminar'
    ;

// Conjunto de clases definidas antes del programa principal.
clases_opt:
    clase*
    ;

// Lista de instrucciones del programa principal.
instrucciones:
    instruccion*
    ;

instruccion:
    declaracion
    | sentencia
    ;

// Reglas para declarar variables, constantes o arreglos.
declaracion:
    'crear' lista_ids 'como' tipo ('igual' 'a')? expresion '::'
    | 'crear' lista_ids 'como' tipo '::'
    | 'crear' lista_ids 'como' tipo ('igual' 'a')? '[' lista_expresiones ']' '::'
    | 'constante' CONSTANTEID 'como' tipo ('igual' 'a')? expresion '::'
    ;

// Lista de identificadores que pueden estar separados por comas y tener asignaciones o índices.
lista_ids:
    id_item
    | lista_ids ',' id_item
    ;

id_item:
    ID
    | ID ('igual' 'a')? expresion
    | ID '[' expresion ']'
    | ID '[' expresion ']' ('igual' 'a')? expresion
    | ID '[' ']'
    ;

lista_expresiones:
    expresion
    | lista_expresiones ',' expresion
    ;

// Tipos válidos que se pueden usar en variables, parámetros o funciones.
tipo:
    'entero'
    | 'decimal'
    | 'doble'
    | 'texto'
    | 'logico'
    | 'nulo'
    | ID
    ;

// Reglas para las diferentes instrucciones ejecutables de CREDAC.
sentencia:
    'mostrar' lista_mostrar '::'
    | 'leer' ID '::'
    | 'mientras' '(' expresion ')' '{' instrucciones '}'
    | 'si' '(' expresion ')' '{' instrucciones '}'
    | 'si' '(' expresion ')' '{' instrucciones '}' 'sino' '{' instrucciones '}'
    | ID ('igual' 'a')? expresion '::'
    | para_sentencia
    | segun_sentencia
    | hacer_mientras_sentencia
    | ID '[' expresion ']' ('igual' 'a')? expresion '::'
    | funcion
    | 'retornar' expresion '::'
    | acceso_objeto ('igual' 'a')? expresion '::'
    | llamada_metodo '::'
    | llamada_funcion '::'
    | ID '++' '::'
    | ID '--' '::'
    ;

lista_mostrar:
    expresion
    | lista_mostrar ',' expresion
    ;

// Reglas para construir cualquier tipo de expresión, aritmética, lógica o de acceso.
expresion:
    NUMENTERO
    | NUMDECIMAL
    | ID
    | TEXTO
    | 'falso'
    | 'verdadero'
    | ID '[' expresion ']'
    | expresion ('igual' 'a' | 'mayor' 'que' | 'menor' 'que' | 'mayor' 'o' 'igual' 'que' | 'menor' 'o' 'igual' 'que' | 'igual' 'que' | 
    'noes' 'igual' 'a' | 'yy' | 'oo' | '+' | '-' | '*' | '/') expresion
    | '(' expresion ')'
    | 'noes' '(' expresion ')'
    | 'noes' ID
    | acceso_objeto
    | llamada_metodo
    | llamada_funcion
    | '[' lista_expresiones ']'
    ;

lista_expresiones_opt:
     
    | lista_expresiones
    ;

// Estructura del ciclo para
para_sentencia:
    'para' '(' inicializar_expr '::' expresion '::' incre_decre ')' '{' instrucciones '}'
    ;

inicializar_expr:
    'crear' ID 'como' tipo ('igual' 'a')? expresion
    | ID ('igual' 'a')? expresion
    ;

incre_decre:
    ID ('igual' 'a')? expresion
    | ID '++'
    | ID '--'
    ;

// Estructura del ciclo según, que permite múltiples casos.
segun_sentencia:
    'segun' expresion '{' lista_casos caso_defecto '}'
    ;

lista_casos:
    caso*
    ;

caso:
    'caso' expresion ':' instrucciones 'romper' '::'
    ;

caso_defecto:
     
    | 'caso' 'otro' ':' instrucciones 'romper' '::'
    ;

// Estructura del ciclo hacer-mientras, que ejecuta al menos una vez.
hacer_mientras_sentencia:
    'hacer' '{' instrucciones '}' 'mientras' '(' expresion ')' '::'
    ;

// Definición de funciones, incluyendo parámetros y tipos de retorno.
funcion:
    'funcion' ID '(' parametros_opt ')' 'retornar' tipo '{' instrucciones '}'
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

// Definición de clases, incluyendo herencia y miembros.
clase:
    'clase' ID '{' miembros '}'
    | 'clase' ID 'hereda' ID '{' miembros '}'
    ;

miembros:
    miembro*
    ;

miembro:
    visibilidad 'atributo' id_con_sin_arreglo 'como' tipo '::'
    | visibilidad 'atributo' id_con_sin_arreglo 'como' tipo ('igual' 'a') expresion '::'
    | visibilidad 'metodo' ID '(' parametros_opt ')' metodo_retorno '{' instrucciones '}'
    ;

id_con_sin_arreglo:
    ID                     
    | ID '[' expresion ']'    
    ;

visibilidad:
    'publico'
    | 'privado'
    ;

metodo_retorno:
     
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