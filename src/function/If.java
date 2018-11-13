package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class If extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Logic_Operator, //
			VRBSDataType.Text, //
			VRBSDataType.Text //
	};
	
	public If(VRBSCompiler compiler) {
		super(compiler, "if", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		String op = parameters[1];
		String v1 = parameters[0];
		String v2 = parameters[2];
		int labelLine = getCompiler().getLabelLine(parameters[3]);
		if(labelLine == -1) {
			throw new VRBSException(
					String.format(VRBSMessages.INVALID_LABEL, parameters[3], getCompiler().getCurrentLine()));
		}

		switch (op) {
		case "=":
			if (v1.equals(v2)) {
				getCompiler().setCurrentLine(labelLine);
			} 
			return;
		case "!=":
			if (!v1.equals(v2)) {
				getCompiler().setCurrentLine(labelLine);
			}
			return;
		default:
			break;
		}

		// Números
		double d1 = 0;
		double d2 = 0;
		try {
			d1 = Double.parseDouble(v1);
			d2 = Double.parseDouble(v2);

		} catch (Exception e) {
			throw new VRBSException(
					String.format(VRBSMessages.LOGIC_OPERATORS_WITHOUT_NUMBERS, getCompiler().getCurrentLine()));
		}

		switch (op) {
		case ">":
			if (d1 > d2) {
				getCompiler().setCurrentLine(labelLine);
			}
			break;
		case ">=":
			if (d1 >= d2) {
				getCompiler().setCurrentLine(labelLine);
			}
			break;
		case "<":
			if (d1 < d2) {
				getCompiler().setCurrentLine(labelLine);
			}
			break;
		case "<=":
			if (d1 <= d2) {
				getCompiler().setCurrentLine(labelLine);
			}
			break;
		default:
			break;
		}
	}

}
