package compiler;

/**
 * Interface de valida��o de par�metros
 * 
 * @author vinicius.reif
 *
 */
public interface VRBSParameterValidator {

	/**
	 * Validar e processar par�metros
	 * @param compiler
	 * @param functionName
	 * @param parameters
	 * @param parametersTypes
	 * @return Par�metros processados
	 * @throws VRBSException Caso houver erro de valida��o
	 */
	String[] validateParameters(VRBSCompiler compiler, String functionName, String[] parameters, VRBSDataType[] parametersTypes)
			throws VRBSException;

}
