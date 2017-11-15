package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Random extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Var //
	};
	
	public Random(VRBSCompiler compiler) {
		super(compiler, "random", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		double value = Math.random();
		getCompiler().getVars().put(parameters[0], value + "");

	}

}
