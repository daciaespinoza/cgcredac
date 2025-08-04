import java.io.PrintWriter;
import java.util.List;

public class CodeGenerator extends AnalizadorParserBaseVisitor<String> {
    private final StringBuilder output = new StringBuilder();
    private final StringBuilder classDeclarations = new StringBuilder();
    private final StringBuilder mainCode = new StringBuilder();
    
    // Método auxiliar para convertir tipos de CREDAC a C++
    private String convertirTipo(String tipoCREDAC) {
        switch (tipoCREDAC.toLowerCase()) {
            case "entero":
                return "int";
            case "decimal":
                return "double";
            case "doble":
                return "double";
            case "texto":
                return "string";
            case "logico":
                return "bool";
            default:
                // Si no es un tipo primitivo, asumir que es una clase
                return tipoCREDAC;
        }
    }

    @Override
    public String visitPrograma(AnalizadorParserParser.ProgramaContext ctx) {

        // Procesar clases
        for (var claseCtx : ctx.clase()) {
            classDeclarations.append(visit(claseCtx)).append("\n");
        }

        // Procesar clases
        for (var funcCtx : ctx.funcion()) {
            classDeclarations.append(visit(funcCtx)).append("\n");
        }

        // Procesar sentencias del bloque principal
        mainCode.append("int main() {\n");
        for (var sentenciaCtx : ctx.sentencia()) {
            mainCode.append(visit(sentenciaCtx)).append("\n");
        }
        mainCode.append("   return 0;\n}\n");

        // Combinar todo
        output.append("#include <iostream>\n");
        output.append("#include <string>\n");
        output.append("using namespace std;\n\n");
        output.append(classDeclarations).append("\n");
        output.append(mainCode);

        return output.toString();
    }


    @Override
    public String visitSentencia(AnalizadorParserParser.SentenciaContext ctx) {
        // caso: este.ID igual a expresion ::
        if (ctx.acceso_objeto() != null && ctx.getChild(1).getText().equals("igual")) {
            String acceso = visit(ctx.acceso_objeto());
            String expr = visit(ctx.expresion(0));
            
            // Si el acceso es a un atributo o miembro de la clase
            if (acceso.startsWith("este")) {
                return acceso + " = " + expr + ";";
            }

            return acceso + " = " + expr + ";";
        }

        // Mostrar una sentencia
        if (ctx.getChild(0).getText().equals("mostrar")) {
            return visit(ctx.lista_mostrar());
        }

        // Leer sentencia
        if (ctx.getChild(0).getText().equals("leer")) {
            String variable = "";

            if (ctx.ID() != null) {
                variable = ctx.ID().getText();
            } else if (ctx.acceso_objeto() != null) {
                variable = visit(ctx.acceso_objeto());
            }

            return "   cin >> " + variable + ";";
        }
        // Incremento y Decremento
        if (ctx.getChildCount() > 1)
        {
            if (ctx.getChild(1).getText().equals("++") || ctx.getChild(1).getText().equals("--")) {
                return "   " + ctx.ID().getText() + ctx.getChild(1).getText() + ";";
            }
        }
        
        // Declaracion
        if (ctx.declaracion() != null) {
            return visit(ctx.declaracion());
        }

        if (ctx.getChild(0).getText().equals("mientras")) {
            StringBuilder sb = new StringBuilder();
            sb.append("   while (" + visit(ctx.expresion(0)) + ") {\n");

            StringBuilder body = new StringBuilder();
            for (var stmt : ctx.sentencia()) {
                body.append("     ").append(visit(stmt)).append("\n");
            }

            sb.append(body);
            sb.append("   }");
            return sb.toString();
        }

        if (ctx.sentencia_si() != null) {
            return visit(ctx.sentencia_si());
        }

        // Asignación: ID igual a expresion ::
        if (ctx.ID() != null && ctx.getChild(1).getText().equals("igual")) {
            String variable = ctx.ID().getText();
            String expr = visit(ctx.expresion(0));
            return "   " + variable + " = " + expr + ";";
        }

        // Para sentencia
        if (ctx.para_sentencia() != null) {
            return visit(ctx.para_sentencia());
        }

        // Según sentencia  
        if (ctx.segun_sentencia() != null) {
            return visit(ctx.segun_sentencia());
        }

        // Llamada a método
        if (ctx.llamada_metodo() != null) {
            return visit(ctx.llamada_metodo()) + ";";
        }

        // Llamada a función 
        if (ctx.llamada_funcion() != null) {
            return "   " + visit(ctx.llamada_funcion()) + ";";
        }

        // Hacer mientras sentencia
        if (ctx.hacer_mientras_sentencia() != null) {
            return visit(ctx.hacer_mientras_sentencia());
        }

        // Asignación a array: ID '[' expresion ']' igual a expresion ::
        if (ctx.ID() != null && ctx.getChild(1).getText().equals("[") && ctx.getChild(3).getText().equals("]") 
            && ctx.getChild(4).getText().equals("igual")) {
            String variable = ctx.ID().getText();
            String indice = visit(ctx.expresion(0));
            String valor = visit(ctx.expresion(1));
            return "   " + variable + "[" + indice + "] = " + valor + ";";
        }

        if (ctx.expresion().size() == 2)
        {
            return ctx.ID().getText() + "[" + visit(ctx.expresion(0)) + "]" + " = " + visit(ctx.expresion(1));
        }

        // Sentencia Retornar  
        if (ctx.getChild(0).getText().equals("retornar")) {
            return "return " + visit(ctx.expresion(0)) + ";";
        }

        return "";
    }

