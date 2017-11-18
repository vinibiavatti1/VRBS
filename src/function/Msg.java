package function;

import javax.swing.JOptionPane;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Msg extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public Msg(VRBSCompiler compiler) {
		super(compiler, "msg",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		parameters[0] = validadeScapeChar(parameters[0]);
		JOptionPane.showMessageDialog(null, parameters[0]);
	}

}
