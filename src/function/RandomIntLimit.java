package function;

import java.util.Random;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class RandomIntLimit extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public RandomIntLimit(VRBSCompiler compiler) {
		super(compiler, "randomIntLimit", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double value = new Random().nextInt((int)Double.parseDouble(parameters[0]));
		getCompiler().getVars().put(parameters[1], value + "");

	}

}
