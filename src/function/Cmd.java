package function;

import java.io.IOException;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Cmd extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	public Cmd(VRBSCompiler compiler) {
		super(compiler, "cmd",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validateParameters(parameters);
		try {
			Runtime.getRuntime().exec(parameters[0]);
		} catch (IOException e) {
			e.printStackTrace();
			throw new VRBSException("Erro ao executar comando CMD:\n" + e.getMessage() + "\nLinha: " + getCompiler().getCurrentLine());
		}
	}

}
