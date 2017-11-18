package function;

import java.util.ArrayList;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Get extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.List, //
			VRBSDataType.Var //
	};
	
	public Get(VRBSCompiler compiler) {
		super(compiler, "get",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		int idx = (int)Double.parseDouble(parameters[0]);
		if(idx > getCompiler().getLists().get(parameters[1]).size()-1) {
			throw new VRBSException(String.format(VRBSMessages.LIST_OUT_OF_BOUNDS, idx, parameters[1], getCompiler().getCurrentLine()));
		}
		getCompiler().getVars().put(parameters[2], getCompiler().getLists().get(parameters[1]).get((int)Double.parseDouble(parameters[0])));
	}

}
