package function;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class Import extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text //
	};
	
	private File file;
	
	public Import(VRBSCompiler compiler) {
		super(compiler, "import",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		file = new File(parameters[0]);
		if(!file.exists()) {
			throw new VRBSException("Arquivo inexistente: " + parameters[0]);
		}
	}
	
	/**
	 * Retornar conteudo do arquivo
	 * @return
	 */
	public String getContent() {
		String content = "";
		try {
			Scanner s = new Scanner(file);
			while(s.hasNext()) {
				content += s.nextLine() + "\n";
			}
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

}
