package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Length extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Var //
	};
	
	public Length(VRBSCompiler compiler) {
		super(compiler, "length",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		String value = parameters[0];
		System.out.println(value);
		int length = value.length();
		getCompiler().getVars().put(parameters[1], length+"");
	}

}
