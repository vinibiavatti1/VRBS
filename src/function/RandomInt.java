package function;

import java.util.Random;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class RandomInt extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Var //
	};
	
	public RandomInt(VRBSCompiler compiler) {
		super(compiler, "randomInt", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double value = new Random().nextInt();
		getCompiler().getVars().put(parameters[0], value + "");

	}

}
