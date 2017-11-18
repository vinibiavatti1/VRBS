package function;

import javax.swing.JOptionPane;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Input extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Var //
	};
	
	public Input(VRBSCompiler compiler) {
		super(compiler, "input",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		parameters[0] = validadeScapeChar(parameters[0]);
		String value = JOptionPane.showInputDialog(null, parameters[0]);
		getCompiler().getVars().put(parameters[1], value);
	}

}
