package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class PrintLn extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public PrintLn(VRBSCompiler compiler) {
		super(compiler, "printLn",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		parameters[0] = proccessScapeChars(parameters[0]);
		System.out.println(parameters[0]);
		if(getCompiler().getOutput().getText().equals("")) {
			getCompiler().getOutput().setText(parameters[0] + "\n");
		} else {
			getCompiler().getOutput().setText(getCompiler().getOutput().getText() + parameters[0] + "\n");
		}
	}

}
