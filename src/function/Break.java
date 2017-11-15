package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Break extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
	};
	
	public Break(VRBSCompiler compiler) {
		super(compiler, "break",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		getCompiler().setBreakCursor(true);
	}

}
