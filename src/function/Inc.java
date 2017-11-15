package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Inc extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Inc(VRBSCompiler compiler) {
		super(compiler, "inc", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		double value = Double.parseDouble(parameters[0]);
		value++;
		getCompiler().getVars().put(parameters[1], value + "");

	}

}
