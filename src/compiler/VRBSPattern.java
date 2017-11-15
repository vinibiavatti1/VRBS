package compiler;

import java.util.ArrayList;

/**
 * Padr�o de par�metros para VRBS<br>
 * O padr�o deve seguir o template x,x,x.. onde x � o tipo do par�mtro<br>
 * <pre>
 * N - N�mero
 * T - Texto
 * O - Operador Aritimetico
 * L - Operador L�gico
 * A - Lista
 * J - Objeto
 * </pre>
 * @author VINICIUS
 *
 */
public class VRBSPattern {

	private String pattern;

	/**
	 * Criar novo padr�o de par�metros
	 * @param pattern
	 */
	public VRBSPattern(String pattern) {
		super();
		this.pattern = pattern;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
