package project;

import java.io.File;

import project.command.Command;

public class BatchProcessor {

	public static void main(String[] args) {

		try {
			String path = getPath(args);
			Batch batch = BatchParser.buildBatch(new File(path));
			executeBatch(batch);
			
		} catch (ProcessException e) {
			System.out.println("Exception while executing batch, reason: "
					+ e.getMessage());
		}
		System.out.println("Process Terminated");
	}

	private static void executeBatch(Batch batch) throws ProcessException {
		for (Command cmd : batch.getCommands().values()) {
			cmd.execute(batch.getWorkingDir());
		}

	}

	private static String getPath(String[] args) throws ProcessException {
		if (args.length == 0) {
			throw new ProcessException("batch file location is not provided");
		} else {
			return args[0];
		}
	}

}
