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
public class ExecutionTest {

	VRBSCompiler compiler;
	JTextComponent input;
	JTextComponent output;
	
	public ExecutionTest() {
		input = new JTextArea();
		output = new JTextArea();
		compiler = new VRBSCompiler(input, output);
	}
	
	@Test
	public void varTest1() {
		try {
			input.setText(TestUtils.readTestCode("test1"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("success", output.getText());
	}
	
	@Test
	public void varTest2() {
		try {
			input.setText(TestUtils.readTestCode("test2"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("abc10abc10", output.getText());
	}
	
	@Test
	public void calcTest() {
		try {
			input.setText(TestUtils.readTestCode("test3"));
			compiler.execute();
		} catch (Exception e) {
			output.setText(e.getMessage());
		}
		assertEquals("9.03.018.02.00.012.0", output.getText());
	}
	
	@Test
	public void parseTest() {
		//TODO
	}

}
