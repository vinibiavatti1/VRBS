package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class AsciiDecode extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Text //
	};

	public AsciiDecode(VRBSCompiler compiler) {
		super(compiler, "asciiDecode", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		int value = (int) Double.parseDouble(parameters[0]);
		char ch = (char) value;
		getCompiler().getVars().put(parameters[1], ch + "");
	}

}
