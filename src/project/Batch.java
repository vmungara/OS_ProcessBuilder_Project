package project;

import java.util.LinkedHashMap;
import java.util.Map;

import project.command.CmdCommand;
import project.command.Command;
import project.command.WdCommand;

public class Batch {

	private String workingDir;
	private Map<String, Command> commands;
	private static Batch batch; 
	
	public static Batch getInstance(){
		if(batch == null){
			batch = new Batch();
		}
		return batch;
	}
	
	private Batch(){
		commands = new LinkedHashMap<String, Command>();
	}
	
	public void addCommand(Command command){
		/*if(command instanceof WdCommand){
			workingDir = ((WdCommand) command).getPath();
		} else if(command instanceof CmdCommand) {
			CmdCommand cmd = (CmdCommand)command;
			cmd.se
		}*/
		commands.put(command.getId(), command);
	}
	
	public String getWorkingDir(){
		return workingDir;
	}
	
	public void setWorkingDir(String dir){
		workingDir = dir;
	}
	
	public Map<String, Command> getCommands(){
		return commands;
	}
	
	
}
