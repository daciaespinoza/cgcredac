import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Archivo de entrada
        String inputFile = "ejemplo.dec"; 
        if (args.length > 0) {
            inputFile = args[0];
        }
        
        CharStream input = CharStreams.fromFileName(inputFile);
        AnalizadorParserLexer lexer = new AnalizadorParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AnalizadorParserParser parser = new AnalizadorParserParser(tokens);
        
        // Add error listener to detect syntax issues
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                  int line, int charPositionInLine,
                                  String msg, RecognitionException e) {
                System.err.println("Syntax error at " + line + ":" + charPositionInLine + " - " + msg);
            }
        });
        
        // Iniciar desde la regla 'programa'
        ParseTree tree = parser.programa();
        
        // Verificar si hubo errores de parsing
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Parseo falló por error de sintaxis.");
            return;
        }
        
        // Generar código C++
        CodeGenerator generator = new CodeGenerator();
        String output = generator.visit(tree);
        
        // Escribir archivo de salida
        Files.write(Paths.get("salida.cpp"), output.getBytes());
        System.out.println("======================================");
        System.out.println("Generado exitosamente en lenguaje C++!");
        System.out.println("======================================");
    }
}