package project.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;

import project.Batch;
import project.ProcessException;

public class CmdCommand extends Command {

	private String args;
	private String in;
	private String out;

	public String getPath() {
		return path;
	}

	/*
	 * public void setPath(String path) { this.path = path; }
	 */

	public String getArgs() {
		return args;
	}

	public String getIn() {
		return in;
	}

	public String getOut() {
		return out;
	}

	/*
	 * public void setArgs(String args) { this.args = args; }
	 * 
	 * public void setIn(String in) { this.in = in; }
	 * 
	 * public void setOut(String out) { this.out = out; }
	 */

	public CmdCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String describe() {
		return ("executing command " + path);
	}

	@Override
	public void execute(String workingDir) throws ProcessException {
		List<String> command = new ArrayList<String>();
		command.add(path);
		for(String arg: args.split(" "))
			command.add(arg);
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.directory(new File(workingDir));
		
		//redirect input, output and error 
		if (!in.isEmpty()) {
			builder.redirectInput(new File(workingDir, in));
		}
		builder.redirectOutput(new File(workingDir, out));

		File errorFile = new File(workingDir, "error.txt");
		/*if (!errorFile.exists()) {
			try {
				errorFile.createNewFile();
			} catch (IOException e) {
				throw new ProcessException("cannot create error file " + errorFile);
			}
		}*/
		
		builder.redirectError(errorFile);
		
		try {
			builder.start();
		} catch (IOException e) {
			throw new ProcessException("error executing cmd "+path + e.getMessage());
		}
	}

	@Override
	public void parse(Element element) throws ProcessException {
		id = element.getAttribute("id");
		path = element.getAttribute("path");
		args = element.getAttribute("args");
		in = element.getAttribute("in");
		out = element.getAttribute("out");
		validateFields();

		// map input and output files
		Batch batchObj = Batch.getInstance();
		Set<String> keySet = batchObj.getCommands().keySet();
		for (String key : keySet) {
			if (key.equalsIgnoreCase(in)) {
				in = batchObj.getCommands().get(key).getPath();
			} else if (key.equalsIgnoreCase(out)) {
				out = batchObj.getCommands().get(key).getPath();
			}
		}
	}

	private void validateFields() throws ProcessException {

		if (id == null || id.isEmpty()) {
			throw new ProcessException("id is not provided for element: cmd");
		}

		if (path == null || path.isEmpty()) {
			throw new ProcessException("path is not provided for element: cmd");
		}

		if (out == null || out.isEmpty()) {
			throw new ProcessException("out is not provided for element: cmd");
		}

	}

}
