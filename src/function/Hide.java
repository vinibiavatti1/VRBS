package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Hide extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
	};
	
	public Hide(VRBSCompiler compiler) {
		super(compiler, "hide",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		getCompiler().getParentFrame().setVisible(false);
	}

}
