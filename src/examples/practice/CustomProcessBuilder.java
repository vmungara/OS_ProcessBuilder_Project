package examples.practice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of using a JavaSE 7 ProcessBuilder to execute a command that reads
 * from a file (randomwords.txt) and write the command's output to a file
 * (sortedwords.txt).
 */
public class CustomProcessBuilder {
	public static void main(String args[]) throws Exception {
		
		//command: java.exe -jar addLines.jar numberdata.txt 2.txt
		List<String> command = new ArrayList<String>();
		/*command.add("java.exe");
		command.add("-jar addLines.jar");
		command.add("numberdata.txt");
		command.add("2.txt");*/
		command.add("CMD");
		command.add("/C");
		command.add("dir");
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.command(command);
		
		File wd = new File(
				"D:\\drive\\1st Sem\\Operating systems\\Projects\\batchProject\\test");
		
		builder.directory(wd);
		File error = new File(wd, "error.txt");
		if(!error.exists())
			error.createNewFile();
		
		builder.redirectError(error);
		
		
		File output = new File(wd, "ouput.txt");
		if(!output.exists())
			output.createNewFile();
		
		builder.redirectOutput(output);

		Process process = builder.start();
		process.waitFor();

		System.out.println("Program terminated!");
	}
}
