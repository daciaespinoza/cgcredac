// Generated from AnalizadorParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AnalizadorParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnalizadorParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AnalizadorParserParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(AnalizadorParserParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#lista_mostrar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_mostrar(AnalizadorParserParser.Lista_mostrarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code siOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSiOnly(AnalizadorParserParser.SiOnlyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sinoOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinoOnly(AnalizadorParserParser.SinoOnlyContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#sentencias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencias(AnalizadorParserParser.SentenciasContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(AnalizadorParserParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#id_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_item(AnalizadorParserParser.Id_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#lista_expresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_expresiones(AnalizadorParserParser.Lista_expresionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(AnalizadorParserParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(AnalizadorParserParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#lista_expresiones_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_expresiones_opt(AnalizadorParserParser.Lista_expresiones_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#para_sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPara_sentencia(AnalizadorParserParser.Para_sentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#inicializar_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializar_expr(AnalizadorParserParser.Inicializar_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#incre_decre}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncre_decre(AnalizadorParserParser.Incre_decreContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#segun_sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegun_sentencia(AnalizadorParserParser.Segun_sentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#caso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso(AnalizadorParserParser.CasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#caso_defecto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_defecto(AnalizadorParserParser.Caso_defectoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#hacer_mientras_sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHacer_mientras_sentencia(AnalizadorParserParser.Hacer_mientras_sentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion(AnalizadorParserParser.FuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#parametros_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros_opt(AnalizadorParserParser.Parametros_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#lista_parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_parametros(AnalizadorParserParser.Lista_parametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(AnalizadorParserParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#llamada_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_funcion(AnalizadorParserParser.Llamada_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClase(AnalizadorParserParser.ClaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#miembro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembro(AnalizadorParserParser.MiembroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#visibilidad}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilidad(AnalizadorParserParser.VisibilidadContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#tipo_retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_retorno(AnalizadorParserParser.Tipo_retornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#acceso_objeto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcceso_objeto(AnalizadorParserParser.Acceso_objetoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParserParser#llamada_metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_metodo(AnalizadorParserParser.Llamada_metodoContext ctx);
}