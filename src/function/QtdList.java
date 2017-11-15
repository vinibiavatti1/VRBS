package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class QtdList extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Var //
	};
	
	public QtdList(VRBSCompiler compiler) {
		super(compiler, "qtdList",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		double val = getCompiler().getLists().size();
		getCompiler().getVars().put(parameters[0], val+"");
	}

}
