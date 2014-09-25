package project;

import java.util.LinkedHashMap;
import java.util.Map;

import project.command.Command;

public class Batch {

	private String workingDir;
	private Map<String, Command> commands;
	
	public Batch(){
		commands = new LinkedHashMap<String, Command>();
	}
	
	public void addCommand(Command command){
		commands.put(command.getId(), command);
	}
	
	public String getWorkingDir(){
		return workingDir;
	}
	
	public Map<String, Command> getCommands(){
		return commands;
	}
	
	
}
