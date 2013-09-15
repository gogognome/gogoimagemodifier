package nl.gogognome.gogoimagemodifier.command;

import nl.gogognome.gogoimagemodifier.ImageModifier;

public interface CommandExecutor {

	/**
	 * Parses the command and executes it on the ImageModifier.
	 * @param imageModifier the image modifier
	 * @param command the command
	 * @return true if the command could be parsed and executed; false otherwise
	 * @throws ExecutionException if a problem occurs while executing the command
	 */
	boolean execute(ImageModifier imageModifier, String command) throws ExecutionException;
}
