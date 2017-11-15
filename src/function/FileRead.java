package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class FileRead extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Var //
	};
	
	public FileRead(VRBSCompiler compiler) {
		super(compiler, "fileRead",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		File f = new File(parameters[0]);
		if(!f.exists()) {
			throw new VRBSException("O arquivo especificado não existe!\n" + parameters[0] + " Linha: " + getCompiler().getCurrentLine());
		}
		try {
			Scanner s = new Scanner(new FileInputStream(f));
			String content = "";
			while(s.hasNext()) {
				content += s.nextLine() + "\n";
			}
			getCompiler().getVars().put(parameters[1], content);
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new VRBSException("Erro ao ler arquivo: " + parameters[0] + "\nErro: " + e.getMessage() + "\nLinha: " + getCompiler().getCurrentLine());
		}
		
	}

}
