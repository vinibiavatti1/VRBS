package function;

import java.util.ArrayList;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Insert extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.List //
	};
	
	public Insert(VRBSCompiler compiler) {
		super(compiler, "insert",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		getCompiler().getLists().get(parameters[1]).add(parameters[0]);
	}

}
