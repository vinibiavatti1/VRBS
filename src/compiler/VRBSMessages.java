package compiler;

public class VRBSMessages {
	public static final String INCORRECT_PARAMETERS = "Parâmetros incorretos na função \"%s\". Linha: %s";
	public static final String INCORRECT_PARAMETERS_NUMBER = "Número de parâmetros incorretos na função \"%s\". Eram esperados \"%s\" parâmetros. Linha: %s";
	public static final String INVALID_LABEL = "Não existe um label com nome \"%s\". Linha: %s";
	public static final String INCORRECT_OPERATOR = "Operador incorreto na função \"%s\". Linha: %s";
	public static final String INCORRECT_PATTERN = "Padrão incorreto na função \"%s\". Linha: %s";
	public static final String VAR_NAME_NULL = "O nome da variável não pode ser nulo \"%s\". Linha: %s";
	public static final String LOGIC_OPERATORS_WITHOUT_NUMBERS = "Instrução \"if()\" com operadores lógicos quantitativos contém parâmetros não numéricos. Linha: %s";
	public static final String LIST_NOT_EXISTS = "Não existe nenhuma instância de lista com nome \"%s\". Linha: %s";
	public static final String LIST_OUT_OF_BOUNDS = "Posição \"%s\" não existe na lista \"%s\". Linha: %s";
	public static final String STRING_WITHOUT_IDEN = "String deve estar entre \"(Áspas duplas)\". Linha: %s";
	public static final String DOUBLE_CONVERSION_FAILED = "Falha ao converter valor em número. Linha: %s";
	public static final String OBJECT_NOT_EXISTS = "Não existe nenhuma instância de objeto com nome \"%s\". Linha: %s";
	public static final String UNDEFINED_VAR = "Não existe nenhuma variável com nome \"%s\". Linha: %s";
	public static final String UNDEFINED_FUNCTION = "Função \"%s\" não existe ou não está registrada no compilador. Linha: %s";
}