    @Override
    public String visitClase(AnalizadorParserParser.ClaseContext ctx) {
        StringBuilder claseCode = new StringBuilder();
        String nombreClase = ctx.ID().getText();

        claseCode.append("class ").append(nombreClase).append(" {\n\n");

        StringBuilder publicMembers = new StringBuilder();
        StringBuilder privateMembers = new StringBuilder();

        if (ctx.miembro() != null) {
            for (AnalizadorParserParser.MiembroContext miembroCtx : ctx.miembro()) {
                String visibilidad = miembroCtx.visibilidad().getText();
                String miembroCode = visit(miembroCtx);

                // Indentación manual
                String miembroIndentado = indentar(miembroCode, "   ");

                if ("privado".equals(visibilidad)) {
                    privateMembers.append(miembroIndentado).append("\n");
                } else if ("publico".equals(visibilidad)) {
                    publicMembers.append(miembroIndentado).append("\n");
                }
            }
        }

        // Agregar miembros privados
        if (privateMembers.length() > 0) {
            claseCode.append(privateMembers.toString()).append("\n");
        }

        // Agregar miembros públicos
        claseCode.append("   public:\n");
        claseCode.append("     ").append(nombreClase).append("() {}\n");
        claseCode.append(publicMembers.toString());
        claseCode.append("};\n");

        return claseCode.toString();
    }


    private String indentar(String texto, String prefijo) {
        StringBuilder resultado = new StringBuilder();
        String[] lineas = texto.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            resultado.append(prefijo).append(lineas[i]);
            if (i < lineas.length - 1) {
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }

    @Override
    public String visitMiembro(AnalizadorParserParser.MiembroContext ctx) {
        String visibilidad = ctx.visibilidad().getText().equals("publico") ? "public:" : "private:";

        if (ctx.getChild(1).getText().equals("atributo")) {
            String tipo = visit(ctx.tipo());
            String nombre = ctx.ID().getText();
            String declaracion = tipo + " " + nombre;

            // Si hay expresión, significa que es la segunda regla con inicialización
            if (ctx.expresion() != null) {
                String valorInicial = visit(ctx.expresion());
                declaracion += " = " + valorInicial;
            }

            return visibilidad + "\n " + " " + declaracion + ";";
        }

        if (ctx.getChild(1).getText().equals("metodo")) {
            String nombreMetodo = ctx.ID().getText();
            String parametros = visit(ctx.parametros_opt());
            String tipoRetorno = (ctx.tipo_retorno() != null && ctx.tipo_retorno().tipo() != null)
                                ? visit(ctx.tipo_retorno().tipo())
                                : "void";

            StringBuilder body = new StringBuilder();
            for (var stmt : ctx.sentencia()) {
                body.append("   ").append(visit(stmt)).append("\n");
            }

            return visibilidad + "\n " + tipoRetorno + " " + nombreMetodo + "(" + parametros + ") {\n" + body + " }";
        }

        return "";
    }

    @Override
    public String visitLista_mostrar(AnalizadorParserParser.Lista_mostrarContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("   cout << ");

        // Recorrer todas las expresiones en la lista de mostrar
        java.util.List<AnalizadorParserParser.ExpresionContext> expresiones = ctx.expresion();
        
        for (int i = 0; i < expresiones.size(); i++) {
            sb.append(visit(expresiones.get(i)));
            
            if (i < expresiones.size() - 1) {
                sb.append(" << ");
            }
        }
        
        sb.append(" << endl;");
        return sb.toString();
    }

    @Override
    public String visitSiOnly(AnalizadorParserParser.SiOnlyContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append("   if (").append(visit(ctx.expresion())).append(") {\n");

        for (AnalizadorParserParser.SentenciaContext stmt : ctx.siBlock.sentencia()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.trim().isEmpty()) {
                sb.append(indentar(stmtCode, "      ")).append("\n"); // 6 espacios
            }
        }

        sb.append("   }"); // cierre del if
        return sb.toString();
    }

