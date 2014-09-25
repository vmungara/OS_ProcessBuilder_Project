package project.command;

import java.io.File;

import org.w3c.dom.Element;

import project.ProcessException;

public class FileCommand extends Command {

	private String path;

	public String getPath() {
		return path;
	}

	/*
	 * public void setPath(String path) { this.path = path; }
	 */

	public FileCommand() {

	}

	@Override
	public String describe() {
		return "File Command on file: " + path;
	}

	@Override
	public void execute(String workingDir) {
		// check if file exists?
		/*File file = new File(workingDir, path);
		
		if (!file.exists()) {
			file.createNewFile();
		}*/
	}

	@Override
	public void parse(Element element) throws ProcessException {
		id = element.getAttribute("id");
		path = element.getAttribute("path");
		validateFields();
	}

	private void validateFields() throws ProcessException {

		if (id != null && !id.isEmpty()) {
			throw new ProcessException("id is not provided for element: cmd");
		}

		if (path != null && !path.isEmpty()) {
			throw new ProcessException("path is not provided for element: cmd");
		}
	}

}
