package compiler;

public class VRBSMessages {
	public static final String INCORRECT_PARAMETERS = "Par�metros incorretos na fun��o \"%s\". Linha: %s";
	public static final String INCORRECT_PARAMETERS_NUMBER = "N�mero de par�metros incorretos na fun��o \"%s\". Eram esperados \"%s\" par�metros. Linha: %s";
	public static final String INVALID_LABEL = "N�o existe um label com nome \"%s\". Linha: %s";
	public static final String INCORRECT_OPERATOR = "Operador incorreto na fun��o \"%s\". Linha: %s";
	public static final String INCORRECT_PATTERN = "Padr�o incorreto na fun��o \"%s\". Linha: %s";
	public static final String VAR_NAME_NULL = "O nome da vari�vel n�o pode ser nulo \"%s\". Linha: %s";
	public static final String LOGIC_OPERATORS_WITHOUT_NUMBERS = "Instru��o \"if()\" com operadores l�gicos quantitativos cont�m par�metros n�o num�ricos. Linha: %s";
	public static final String LIST_NOT_EXISTS = "N�o existe nenhuma inst�ncia de lista com nome \"%s\". Linha: %s";
	public static final String LIST_OUT_OF_BOUNDS = "Posi��o \"%s\" n�o existe na lista \"%s\". Linha: %s";
	public static final String STRING_WITHOUT_IDEN = "String deve estar entre \"(�spas duplas)\". Linha: %s";
	public static final String DOUBLE_CONVERSION_FAILED = "Falha ao converter valor em n�mero. Linha: %s";
	public static final String OBJECT_NOT_EXISTS = "N�o existe nenhuma inst�ncia de objeto com nome \"%s\". Linha: %s";
	public static final String UNDEFINED_VAR = "N�o existe nenhuma vari�vel com nome \"%s\". Linha: %s";
	public static final String UNDEFINED_FUNCTION = "Fun��o \"%s\" n�o existe ou n�o est� registrada no compilador. Linha: %s";
}
