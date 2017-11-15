package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;
import compiler.VRBSMessages;

public class Check extends VRBSFunction {

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Logic_Operator, //
			VRBSDataType.Text //
	};
	
	public Check(VRBSCompiler compiler) {
		super(compiler, "check", PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		String op = parameters[1];
		String v1 = parameters[0];
		String v2 = parameters[2];

		switch (op) {
		case "=":
			if (v1.equals(v2)) {
				
			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
		case "!=":
			if (!v1.equals(v2)) {

			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
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

			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
		case ">=":
			if (d1 >= d2) {

			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
		case "<":
			if (d1 < d2) {

			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
		case "<=":
			if (d1 <= d2) {

			} else {
				getCompiler().setCurrentLine(getCompiler().getCurrentLine()+1);
			}
			break;
		default:
			break;
		}
	}

}
