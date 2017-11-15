package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class ToFloat extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public ToFloat(VRBSCompiler compiler) {
		super(compiler, "toFloat", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		double value = Double.parseDouble(parameters[0]);
		this.compiler.getVars().put(parameters[1], value + "");

	}

}
