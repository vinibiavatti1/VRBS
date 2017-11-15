package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Case extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Text, //
			VRBSDataType.Text //
	};
	
	public Case(VRBSCompiler compiler) {
		super(compiler, "case",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		String value = parameters[0];
		int labelLine = getCompiler().getLabelLine(parameters[2]);
		if(labelLine == -1) {
			throw new VRBSException(
					String.format(VRBSMessages.INVALID_LABEL, parameters[2], getCompiler().getCurrentLine()));
		}
		if(parameters[1].equals(value)) {
			getCompiler().setCurrentLine(labelLine);
		}
	}

}
