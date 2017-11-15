package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class AsciiEncode extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Text //
	};
	
	public AsciiEncode(VRBSCompiler compiler) {
		super(compiler, "asciiEncode",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		char first = parameters[0].charAt(0);
		int code = (int)first;
		getCompiler().getVars().put(parameters[1], code+"");
	}

}
