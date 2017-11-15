package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Concat extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Text, //
			VRBSDataType.Var //
	};
	
	public Concat(VRBSCompiler compiler) {
		super(compiler, "concat",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		getCompiler().getVars().put(parameters[2], parameters[0] + "" + parameters[1]);
	}

}
