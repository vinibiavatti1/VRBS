package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class CompilerDelay extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number //
	};
	
	public CompilerDelay(VRBSCompiler compiler) {
		super(compiler, "compilerDelay",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		getCompiler().setDelay((int)Double.parseDouble(parameters[0]));
	}

}
