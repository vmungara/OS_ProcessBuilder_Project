package project.command;

import org.w3c.dom.Element;

import project.Batch;
import project.ProcessException;

public class WdCommand extends Command {

	private String path;

	public WdCommand() {
	}

	@Override
	public String describe() {
		return "The working directory will be set to "+path;
	}

	@Override
	public void execute(String workingDir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void parse(Element element) throws ProcessException {
		id = element.getAttribute("id");
		path = element.getAttribute("path");
		validateFields();
		
		//set Working dir in batch
		Batch batchObj = Batch.getInstance();
		batchObj.setWorkingDir(path);
	}

	private void validateFields() throws ProcessException {
		
		if(id == null || id.isEmpty()){
			throw new ProcessException("id is not provided for element: cmd");
		}
		
		if(path == null || path.isEmpty()){
			throw new ProcessException("path is not provided for element: cmd");
		}
	}
	
	public String getPath() {
		return path;
	}

	/*public void setPath(String path) {
		this.path = path;
	}*/

}
