package project;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import project.command.Command;

public class BatchProcessor {

	public static void main(String[] args) throws ProcessException {
		String path = getPath(args);

		if (path == null || path.isEmpty()) {
			System.out.println("batch file location is not provided");
			return;
		}
		File currentDirectory = new File(new File(".").getAbsolutePath());
		Batch batch = BatchParser.buildBatch(new File(currentDirectory, path));

		executeBatch(batch);
	}

	static void executeBatch(Batch batch) {
		for (Command cmd : batch.getCommands().values()) {
			try {
				cmd.execute(batch.getWorkingDir());
			} catch (ProcessException e) {
				System.out.println("exception while executing batch, reason: "
						+ e.getMessage());
			}
		}

	}

	private static String getPath(String[] args) {
		String path;
		if (args.length == 0) {
			System.out.println("Please enter the xml path : ");
			Scanner scanner = new Scanner(System.in);
			path = scanner.nextLine();
			scanner.close();
		} else {
			path = args[0];
		}
		return path;
	}

}
