package function;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Delay extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Number //
	};
	
	public Delay(VRBSCompiler compiler) {
		super(compiler, "delay",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		try {
			Thread.sleep(Long.parseLong(parameters[0]));
		} catch (NumberFormatException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
