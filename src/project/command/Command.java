/**
 * 
 */
package project.command;

import java.util.List;

import org.w3c.dom.Element;

import project.ProcessException;

/**
 * @author
 *
 */
public abstract class Command {

	protected String id;
	protected String path;
	
	public abstract String describe();

	public abstract void execute(String workingDir) throws ProcessException;

	public abstract void parse(Element element) throws ProcessException;

	public String getId() {
		return id;
	}

/*	public void setId(String id) {
		this.id = id;
	}

	public void setPath(String path) {
		this.path = path;
	}*/

	public String getPath() {
		return path;
	}


}
