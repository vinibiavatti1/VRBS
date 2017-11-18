package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Dec extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Dec(VRBSCompiler compiler) {
		super(compiler, "dec", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double value = Double.parseDouble(parameters[0]);
		value--;
		getCompiler().getVars().put(parameters[1], value + "");

	}

}
