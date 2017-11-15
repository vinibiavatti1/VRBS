package compiler;

import java.util.ArrayList;

/**
 * Padrão de parâmetros para VRBS<br>
 * O padrão deve seguir o template x,x,x.. onde x é o tipo do parâmtro<br>
 * <pre>
 * N - Número
 * T - Texto
 * O - Operador Aritimetico
 * L - Operador Lógico
 * A - Lista
 * J - Objeto
 * </pre>
 * @author VINICIUS
 *
 */
public class VRBSPattern {

	private String pattern;

	/**
	 * Criar novo padrão de parâmetros
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
