// Generated from AnalizadorParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AnalizadorParserParser}.
 */
public interface AnalizadorParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AnalizadorParserParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AnalizadorParserParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(AnalizadorParserParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(AnalizadorParserParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#lista_mostrar}.
	 * @param ctx the parse tree
	 */
	void enterLista_mostrar(AnalizadorParserParser.Lista_mostrarContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#lista_mostrar}.
	 * @param ctx the parse tree
	 */
	void exitLista_mostrar(AnalizadorParserParser.Lista_mostrarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code siOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 */
	void enterSiOnly(AnalizadorParserParser.SiOnlyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code siOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 */
	void exitSiOnly(AnalizadorParserParser.SiOnlyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sinoOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 */
	void enterSinoOnly(AnalizadorParserParser.SinoOnlyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sinoOnly}
	 * labeled alternative in {@link AnalizadorParserParser#sentencia_si}.
	 * @param ctx the parse tree
	 */
	void exitSinoOnly(AnalizadorParserParser.SinoOnlyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#sentencias}.
	 * @param ctx the parse tree
	 */
	void enterSentencias(AnalizadorParserParser.SentenciasContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#sentencias}.
	 * @param ctx the parse tree
	 */
	void exitSentencias(AnalizadorParserParser.SentenciasContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(AnalizadorParserParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(AnalizadorParserParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#id_item}.
	 * @param ctx the parse tree
	 */
	void enterId_item(AnalizadorParserParser.Id_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#id_item}.
	 * @param ctx the parse tree
	 */
	void exitId_item(AnalizadorParserParser.Id_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void enterLista_expresiones(AnalizadorParserParser.Lista_expresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void exitLista_expresiones(AnalizadorParserParser.Lista_expresionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AnalizadorParserParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AnalizadorParserParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(AnalizadorParserParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(AnalizadorParserParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#lista_expresiones_opt}.
	 * @param ctx the parse tree
	 */
	void enterLista_expresiones_opt(AnalizadorParserParser.Lista_expresiones_optContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#lista_expresiones_opt}.
	 * @param ctx the parse tree
	 */
	void exitLista_expresiones_opt(AnalizadorParserParser.Lista_expresiones_optContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#para_sentencia}.
	 * @param ctx the parse tree
	 */
	void enterPara_sentencia(AnalizadorParserParser.Para_sentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#para_sentencia}.
	 * @param ctx the parse tree
	 */
	void exitPara_sentencia(AnalizadorParserParser.Para_sentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#inicializar_expr}.
	 * @param ctx the parse tree
	 */
	void enterInicializar_expr(AnalizadorParserParser.Inicializar_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#inicializar_expr}.
	 * @param ctx the parse tree
	 */
	void exitInicializar_expr(AnalizadorParserParser.Inicializar_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#incre_decre}.
	 * @param ctx the parse tree
	 */
	void enterIncre_decre(AnalizadorParserParser.Incre_decreContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#incre_decre}.
	 * @param ctx the parse tree
	 */
	void exitIncre_decre(AnalizadorParserParser.Incre_decreContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#segun_sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSegun_sentencia(AnalizadorParserParser.Segun_sentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#segun_sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSegun_sentencia(AnalizadorParserParser.Segun_sentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#caso}.
	 * @param ctx the parse tree
	 */
	void enterCaso(AnalizadorParserParser.CasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#caso}.
	 * @param ctx the parse tree
	 */
	void exitCaso(AnalizadorParserParser.CasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void enterCaso_defecto(AnalizadorParserParser.Caso_defectoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void exitCaso_defecto(AnalizadorParserParser.Caso_defectoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#hacer_mientras_sentencia}.
	 * @param ctx the parse tree
	 */
	void enterHacer_mientras_sentencia(AnalizadorParserParser.Hacer_mientras_sentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#hacer_mientras_sentencia}.
	 * @param ctx the parse tree
	 */
	void exitHacer_mientras_sentencia(AnalizadorParserParser.Hacer_mientras_sentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#funcion}.
	 * @param ctx the parse tree
	 */
	void enterFuncion(AnalizadorParserParser.FuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#funcion}.
	 * @param ctx the parse tree
	 */
	void exitFuncion(AnalizadorParserParser.FuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#parametros_opt}.
	 * @param ctx the parse tree
	 */
	void enterParametros_opt(AnalizadorParserParser.Parametros_optContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#parametros_opt}.
	 * @param ctx the parse tree
	 */
	void exitParametros_opt(AnalizadorParserParser.Parametros_optContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#lista_parametros}.
	 * @param ctx the parse tree
	 */
	void enterLista_parametros(AnalizadorParserParser.Lista_parametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#lista_parametros}.
	 * @param ctx the parse tree
	 */
	void exitLista_parametros(AnalizadorParserParser.Lista_parametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(AnalizadorParserParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(AnalizadorParserParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_funcion(AnalizadorParserParser.Llamada_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_funcion(AnalizadorParserParser.Llamada_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#clase}.
	 * @param ctx the parse tree
	 */
	void enterClase(AnalizadorParserParser.ClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#clase}.
	 * @param ctx the parse tree
	 */
	void exitClase(AnalizadorParserParser.ClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#miembro}.
	 * @param ctx the parse tree
	 */
	void enterMiembro(AnalizadorParserParser.MiembroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#miembro}.
	 * @param ctx the parse tree
	 */
	void exitMiembro(AnalizadorParserParser.MiembroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#visibilidad}.
	 * @param ctx the parse tree
	 */
	void enterVisibilidad(AnalizadorParserParser.VisibilidadContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#visibilidad}.
	 * @param ctx the parse tree
	 */
	void exitVisibilidad(AnalizadorParserParser.VisibilidadContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#tipo_retorno}.
	 * @param ctx the parse tree
	 */
	void enterTipo_retorno(AnalizadorParserParser.Tipo_retornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#tipo_retorno}.
	 * @param ctx the parse tree
	 */
	void exitTipo_retorno(AnalizadorParserParser.Tipo_retornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#acceso_objeto}.
	 * @param ctx the parse tree
	 */
	void enterAcceso_objeto(AnalizadorParserParser.Acceso_objetoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#acceso_objeto}.
	 * @param ctx the parse tree
	 */
	void exitAcceso_objeto(AnalizadorParserParser.Acceso_objetoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParserParser#llamada_metodo}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_metodo(AnalizadorParserParser.Llamada_metodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParserParser#llamada_metodo}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_metodo(AnalizadorParserParser.Llamada_metodoContext ctx);
}