package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class QtdObj extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Var //
	};
	
	public QtdObj(VRBSCompiler compiler) {
		super(compiler, "qtdObj",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double val = getCompiler().getObjects().size();
		getCompiler().getVars().put(parameters[0], val+"");
	}

}
