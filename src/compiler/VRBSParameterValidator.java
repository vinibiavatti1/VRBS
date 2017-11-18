package compiler;

/**
 * Interface de validação de parâmetros
 * 
 * @author vinicius.reif
 *
 */
public interface VRBSParameterValidator {

	/**
	 * Validar e processar parâmetros
	 * @param compiler
	 * @param functionName
	 * @param parameters
	 * @param parametersTypes
	 * @return Parâmetros processados
	 * @throws VRBSException Caso houver erro de validação
	 */
	String[] validateParameters(VRBSCompiler compiler, String functionName, String[] parameters, VRBSDataType[] parametersTypes)
			throws VRBSException;

}
