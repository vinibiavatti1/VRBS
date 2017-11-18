package compiler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Validador padr�o VRBS de par�metros<br>
 * <br>
 * Esta rotina valida e Processa todos os par�metros enviados na chamada da fun��o.<br><br>
 * <b>Valida��o:</b>
 * <br>
 * 1- Valida��o da quantidade de par�metros estabelecidos<br>
 * 2- Valida��o do tipo de dado informado conforme template de par�metros<br>
 * <br>
 * <b>Processamento:</b>
 * <br>
 * 1- Dados informados como vari�veis ser�o convertidos com o valor da vari�vel estabelecida<br>
 * 
 * @author vinicius.reif
 */
public class DefaultParameterValidator implements VRBSParameterValidator {

	@Override
	public String[] validateParameters(VRBSCompiler compiler, String functionName, String[] parameters, VRBSDataType[] parametersTypes) throws VRBSException {

		// Validar quantidade de par�metros
		if (parametersTypes.length != parameters.length) {
			throw new VRBSException(String.format(VRBSMessages.INCORRECT_PARAMETERS_NUMBER, functionName, parametersTypes.length,
					compiler.getCurrentLine()));
		}

		for (int i = 0; i < parameters.length; i++) {

			// Processar par�metro
			if(VRBSUtils.isText(parameters[i])) {
				parameters[i] = parameters[i].replaceAll("\"", "");
			} else if(compiler.getVars().containsKey(parameters[i])) {
				parameters[i] = compiler.getVars().get(parameters[i]);
			} else if(VRBSUtils.isNumber(parameters[i])) {
			} else if(VRBSUtils.isArithmeticOperator(parameters[i])) {
			} else if(VRBSUtils.isLogicOperator(parameters[i])) {
			} else {
				throw new VRBSException(String.format(VRBSMessages.UNDEFINED_VAR, parameters[i], compiler.getCurrentLine()));
			}

			// Validar par�metro
			switch (parametersTypes[i]) {
			case Number:
				try {
					Double.parseDouble(parameters[i]);
				} catch (Exception e) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_PARAMETERS, functionName, compiler.getCurrentLine()));
				}
				break;
			case Text:
				// Nothing
				break;
			case Aritmethic_Operator:
				if (!VRBSUtils.isArithmeticOperator(parameters[i])) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_OPERATOR, functionName, compiler.getCurrentLine()));
				}
				break;
			case Logic_Operator:
				if (!VRBSUtils.isLogicOperator(parameters[i])) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_OPERATOR, functionName, compiler.getCurrentLine()));
				}
				break;
			case Var:
				if (!compiler.getVars().containsKey(parameters[i])) {
					compiler.getVars().put(parameters[i], "");
				}
				break;
			case List:
				if (!compiler.getLists().containsKey(parameters[i])) {
					compiler.getLists().put(parameters[i], new ArrayList<>());
				}
				break;
			case Object:
				if (!compiler.getObjects().containsKey(parameters[i])) {
					compiler.getObjects().put(parameters[i], new HashMap<>());
				}
				break;
			default:
				throw new VRBSException(String.format(VRBSMessages.INCORRECT_PATTERN, functionName, compiler.getCurrentLine()));
			}
		}
		return parameters;
	}

}
