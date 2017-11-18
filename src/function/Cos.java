package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Cos extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Cos(VRBSCompiler compiler) {
		super(compiler, "cos",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		String valueStr = parameters[0];
		double value = Double.parseDouble(valueStr);
		double cos = Math.cos(value);
		getCompiler().getVars().put(parameters[1], cos+"");
	}

}
