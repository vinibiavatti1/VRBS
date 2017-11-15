package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class QtdVar extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Var //
	};
	
	public QtdVar(VRBSCompiler compiler) {
		super(compiler, "qtdVar",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		double val = getCompiler().getVars().size();
		getCompiler().getVars().put(parameters[0], val+"");
	}

}
