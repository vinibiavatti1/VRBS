package compiler;

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
	private VRBSParameterValidator parameterValidator;
	private VRBSScapeCharValidator scapeCharValidator;

	/**
	 * Criar função
	 * 
	 * @param compiler
	 */
	public VRBSFunction(VRBSCompiler compiler, String name, VRBSDataType[] parametersTypes) {
		this.compiler = compiler;
		this.name = name;
		this.parametersTypes = parametersTypes;
		this.parameterValidator = new DefaultParameterValidator();
		this.scapeCharValidator = new DefaultScapeCharValidator();
	}

	/**
	 * Criar função com validadores de parâmetros e caracteres de scape customizados
	 * 
	 * @param compiler
	 */
	public VRBSFunction(VRBSCompiler compiler, String name, VRBSDataType[] parametersTypes,
			VRBSParameterValidator parameterValidator, VRBSScapeCharValidator scapeCharValidator) {
		this.compiler = compiler;
		this.name = name;
		this.parametersTypes = parametersTypes;
		this.parameterValidator = parameterValidator;
		this.scapeCharValidator = scapeCharValidator;
	}

	/**
	 * Executar Abstract
	 * 
	 * @param parameters
	 */
	public abstract void execute(String[] parameters) throws VRBSException;

	/**
	 * Validar parâmetros utilizando validador de parâmetros da função
	 * @param parameters
	 * @return
	 * @throws VRBSException
	 * @see VRBSParameterValidator
	 */
	public String[] validateParameters(String[] parameters) throws VRBSException {
		return this.parameterValidator.validateParameters(compiler, name, parameters, parametersTypes);
	}
	
	/**
	 * Validar caracteres de scape utilizando validador de caracteres de scape da função
	 * @param text
	 * @return
	 * @see VRBSScapeCharValidator
	 */
	public String validadeScapeChar(String text) {
		return this.scapeCharValidator.validateParameters(compiler, text);
	}
	
	/**
	 * @return the parametersTypes
	 */
	public VRBSDataType[] getParametersTypes() {
		return parametersTypes;
	}

	/**
	 * @param parametersTypes
	 *            the parametersTypes to set
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
	 * @return the parameterValidator
	 */
	public VRBSParameterValidator getParameterValidator() {
		return parameterValidator;
	}

	/**
	 * @param parameterValidator
	 *            the parameterValidator to set
	 */
	public void setParameterValidator(VRBSParameterValidator parameterValidator) {
		this.parameterValidator = parameterValidator;
	}

	/**
	 * @return the scapeCharValidator
	 */
	public VRBSScapeCharValidator getScapeCharValidator() {
		return scapeCharValidator;
	}

	/**
	 * @param scapeCharValidator the scapeCharValidator to set
	 */
	public void setScapeCharValidator(VRBSScapeCharValidator scapeCharValidator) {
		this.scapeCharValidator = scapeCharValidator;
	}
}
