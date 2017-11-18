package compiler;

/**
 * Interface de validação de caracteres de scape
 * @author vinicius.reif
 *
 */
public interface VRBSScapeCharValidator {

	/**
	 * Validar e processar caracteres de Scape
	 * @param compiler
	 * @param text
	 * @return
	 */
	String validateParameters(VRBSCompiler compiler, String text);
	
}
