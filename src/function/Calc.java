package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Calc extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number, //
			VRBSDataType.Aritmethic_Operator, //
			VRBSDataType.Number, //
			VRBSDataType.Var //
	};
	
	public Calc(VRBSCompiler compiler) {
		super(compiler, "calc", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		double v1 = Double.parseDouble(parameters[0]);
		double v2 = Double.parseDouble(parameters[2]);
		String op = parameters[1];
		double result = 0.0;
		switch (op) {
		case "+":
			result = v1 + v2;
			break;
		case "-":
			result = v1 - v2;
			break;
		case "*":
			result = v1 * v2;
			break;
		case "/":
			result = v1 / v2;
			break;
		case "%":
			result = v1 % v2;
			break;
		default:
			break;
		}
	    getCompiler().getVars().put(parameters[3], result+"");
	}

}
