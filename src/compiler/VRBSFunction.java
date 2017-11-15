package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Template de Função
 * 
 * @author VINICIUS
 *
 */
public abstract class VRBSFunction {

	protected VRBSCompiler compiler;
	private String name;
	private VRBSDataType[] parametersTypes;

	/**
	 * Criar função
	 * 
	 * @param compiler
	 */
	public VRBSFunction(VRBSCompiler compiler, String name, VRBSDataType[] parametersTypes) {
		this.compiler = compiler;
		this.name = name;
		this.parametersTypes = parametersTypes;
	}

	/**
	 * Executar Abstract
	 * 
	 * @param parameters
	 */
	public abstract void execute(String[] parameters) throws VRBSException;

	/**
	 * @return the parametersTypes
	 */
	public VRBSDataType[] getParametersTypes() {
		return parametersTypes;
	}

	/**
	 * @param parametersTypes the parametersTypes to set
	 */
	public void setParametersTypes(VRBSDataType[] parametersTypes) {
		this.parametersTypes = parametersTypes;
	}

	/**
	 * @return the compiler
	 */
	public VRBSCompiler getCompiler() {
		return compiler;
	}

	/**
	 * @param compiler
	 *            the compiler to set
	 */
	public void setCompiler(VRBSCompiler compiler) {
		this.compiler = compiler;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Validar e Processar todos os parâmetros enviados na chamada da função.<br><br>
	 * <b>Validação:</b>
	 * <br>
	 * 1- Validação da quantidade de parâmetros estabelecidos<br>
	 * 2- Validação do tipo de dado informado conforme template de parâmetros<br>
	 * <br>
	 * <b>Processamento:</b>
	 * <br>
	 * 1- Dados informados como variáveis serão convertidos com o valor da variável estabelecida<br>
	 * 
	 * @param parameters
	 * @throws VRBSException Caso houver inconsistencias nos parâmetros enviados.
	 */
	public String[] validate(String[] parameters) throws VRBSException {
		
		// Validar quantidade de parâmetros
		if (parametersTypes.length != parameters.length) {
			throw new VRBSException(String.format(VRBSMessages.INCORRECT_PARAMETERS_NUMBER, getName(), parametersTypes.length,
					this.compiler.getCurrentLine()));
		}

		for (int i = 0; i < parameters.length; i++) {

			// Processar parâmetro
			if(isText(parameters[i])) {
				parameters[i] = parameters[i].replaceAll("\"", "");
			} else if(isVar(parameters[i])) {
				parameters[i] = getCompiler().getVars().get(parameters[i]);
			} else if(isNumber(parameters[i])) {
			} else if(isArithmeticOperator(parameters[i])) {
			} else if(isLogicOperator(parameters[i])) {
			} else {
				throw new VRBSException(String.format(VRBSMessages.UNDEFINED_VAR, parameters[i], getCompiler().getCurrentLine()));
			}

			// Validar parâmetro
			switch (parametersTypes[i]) {
			case Number:
				try {
					Double.parseDouble(parameters[i]);
				} catch (Exception e) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_PARAMETERS, getName(), compiler.getCurrentLine()));
				}
				break;
			case Text:
				// Nothing
				break;
			case Aritmethic_Operator:
				if (!isArithmeticOperator(parameters[i])) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_OPERATOR, getName(), compiler.getCurrentLine()));
				}
				break;
			case Logic_Operator:
				if (!isLogicOperator(parameters[i])) {
					throw new VRBSException(String.format(VRBSMessages.INCORRECT_OPERATOR, getName(), compiler.getCurrentLine()));
				}
				break;
			case Var:
				if (!getCompiler().getVars().containsKey(parameters[i])) {
					getCompiler().getVars().put(parameters[i], "");
				}
				break;
			case List:
				if (!getCompiler().getLists().containsKey(parameters[i])) {
					getCompiler().getLists().put(parameters[i], new ArrayList<>());
				}
				break;
			case Object:
				if (!getCompiler().getObjects().containsKey(parameters[i])) {
					getCompiler().getObjects().put(parameters[i], new HashMap<>());
				}
				break;
			default:
				throw new VRBSException(String.format(VRBSMessages.INCORRECT_PATTERN, getName(), compiler.getCurrentLine()));
			}
		}
		return parameters;
	}
	
	/**
	 * Checar se o parâmetro é um operador lógico
	 * @param par
	 * @return
	 */
	public boolean isLogicOperator(String par) {
		return VRBSCompiler.LOGIC_OPERATORS.contains(par);
	}
	
	/**
	 * Checar se o parâmetro é um operador aritmético
	 * @param par
	 * @return
	 */
	public boolean isArithmeticOperator(String par) {
		return VRBSCompiler.ARITHMETIC_OPERATORS.contains(par);
	}
	
	/**
	 * Checar se o parâmetro contém aspas
	 * @param par
	 * @return
	 */
	public boolean isText(String par) {
		return par.startsWith("\"") && par.endsWith("\"");
	}
	
	/**
	 * Checar se o parâmetro é um número
	 * @param par
	 * @return
	 */
	public boolean isNumber(String par) {
		try {
			Double.parseDouble(par);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Retorna true caso o parâmetro for uma variável
	 * @param par
	 * @return
	 */
	public boolean isVar(String par) {
		return getCompiler().getVars().containsKey(par);
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

	/**
	 * Processar caracteres de scape
	 * 
	 * @param s
	 * @return
	 */
	public String proccessScapeChars(String s) {
		s = s.replaceAll("%n", "\n");
		return s;
	}
}
