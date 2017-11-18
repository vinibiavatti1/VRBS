package function;

import java.util.ArrayList;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Size extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.List, //
			VRBSDataType.Var //
	};
	
	public Size(VRBSCompiler compiler) {
		super(compiler, "size",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		int size = getCompiler().getLists().get(parameters[0]).size();
		getCompiler().getVars().put(parameters[1],size+"");
	}

}
