package compiler;

/**
 * Pacote de utilizades para VRBS
 * @author vinicius.reif
 *
 */
public class VRBSUtils {

	/**
	 * Checar se o parâmetro é um operador lógico
	 * @param par
	 * @return
	 */
	public static boolean isLogicOperator(String par) {
		return VRBSCompiler.LOGIC_OPERATORS.contains(par);
	}
	
	/**
	 * Checar se o parâmetro é um operador aritmético
	 * @param par
	 * @return
	 */
	public static boolean isArithmeticOperator(String par) {
		return VRBSCompiler.ARITHMETIC_OPERATORS.contains(par);
	}
	
	/**
	 * Checar se o parâmetro contém aspas
	 * @param par
	 * @return
	 */
	public static boolean isText(String par) {
		return par.startsWith("\"") && par.endsWith("\"");
	}
	
	/**
	 * Checar se o parâmetro é um número
	 * @param par
	 * @return
	 */
	public static boolean isNumber(String par) {
		try {
			Double.parseDouble(par);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Retornar nome da função
	 * 
	 * @param functionStr
	 * @return
	 */
	public static String extractFunctionName(String functionStr) {
		return functionStr.split("\\(")[0].trim();
	}

	/**
	 * Retornar parâmetros da função
	 * 
	 * @param functionStr
	 * @return
	 */
	public static String[] extractFunctionParameters(String functionStr) {
		String[] parameters = functionStr.split("\\(")[1].replaceAll("\\)", "").split(",");
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = parameters[i].trim();
		}
		return parameters;
	}
}
