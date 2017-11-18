package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import function.*;

/**
 * Compilador para leitura de código VRBS. O compilador tem como principal
 * rotina: <br>
 * 1- Entrada do Script <br>
 * 2- Compilação das Funções do Script <br>
 * 3- Execução e Processamento das Funções <br>
 * <br>
 * Realize a execução de um script no input de entrada utilizando o método
 * <code>execute()</code>
 * <br>
 * Para poder utilizar funções customizadas no compilador, a função deve ser registrada
 * utilizando o método <code>registerFunction()</code>
 * 
 * @author Vinícius Reif Biavatti
 *
 */
public class VRBSCompiler {

	public static final List<String> ARITHMETIC_OPERATORS = new ArrayList<>();
	public static final List<String> LOGIC_OPERATORS = new ArrayList<>();

	private String functionRegexPattern = "[0-9A-Za-z_\\-.]+\\(.*\\)";
	private String importRegexPattern = "import+\\(.*\\)";

	private JTextComponent input;
	private JTextComponent output;
	private JTable codeTable = new JTable();
	private JTable varTable = new JTable();
	private JTable listTable = new JTable();
	private JTable objTable = new JTable();
	private JFrame parentFrame = new JFrame();

	private List<String> codeSequence = new ArrayList<>();
	private Map<String, VRBSFunction> functions = new HashMap<>();
	private Map<String, String> vars = new HashMap<>();
	private Map<String, List<String>> lists = new HashMap<>();
	private Map<String, Map<String, String>> objects = new HashMap<>();
	private Map<String, String> scapeChars = new HashMap<>();

	private int currentLine = 0;
	private int delay = 0;
	private boolean stop = false;
	private boolean breakCursor = false;
	private boolean autoEraseOutput = true;

	/**
	 * Inicializar operadores
	 */
	static {
		ARITHMETIC_OPERATORS.add("+");
		ARITHMETIC_OPERATORS.add("-");
		ARITHMETIC_OPERATORS.add("*");
		ARITHMETIC_OPERATORS.add("/");
		ARITHMETIC_OPERATORS.add("%");
		LOGIC_OPERATORS.add("=");
		LOGIC_OPERATORS.add(">=");
		LOGIC_OPERATORS.add("<=");
		LOGIC_OPERATORS.add(">");
		LOGIC_OPERATORS.add("<");
		LOGIC_OPERATORS.add("!=");
	}

	/**
	 * Criar instância do Compilador VRBS
	 * 
	 * @param input
	 * @param output
	 */
	public VRBSCompiler(JTextComponent input, JTextComponent output) {
		super();
		this.input = input;
		this.output = output;
		registerNativeFunctions();
		registerNativeScapeChars();
	}
	
	/**
	 * Registrar Função no compilador para ser acessível a nível de usuário
	 * 
	 * @param function
	 */
	public void registerFunction(VRBSFunction function) {
		functions.put(function.getName(), function);
	}
	
	/**
	 * Registrar caracteres de scape nativos
	 */
	private void registerNativeScapeChars() {
		getScapeChars().put("%s","\n");
		getScapeChars().put("%a","\"");
		getScapeChars().put("%h","\'");
	}

