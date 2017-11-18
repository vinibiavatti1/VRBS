package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Print extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public Print(VRBSCompiler compiler) {
		super(compiler, "print", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		parameters[0] = validadeScapeChar(parameters[0]);
		System.out.print(parameters[0]);
		if(getCompiler().getOutput().getText().equals("")) {
			getCompiler().getOutput().setText(parameters[0]);
		} else {
			getCompiler().getOutput().setText(getCompiler().getOutput().getText() + parameters[0]);
		}
	}

}
