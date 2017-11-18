package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Pow extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Pow(VRBSCompiler compiler) {
		super(compiler, "pow", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double val = Math.pow(Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1]));
		getCompiler().getVars().put(parameters[2], val+"");
	}

}
