package function;

import java.util.HashMap;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Obj extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public Obj(VRBSCompiler compiler) {
		super(compiler, "obj",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		getCompiler().getObjects().put(parameters[0], new HashMap<>());
	}

}