	/**
	 * Registrar funções nativas no compilador
	 */
	private void registerNativeFunctions() {
		registerFunction(new Var(this));
		registerFunction(new Calc(this));
		registerFunction(new PrintLn(this));
		registerFunction(new Check(this));
		registerFunction(new Stop(this));
		registerFunction(new CompilerDelay(this));
		registerFunction(new Print(this));
		registerFunction(new Delay(this));
		registerFunction(new Input(this));
		registerFunction(new Label(this));
		registerFunction(new If(this));
		registerFunction(new Line(this));
		registerFunction(new Goto(this));
		registerFunction(new Msg(this));
		registerFunction(new Concat(this));
		registerFunction(new Clear(this));
		registerFunction(new ToInt(this));
		registerFunction(new ToFloat(this));
		registerFunction(new function.List(this));
		registerFunction(new Get(this));
		registerFunction(new Insert(this));
		registerFunction(new Inc(this));
		registerFunction(new Dec(this));
		registerFunction(new Size(this));
		registerFunction(new Exit(this));
		registerFunction(new Case(this));
		registerFunction(new Import(this));
		registerFunction(new Sin(this));
		registerFunction(new Cos(this));
		registerFunction(new IfElse(this));
		registerFunction(new Remove(this));
		registerFunction(new Length(this));
		registerFunction(new CharAt(this));
		registerFunction(new Show(this));
		registerFunction(new Hide(this));
		registerFunction(new FileRead(this));
		registerFunction(new FileWrite(this));
		registerFunction(new AsciiEncode(this));
		registerFunction(new AsciiDecode(this));
		registerFunction(new function.Random(this));
		registerFunction(new RandomInt(this));
		registerFunction(new RandomIntLimit(this));
		registerFunction(new EasterEgg(this));
		registerFunction(new Obj(this));
		registerFunction(new SetAttribute(this));
		registerFunction(new GetAttribute(this));
		registerFunction(new QtdVar(this));
		registerFunction(new QtdList(this));
		registerFunction(new QtdObj(this));
		registerFunction(new GetVarName(this));
		registerFunction(new GetListName(this));
		registerFunction(new GetObjName(this));
		registerFunction(new Break(this));
		registerFunction(new Pow(this));
		registerFunction(new Cmd(this));
	}

