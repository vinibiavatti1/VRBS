package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Sin extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Sin(VRBSCompiler compiler) {
		super(compiler, "sin",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		String valueStr = parameters[0];
		double value = Double.parseDouble(valueStr);
		double sen = Math.sin(value);
		getCompiler().getVars().put(parameters[1], sen+"");
	}

}
