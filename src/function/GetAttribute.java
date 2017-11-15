package function;

import java.util.HashMap;
import java.util.Map;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class GetAttribute extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Object, //
			VRBSDataType.Var //
	};
	
	public GetAttribute(VRBSCompiler compiler) {
		super(compiler, "getAttribute",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		Map<String, String> obj = getCompiler().getObjects().get(parameters[1]);
		String value = "";
		if(obj.containsKey(parameters[0])) {
			value = obj.get(parameters[0]);
		}
		getCompiler().getVars().put(parameters[2], value);
	}

}
