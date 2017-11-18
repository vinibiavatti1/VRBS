package compiler;

import java.util.Map;

/**
 * Validador padrão VRBS de caracteres de scape
 * @author vinicius.reif
 *
 */
public class DefaultScapeCharValidator implements VRBSScapeCharValidator {

	@Override
	public String validateParameters(VRBSCompiler compiler, String text) {
		for(Map.Entry<String, String> entry : compiler.getScapeChars().entrySet()) {
			text = text.replaceAll(entry.getKey(), entry.getValue());
		}
		return text;
	}

}
