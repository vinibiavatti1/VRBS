package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Line extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number //
	};
	
	public Line(VRBSCompiler compiler) {
		super(compiler, "line",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		getCompiler().setCurrentLine(((int)(Double.parseDouble(parameters[0])))-1);
	}

}
