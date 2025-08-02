antlr4 AnalizadorParser.g4 AnalizadorLexer.g4

antlr4 -listener -visitor AnalizadorParser.g4

javac AnalizadorLexer*.java AnalizadorParser*.java

# Ver arbol en terminal
grun AnalizadorParser programa -tree < codigo.dec

# Ver arbol en gui
grun AnalizadorParser programa -gui < codigo.dec

# Ver Tokens
grun AnalizadorParser programa -tokens < codigo.dec

antlr4 AnalizadorParser.g4 -visitor
javac *.java
java Main
g++ ejemplo.cpp -o programa
./programa