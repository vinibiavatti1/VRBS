package function;

import java.util.Map;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class GetObjName extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public GetObjName(VRBSCompiler compiler) {
		super(compiler, "getObjName",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		int index = (int)Double.parseDouble(parameters[0]);
		int count = 0;
		String varName = "";
		for(Map.Entry<String, Map<String, String>> entry : getCompiler().getObjects().entrySet()) {
		    if(count != index) {
		    	count++;
		    	continue;
		    }
		    varName = entry.getKey();
		    break;
		}
		getCompiler().getVars().put(parameters[1], varName);
	}

}
