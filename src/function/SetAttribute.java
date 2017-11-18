package function;

import java.util.HashMap;
import java.util.Map;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class SetAttribute extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Text, //
			VRBSDataType.Object //
	};
	
	public SetAttribute(VRBSCompiler compiler) {
		super(compiler, "setAttribute",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		Map<String, String> obj = getCompiler().getObjects().get(parameters[2]);
		obj.put(parameters[0], parameters[1]);
	}

}
