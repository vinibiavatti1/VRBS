package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Stop extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
	};
	
	public Stop(VRBSCompiler compiler) {
		super(compiler, "stop",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		getCompiler().stop();
	}

}
