package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import compiler.VRBSCompiler;
import compiler.VRBSDataType;
import compiler.VRBSException;
import compiler.VRBSFunction;

public class FileWrite extends VRBSFunction{

	public static final VRBSDataType[] PARAMETERS_TYPES = { //
			VRBSDataType.Text, //
			VRBSDataType.Text //
	};
	
	public FileWrite(VRBSCompiler compiler) {
		super(compiler, "fileWrite",PARAMETERS_TYPES);
	}

	@Override
	public void execute(String[] parameters) throws VRBSException {
		parameters = validate(parameters);
		File f = new File(parameters[1]);
		if(!f.exists()) {
			throw new VRBSException("O arquivo especificado não existe!\n" + parameters[1] + " Linha: " + getCompiler().getCurrentLine());
		}
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(f));
			pw.write(parameters[0]);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new VRBSException("Erro ao ler arquivo: " + parameters[1] + "\nErro: " + e.getMessage() + "\nLinha: " + getCompiler().getCurrentLine());
		}
		
	}

}
