package project.command;

import java.util.List;

import org.w3c.dom.Element;

import project.ProcessException;

public class CmdCommand extends Command {

	private String path;
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

	/*public void setArgs(String args) {
		this.args = args;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public void setOut(String out) {
		this.out = out;
	}*/

	public CmdCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String describe() {
		return ("executing command "+path);
	}

	@Override
	public void execute(String workingDir) {
		

	}

	@Override
	public void parse(Element element) throws ProcessException {
		id = element.getAttribute("id");
		path = element.getAttribute("path");
		args = element.getAttribute("args");
		in = element.getAttribute("in");
		out = element.getAttribute("out");
		validateFields();
	}
	
	private void validateFields() throws ProcessException {
		
		if(id != null && !id.isEmpty()){
			throw new ProcessException("id is not provided for element: cmd");
		}
		
		if(path != null && !path.isEmpty()){
			throw new ProcessException("path is not provided for element: cmd");
		}
		
		if(args != null && !args.isEmpty()){
			throw new ProcessException("args is not provided for element: cmd");
		}
		
		if(in != null && !in.isEmpty()){
			throw new ProcessException("in is not provided for element: cmd");
		}
		
		if(out != null && !out.isEmpty()){
			throw new ProcessException("out is not provided for element: cmd");
		}
		
	}
	
}
