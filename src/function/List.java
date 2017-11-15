package function;

import java.util.ArrayList;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class List extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public List(VRBSCompiler compiler) {
		super(compiler, "list",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		getCompiler().getLists().put(parameters[0], new ArrayList<>());
	}

}
