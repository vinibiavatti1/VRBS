package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class CharAt extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Text, //
			VRBSDataType.Var //
	};
	
	public CharAt(VRBSCompiler compiler) {
		super(compiler, "charAt",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		String value = parameters[1];
		char c = value.charAt((int)Double.parseDouble(parameters[0]));
		getCompiler().getVars().put(parameters[2], c+"");
	}

}