	/**
	 * Iniciar leitura do código e executar funções do script.
	 * 
	 * @throws VRBSException
	 *             Caso houver algum erro de compilação
	 */
	public void execute() throws VRBSException {
		
		//Apagar dados
		clearAll();
		if(autoEraseOutput) {
			this.output.setText("");
		}
		
		//Realizar leitura do script
		readCode(this.getInput().getText());
		
		updateCodeTable();
		this.currentLine = 0;
		this.stop = false;
		while (currentLine < codeSequence.size() && !stop) {
			
			//Atualizar linha da tabela de código
			getCodeTable().setRowSelectionInterval(this.currentLine, this.currentLine);
			
			//Extrair dados da função
			String functionStr = codeSequence.get(currentLine);
			String functionName = VRBSUtils.extractFunctionName(functionStr);
			String[] functionParameters = VRBSUtils.extractFunctionParameters(functionStr);

			// Validar registro da função
			if (!functions.containsKey(functionName)) {
				throw new VRBSException(String.format(VRBSMessages.UNDEFINED_FUNCTION,functionName, currentLine));
			}

			//Execute function
			this.currentLine++;
			VRBSFunction function = functions.get(functionName);
			function.execute(functionParameters);

			//Update Tables
			updateVarTable();
			updateListTable();
			updateObjectTable();

			// Delays
			try {
				while (breakCursor) {
					Thread.sleep(100);
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Apagar todos os dados do compilador (Variáveis, Listas e Objetos)
	 */
	public void clearAll() {
		clearVars();
		clearLists();
		clearObjects();
	}
	
	/**
	 * Apagar variáveis
	 */
	public void clearVars() {
		getVars().clear();
	}
	
	/**
	 * Apagar listas
	 */
	public void clearLists() {
		getLists().clear();
	}

	/**
	 * Apagar objetos
	 */
	public void clearObjects() {
		getObjects().clear();
	}

	/**
	 * Obter funções do código de entrada e processar funções de importação
	 * 
	 * @param code
	 * @throws VRBSException
	 *             Caso houver parâmetros incorrêtos em funções de importação
	 */
	private void readCode(String code) throws VRBSException {
		getCodeSequence().clear();
		code = replaceImports(code);
		Pattern functionPattern = Pattern.compile(this.functionRegexPattern);
		Matcher matcher = functionPattern.matcher(code);
		while (matcher.find()) {
			this.codeSequence.add(matcher.group(0));
		}
	}

	/**
	 * Processar funções de importação
	 * 
	 * @param code
	 * @return
	 * @throws VRBSException
	 */
	private String replaceImports(String code) throws VRBSException {
		Pattern importPattern = Pattern.compile(this.importRegexPattern);
		Matcher importMatcher = importPattern.matcher(code);
		while (importMatcher.find()) {
			VRBSFunction function = functions.get("import");
			function.execute(VRBSUtils.extractFunctionParameters(importMatcher.group(0)));
			code = code.replace(importMatcher.group(0), ((Import) function).getContent());
		}
		return code;
	}

	/**
	 * Atualizar tabela de código
	 */
	private void updateCodeTable() {
		DefaultTableModel dtm = (DefaultTableModel) getCodeTable().getModel();
		dtm.setRowCount(0);
		for (int i = 0; i < this.codeSequence.size(); i++) {
			dtm.addRow(new Object[] { i, codeSequence.get(i) });
		}
	}

	/**
	 * Atualizar tabela de variáveis
	 */
	private void updateVarTable() {
		DefaultTableModel dtm = (DefaultTableModel) getVarTable().getModel();
		dtm.setRowCount(0);
		for (Map.Entry<String, String> entry : getVars().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			dtm.addRow(new Object[] { key, value });
		}
	}

	/**
	 * Atualizar tabela de objetos
	 */
	private void updateObjectTable() {
		DefaultTableModel dtm = (DefaultTableModel) getObjTable().getModel();
		dtm.setRowCount(0);
		for (Map.Entry<String, Map<String, String>> entry : getObjects().entrySet()) {
			String key = entry.getKey();
			String content = "[";
			String glue = "";
			for (Map.Entry<String, String> entry2 : getObjects().get(key).entrySet()) {
				content += glue + " " + entry2.getKey() + ": " + entry2.getValue() + "";
				glue += ",";
			}
			content += "]";
			dtm.addRow(new Object[] { key, content });
		}
	}

	/**
	 * Atualizar tabela de listas
	 */
	private void updateListTable() {
		DefaultTableModel dtm = (DefaultTableModel) getListTable().getModel();
		dtm.setRowCount(0);
		for (Map.Entry<String, List<String>> entry : getLists().entrySet()) {
			String key = entry.getKey();
			List<String> value = entry.getValue();
			dtm.addRow(new Object[] { key, value.toString() });
		}
	}

	/**
	 * Retorna o próximo número de linha contendo o nome da função enviada por
	 * parâmetro
	 * 
	 * @param name
	 * @return Número da linha. Caso a função não for encontrada, retorna -1
	 */
	public int getNextFunctionName(String name) {
		for (int i = currentLine; i < codeSequence.size(); i++) {
			String functionStr = VRBSUtils.extractFunctionName(codeSequence.get(i));
			if (functionStr.equals(name)) {
				return currentLine + 1;
			}
		}
		return -1;
	}

	/**
	 * Retorna o próximo número de linha contendo o nome do label enviado por
	 * parâmetro
	 * 
	 * @param name
	 * @return Número da linha. Caso o label não for encontrado, retorna -1
	 */
	public int getLabelLine(String name) {
		for (int i = 0; i < this.codeSequence.size(); i++) {
			String functionStr = VRBSUtils.extractFunctionName(this.codeSequence.get(i));
			if (functionStr.equals("label")) {
				String par = VRBSUtils.extractFunctionParameters(this.codeSequence.get(i))[0];
				if (par.startsWith("\"") && par.endsWith("\"")) {
					par = par.replaceAll("\"", "");
					if (par.equals(name)) {
						return i;
					}
				} else {
					if (getVars().containsKey(par)) {
						par = getVars().get(par);
						if (par.equals(name)) {
							return i;
						}
					}

				}

			}
		}
		return -1;
	}

	/**
	 * Continuar lendo o código caso estiver em modo \"break\"
	 */
	public void continueCursor() {
		this.setBreakCursor(false);
	}
	
	/**
	 * Parar compilador
	 */
	public void stop() {
		this.stop = true;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @param delay
	 *            the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 * @return the objects
	 */
	public Map<String, Map<String, String>> getObjects() {
		return objects;
	}

	/**
	 * @param objects
	 *            the objects to set
	 */
	public void setObjects(Map<String, Map<String, String>> objects) {
		this.objects = objects;
	}

	/**
	 * @return the codeSequence
	 */
	public List<String> getCodeSequence() {
		return codeSequence;
	}

	/**
	 * @param codeSequence
	 *            the codeSequence to set
	 */
	public void setCodeSequence(List<String> codeSequence) {
		this.codeSequence = codeSequence;
	}

	/**
	 * @return the vars
	 */
	public Map<String, String> getVars() {
		return vars;
	}

	/**
	 * @param vars
	 *            the vars to set
	 */
	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}

	/**
	 * @return the currentLine
	 */
	public int getCurrentLine() {
		return currentLine;
	}

	/**
	 * @param currentLine
	 *            the currentLine to set
	 */
	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}

	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * @return the lists
	 */
	public Map<String, List<String>> getLists() {
		return lists;
	}

	/**
	 * @param lists
	 *            the lists to set
	 */
	public void setLists(Map<String, List<String>> lists) {
		this.lists = lists;
	}

	/**
	 * @return the breakk
	 */
	public boolean isBreakCursor() {
		return breakCursor;
	}

	/**
	 * @param breakk
	 *            the breakk to set
	 */
	public void setBreakCursor(boolean breakCursor) {
		this.breakCursor = breakCursor;
	}

	/**
	 * @return the input
	 */
	public JTextComponent getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(JTextComponent input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public JTextComponent getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(JTextComponent output) {
		this.output = output;
	}

	/**
	 * @return the codeTable
	 */
	public JTable getCodeTable() {
		return codeTable;
	}

	/**
	 * @param codeTable
	 *            the codeTable to set
	 */
	public void setCodeTable(JTable codeTable) {
		this.codeTable = codeTable;
	}

	/**
	 * @return the varTable
	 */
	public JTable getVarTable() {
		return varTable;
	}

	/**
	 * @param varTable
	 *            the varTable to set
	 */
	public void setVarTable(JTable varTable) {
		this.varTable = varTable;
	}

	/**
	 * @return the listTable
	 */
	public JTable getListTable() {
		return listTable;
	}

	/**
	 * @param listTable
	 *            the listTable to set
	 */
	public void setListTable(JTable listTable) {
		this.listTable = listTable;
	}

	/**
	 * @return the objTable
	 */
	public JTable getObjTable() {
		return objTable;
	}

	/**
	 * @param objTable
	 *            the objTable to set
	 */
	public void setObjTable(JTable objTable) {
		this.objTable = objTable;
	}

	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame
	 *            the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * @return the functions
	 */
	public Map<String, VRBSFunction> getFunctions() {
		return functions;
	}

	/**
	 * @param functions
	 *            the functions to set
	 */
	public void setFunctions(Map<String, VRBSFunction> functions) {
		this.functions = functions;
	}

	/**
	 * @return the functionPattern
	 */
	public String getFunctionRegexPattern() {
		return functionRegexPattern;
	}

	/**
	 * @param functionPattern
	 *            the functionPattern to set
	 */
	public void setFunctionRegexPattern(String functionPattern) {
		this.functionRegexPattern = functionPattern;
	}

	/**
	 * @return the importPattern
	 */
	public String getImportRegexPattern() {
		return importRegexPattern;
	}

	/**
	 * @param importPattern
	 *            the importPattern to set
	 */
	public void setImportRegexPattern(String importPattern) {
		this.importRegexPattern = importPattern;
	}

	/**
	 * @return the autoEraseOutput
	 */
	public boolean isAutoEraseOutput() {
		return autoEraseOutput;
	}

	/**
	 * @param autoEraseOutput the autoEraseOutput to set
	 */
	public void setAutoEraseOutput(boolean autoEraseOutput) {
		this.autoEraseOutput = autoEraseOutput;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * @return the scapeChars
	 */
	public Map<String, String> getScapeChars() {
		return scapeChars;
	}

	/**
	 * @param scapeChars the scapeChars to set
	 */
	public void setScapeChars(Map<String, String> scapeChars) {
		this.scapeChars = scapeChars;
	}
}