    @Override
    public String visitSinoOnly(AnalizadorParserParser.SinoOnlyContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append("   if (").append(visit(ctx.expresion())).append(") {\n");

        for (AnalizadorParserParser.SentenciaContext stmt : ctx.siBlock.sentencia()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.trim().isEmpty()) {
                sb.append(indentar(stmtCode, "      ")).append("\n"); // 6 espacios
            }
        }

        sb.append("   } else {\n");

        for (AnalizadorParserParser.SentenciaContext stmt : ctx.sinoBlock.sentencia()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.trim().isEmpty()) {
                sb.append(indentar(stmtCode, "      ")).append("\n"); // 6 espacios
            }
        }

        sb.append("   }"); // cierre del else
        return sb.toString();
    }

    @Override
    public String visitParametros_opt(AnalizadorParserParser.Parametros_optContext ctx) {
        if (ctx.lista_parametros() != null) {
            return visit(ctx.lista_parametros());
        }
        return ""; // vacío si no hay parámetros
    }

    @Override
    public String visitLista_parametros(AnalizadorParserParser.Lista_parametrosContext ctx) {
        if (ctx.parametro() != null && ctx.lista_parametros() == null) {
            return visit(ctx.parametro());
        } else {
            return visit(ctx.lista_parametros()) + ", " + visit(ctx.parametro());
        }
    }

    @Override
    public String visitParametro(AnalizadorParserParser.ParametroContext ctx) {
        String tipo = visit(ctx.tipo());
        String nombre = ctx.ID() != null ? ctx.ID().getText()
                    : ctx.NUMENTERO() != null ? ctx.NUMENTERO().getText()
                    : ctx.NUMDECIMAL() != null ? ctx.NUMDECIMAL().getText()
                    : ctx.TEXTO().getText();
        return convertirTipo(tipo) + " " + nombre;
    }

    @Override
    public String visitTipo(AnalizadorParserParser.TipoContext ctx) {
        if (ctx.getText().equals("entero")) return "int";
        if (ctx.getText().equals("decimal") || ctx.getText().equals("doble")) return "double";
        if (ctx.getText().equals("texto")) return "string";
        if (ctx.getText().equals("logico")) return "bool";
        return ctx.getText(); // tipo personalizado (ID)
    }


    @Override
    public String visitAcceso_objeto(AnalizadorParserParser.Acceso_objetoContext ctx) {
        if (ctx.getChildCount() == 3) { // ID '.' ID  o acceso_objeto '.' ID
            String left = ctx.getChild(0).getText();
            String right = ctx.getChild(2).getText();
            
            // Si es acceso a un miembro de la clase (uso de 'este')
            if (left.equals("este")) {
                return "   this->" + right;
            }
            return "   " + left + "." + right;
        } else if (ctx.getChildCount() == 6) { // ID '.' ID '[' expresion ']' o acceso_objeto '.' ID '[' expresion ']'
            String left = visit(ctx.getChild(0));
            String id = ctx.getChild(2).getText();
            String index = visit(ctx.getChild(4));
            
            // Si es acceso a un miembro de la clase (uso de 'este')
            if (left.equals("este")) {
                return "   this->" + id + "[" + index + "]";
            }
            return "   " + left + "." + id + "[" + index + "]";
        } else if (ctx.getChild(0).getText().equals("este")) {
            String id = ctx.getChild(2).getText();
            if (ctx.getChildCount() == 3) {
                return "   this->" + id;
            } else {
                String index = visit(ctx.getChild(4));
                return "   this->" + id + "[" + index + "]";
            }
        }
        return "";
    }

    @Override
    public String visitExpresion(AnalizadorParserParser.ExpresionContext ctx) {

        if (ctx == null) {
            return "0";
        }

        // Literales y valores logicos
        if (ctx.NUMENTERO() != null) return ctx.NUMENTERO().getText();
        if (ctx.NUMDECIMAL() != null) return ctx.NUMDECIMAL().getText();
        if (ctx.ID() != null) {
            // Verificar si es acceso a arreglo: ID [ expresion ]
            if (ctx.getChildCount() == 4 && ctx.getChild(1).getText().equals("[")) {
                String arreglo = ctx.ID().getText();
                String indice = visit(ctx.expresion(0));
                return arreglo + "[" + indice + "]";
            }
            // Si no, es solo un ID normal
            return ctx.ID().getText();
        }
        if (ctx.CONSTANTEID() != null) return ctx.CONSTANTEID().getText();
        if (ctx.TEXTO() != null) return ctx.TEXTO().getText();
        if (ctx.getText().equals("verdadero")) return "true";
        if (ctx.getText().equals("falso")) return "false";

        // Operadores binarios
        if (ctx.expresion().size() == 2) {

            String left = visit(ctx.expresion(0));
            String right = visit(ctx.expresion(1));

            if (ctx.getChild(1).getText().equals("menor") && ctx.getChild(2).getText().equals("que"))
            {
                return "(" + left + " < " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("igual") && ctx.getChild(2).getText().equals("que"))
            {
                return "(" + left + " == " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("igual") && ctx.getChild(2).getText().equals("a"))
            {
                return "(" + left + " = " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("mayor") && ctx.getChild(2).getText().equals("que"))
            {
                return "(" + left + " > " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("mayor") && ctx.getChild(2).getText().equals("o"))
            {
                return "(" + left + " >= " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("menor") && ctx.getChild(2).getText().equals("o"))
            {
                return "(" + left + " <= " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("noes"))
            {
                return "(" + left + " != " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("yy"))
            {
                return "(" + left + " && " + right + ")";
            }

            if (ctx.getChild(1).getText().equals("oo"))
            {
                return "(" + left + " || " + right + ")";
            }

            return "(" + left + " " + ctx.getChild(1).getText() + " " + right + ")"; 
        }

        // Operador unario "noes" al inicio
        if (ctx.getChild(0).getText().equals("noes"))
        {
            return "!" + visit(ctx.expresion(0));
        }

        // Operadores unarios
        if (ctx.getChildCount() == 2 && ctx.expresion().size() == 1) {
            String operator = ctx.getChild(0).getText();
            String expr = visit(ctx.expresion(0));
            if (operator.equals("noes")) {
                return "!" + expr;
            }
        }

        // Expresion en Paréntesis
        if (ctx.getChildCount() == 3 && ctx.getChild(0).getText().equals("(")) {
            return "(" + visit(ctx.expresion(0)) + ")";
        }

        // Llamadas a métodos o funciones
        if (ctx.llamada_metodo() != null) return visit(ctx.llamada_metodo());
        if (ctx.llamada_funcion() != null) return visit(ctx.llamada_funcion());

        // Acceso a objetos
        if (ctx.acceso_objeto() != null) return visit(ctx.acceso_objeto());

        // Expresiones de arreglos literales
        if (ctx.getChildCount() > 2 && ctx.getChild(0).getText().equals("[")) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < ctx.expresion().size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visit(ctx.expresion(i)));
            }
            sb.append("]");
            return sb.toString();
        }

        // Si no se reconoce la expresión, devolver el texto original
        return ctx.getText();
    }

    @Override
    public String visitDeclaracion(AnalizadorParserParser.DeclaracionContext ctx) {

        StringBuilder code = new StringBuilder();
        String tipo = convertirTipo(ctx.tipo().getText());
        
        if (ctx.CONSTANTEID() != null) {
            // Declaración de constante
            code.append("   const ").append(tipo).append(" ")
                .append(ctx.CONSTANTEID().getText())
                .append(" = ").append(visit(ctx.expresion()))
                .append(";");
        
        } else {
            // Declaración normal o de arreglo
            
            List<AnalizadorParserParser.Id_itemContext> items = ctx.id_item();
        
            code.append("   ").append(convertirTipo(tipo)).append(" "); 
            
            for (AnalizadorParserParser.Id_itemContext item : items) 
            {
                if (item.getChildCount() == 1)
                {
                    code.append(item.ID().getText());
                } 
                else if (item.getChild(1).getText().equals("igual"))
                {
                    code.append(item.ID().getText()).append(" = ").append(visit(item.expresion()));
                }
                else if (item.lista_expresiones() != null)
                {
                    code.append(item.ID().getText())
                        .append("[")
                        .append(visit(item.expresion()))
                        .append("]")
                        .append(" = {")
                        .append(item.lista_expresiones().getText())
                        .append("}");
                }
                else
                {
                    code.append(item.ID().getText()).append("[").append(visit(item.expresion())).append("]");
                }
                
                code.append(", ");
            }

            code.setLength(code.length() - 2);
            code.append(";");
        }
        return code.toString();
    }
    
    @Override
    public String visitId_item(AnalizadorParserParser.Id_itemContext ctx) {
        String id = ctx.ID().getText();
        
        if (ctx.getChildCount() == 1) {
            return id;
        } else if (ctx.getChildCount() >= 3 && ctx.getChild(1).getText().equals("[")) {
            // ID [ expresion ] o ID [ expresion ] igual a expresion
            String arrayDecl = id + "[" + visit(ctx.expresion()) + "]";
            
            if (ctx.expresion() != null) {
                // ID [exp] igual a expr
                arrayDecl += " = " + visit(ctx.expresion());
            }
            return arrayDecl;
        } else if (ctx.getChild(1).getText().equals("igual")) {
            // ID igual a expresion
            return id + " = " + visit(ctx.expresion());
        }
        
        return id; 
    }

    @Override
    public String visitPara_sentencia(AnalizadorParserParser.Para_sentenciaContext ctx) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("   for (");
        
        // Inicialización
        sb.append(visit(ctx.inicializar_expr()));
        sb.append("; ");
        
        // Condición
        sb.append(visit(ctx.expresion()));
        sb.append("; ");
        
        // Incremento/Decremento
        sb.append(visit(ctx.incre_decre()));
        
        sb.append(") {\n");
        
        // Cuerpo del ciclo
        for (AnalizadorParserParser.SentenciaContext stmt : ctx.sentencia()) {
            sb.append("     ").append(visit(stmt)).append("\n");
        }
        
        sb.append("   }");
        return sb.toString();
    }

    @Override
    public String visitInicializar_expr(AnalizadorParserParser.Inicializar_exprContext ctx) {
        // Caso: crear ID como tipo igual a expresion
        if (ctx.getChild(0).getText().equals("crear")) {
            String tipo = visit(ctx.tipo());
            String variable = ctx.ID().getText();
            
            if (ctx.expresion() != null) {
                String valor = visit(ctx.expresion());
                return tipo + " " + variable + " = " + valor;
            } else {
                return tipo + " " + variable;
            }
        }
        // Caso: ID igual a expresion
        else {
            String variable = ctx.ID().getText();
            if (ctx.expresion() != null) {
                String valor = visit(ctx.expresion());
                return variable + " = " + valor;
            } else {
                return variable;
            }
        }
    }

    @Override
    public String visitIncre_decre(AnalizadorParserParser.Incre_decreContext ctx) {
        String variable = ctx.ID().getText();
        
        // Caso: ID igual a expresion
        if (ctx.expresion() != null) {
            String valor = visit(ctx.expresion());
            return variable + " = " + valor;
        }
        // Caso: ID++
        else if (ctx.getChild(1).getText().equals("++")) {
            return variable + "++";
        }
        // Caso: ID--
        else if (ctx.getChild(1).getText().equals("--")) {
            return variable + "--";
        }
        
        return variable;
    }

    @Override
    public String visitSegun_sentencia(AnalizadorParserParser.Segun_sentenciaContext ctx) {
        StringBuilder switchCode = new StringBuilder();
        String condition = visit(ctx.expresion());

        switchCode.append("switch (").append(condition).append(") {\n");

        for (AnalizadorParserParser.CasoContext caso : ctx.caso()) {
            String casoCode = visit(caso);
            switchCode.append(indentar(casoCode, "    ")).append("\n"); // 4 espacios
        }

        if (ctx.caso_defecto() != null) {
            String defectoCode = visit(ctx.caso_defecto());
            switchCode.append(indentar(defectoCode, "    ")).append("\n");
        }
        switchCode.append("}");
        // return switchCode.toString();
        return indentar(switchCode.toString(), "   ");
    }

    @Override
    public String visitCaso(AnalizadorParserParser.CasoContext ctx) {
        StringBuilder cppCode = new StringBuilder();
        String caseValue = visit(ctx.expresion());
        cppCode.append("case ").append(caseValue).append(":\n");
        
        for (AnalizadorParserParser.SentenciaContext stmt : ctx.sentencia()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.trim().isEmpty()) {
                cppCode.append(stmtCode).append("\n"); // sin indentar aquí
            }
        }

        cppCode.append("break;\n"); // sin indentar aquí
        return cppCode.toString();
    }

    @Override
    public String visitCaso_defecto(AnalizadorParserParser.Caso_defectoContext ctx) {
        if (ctx.getChildCount() == 0) {
            return "";
        }

        StringBuilder cppCode = new StringBuilder();
        cppCode.append("default:\n");
        
        for (AnalizadorParserParser.SentenciaContext stmt : ctx.sentencia()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.trim().isEmpty()) {
                cppCode.append(stmtCode).append("\n"); // sin indentar aquí
            }
        }

        cppCode.append("break;\n"); // sin indentar aquí
        return cppCode.toString();
    }

    @Override
    public String visitHacer_mientras_sentencia(AnalizadorParserParser.Hacer_mientras_sentenciaContext ctx) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("   do {\n");
        
        // Procesar sentencias dentro del do
        for (AnalizadorParserParser.SentenciaContext stmt : ctx.sentencia()) {
            sb.append("     ").append(visit(stmt)).append("\n");
        }
        
        sb.append("   } while (").append(visit(ctx.expresion())).append(");");
        
        return sb.toString();
    }

    @Override
    public String visitFuncion(AnalizadorParserParser.FuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        String tipoRetorno = ctx.tipo_retorno() != null && ctx.tipo_retorno().tipo() != null
                            ? visit(ctx.tipo_retorno().tipo()) : "void";
        String parametros = visit(ctx.parametros_opt());

        StringBuilder cuerpo = new StringBuilder();
        for (var stmt : ctx.sentencia()) {
            cuerpo.append("   ").append(visit(stmt)).append("\n");
        }

        return tipoRetorno + " " + nombreFuncion + "(" + parametros + ") {\n" + cuerpo + "}";
    }

    @Override
    public String visitLlamada_funcion(AnalizadorParserParser.Llamada_funcionContext ctx) {
        String nombre = ctx.ID().getText();
        String args = visit(ctx.lista_expresiones_opt());
        return nombre + "(" + args + ")";
    }

    @Override
    public String visitLlamada_metodo(AnalizadorParserParser.Llamada_metodoContext ctx) {
        String acceso = visit(ctx.acceso_objeto());
        String args = visit(ctx.lista_expresiones_opt());
        return acceso + "(" + args + ")";
    }

    @Override
    public String visitLista_expresiones_opt(AnalizadorParserParser.Lista_expresiones_optContext ctx) {
        if (ctx.lista_expresiones() != null) {
            return visit(ctx.lista_expresiones());
        }
        return "";
    }

    @Override
    public String visitLista_expresiones(AnalizadorParserParser.Lista_expresionesContext ctx) {
        if (ctx.expresion().size() == 1) {
            return visit(ctx.expresion(0));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.expresion().size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(ctx.expresion(i)));
        }
        return sb.toString();
    }
}