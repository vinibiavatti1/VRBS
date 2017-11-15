package function;

import java.util.ArrayList;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Remove extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.List //
	};
	
	public Remove(VRBSCompiler compiler) {
		super(compiler, "remove",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		int idx = (int)Double.parseDouble(parameters[0]);
		if(idx > getCompiler().getLists().get(parameters[1]).size()-1) {
			throw new VRBSException(String.format(VRBSMessages.LIST_OUT_OF_BOUNDS, idx, parameters[1], getCompiler().getCurrentLine()));
		}
		getCompiler().getLists().get(parameters[1]).remove((int)Double.parseDouble(parameters[0]));
	}

}
