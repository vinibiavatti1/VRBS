package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Clear extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
	};
	
	public Clear(VRBSCompiler compiler) {
		super(compiler, "clear",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		getCompiler().getOutput().setText("");
		
	}

}
