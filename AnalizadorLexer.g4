lexer grammar AnalizadorLexer;

// Palabras clave
COMENZAR: 'comenzar';
TERMINAR: 'terminar';
CREAR: 'crear';
COMO: 'como';
CONSTANTE: 'constante';
FUNCION: 'funcion';
RETORNAR: 'retornar';
SI: 'si';
SINO: 'sino';
MOSTRAR: 'mostrar';
LEER: 'leer';
MIENTRAS: 'mientras';
PARA: 'para';
DESDE: 'desde';
HASTA: 'hasta';
PASO: 'paso';
HACER: 'hacer';
SEGUN: 'segun';
ROMPER: 'romper';
CASO: 'caso';
OTRO: 'otro';
CLASE: 'clase';
ATRIBUTO: 'atributo';
METODO: 'metodo';
HEREDA: 'hereda';
ESTE: 'este';
PUBLICO: 'publico';
PRIVADO: 'privado';

// Tipos de datos
TIPOENTERO: 'entero';
TIPODECIMAL: 'decimal';
TIPODOBLE: 'doble';
TIPOTEXTO: 'texto';
TIPOLOGICO: 'logico';
TIPONULO: 'nulo';

// Valores booleanos
BOOLEANOVERDADERO: 'verdadero';
BOOLEANOFALSO: 'falso';

// Operadores relacionales y lógicos
IGUAL: 'igual';
A: 'a';
QUE: 'que';
MAYOR: 'mayor';
MENOR: 'menor';
O: 'o';
NOES: 'noes';
YY: 'yy';
OO: 'oo';

// Operadores aritméticos
SUMA: '+';
RESTA: '-';
MULTIPLICACION: '*';
DIVISION: '/';
INCREMENTO: '++';
DECREMENTO: '--';

// Símbolos de puntuación
FININSTRUCCION: '::';
DOSPUNTOS: ':';
COMA: ',';
PUNTO: '.';
PARENIZQ: '(';
PARENDER: ')';
LLAVEIZQ: '{';
LLAVEDER: '}';
CORCHETEIZQ: '[';
CORCHETEDER: ']';

// Literales y identificadores
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

ERROR: .;