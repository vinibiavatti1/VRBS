package testcase;
import static org.junit.Assert.*;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import org.junit.Test;
import compiler.VRBSCompiler;

/**
 * Teste automatizado
 * @author VINICIUS
 *
 */
public class FunctionTest {

	VRBSCompiler compiler;
	JTextComponent input;
	JTextComponent output;
	
	public FunctionTest() {
		input = new JTextArea();
		output = new JTextArea();
		compiler = new VRBSCompiler(input, output);
	}
	
	@Test
	public void varTest() {
		try {
			input.setText(TestUtils.readTestCode("varTest"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("abc10abc10", output.getText());
	}
	
	@Test
	public void calcTest() {
		try {
			input.setText(TestUtils.readTestCode("calcTest"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("9.03.018.02.00.012.0", output.getText());
	}
	
	@Test
	public void toIntTest() {
		try {
			input.setText(TestUtils.readTestCode("toIntTest"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("1.01", output.getText());
	}
	
	@Test
	public void toFloatTest() {
		try {
			input.setText(TestUtils.readTestCode("toFloatTest"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("11.0", output.getText());
	}

}
