package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Exit extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
	};
	
	public Exit(VRBSCompiler compiler) {
		super(compiler, "exit",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		System.exit(0);
	}

}
