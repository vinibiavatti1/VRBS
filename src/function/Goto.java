package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Goto extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public Goto(VRBSCompiler compiler) {
		super(compiler, "goto",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		int labelLine = getCompiler().getLabelLine(parameters[0]);
		if(labelLine == -1) {
			throw new VRBSException(
					String.format(VRBSMessages.INVALID_LABEL, parameters[0], getCompiler().getCurrentLine()));
		}
		getCompiler().setCurrentLine(labelLine);
	}

}
