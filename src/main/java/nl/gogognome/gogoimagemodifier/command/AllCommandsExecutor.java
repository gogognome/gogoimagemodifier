package nl.gogognome.gogoimagemodifier.command;

import java.util.ArrayList;
import java.util.List;

import nl.gogognome.gogoimagemodifier.ImageModifier;

public class AllCommandsExecutor implements CommandExecutor {

	private final static List<Class<? extends CommandExecutor>> COMMAND_CLASSES = new ArrayList<Class<? extends CommandExecutor>>();

	static {
		COMMAND_CLASSES.add(ColorCommandExecutor.class);
		COMMAND_CLASSES.add(FontCommandExecutor.class);
		COMMAND_CLASSES.add(TextCommandExecutor.class);
	}

	public boolean execute(ImageModifier imageModifier, String command) throws ExecutionException {
		for (Class<? extends CommandExecutor> clazz : COMMAND_CLASSES) {
			CommandExecutor commandExecutor;
			try {
				commandExecutor = clazz.newInstance();
			} catch (Exception e) {
				throw new ExecutionException("Failed to instantiate or execute command " + clazz.getName());
			}
			try {
				if (commandExecutor.execute(imageModifier, command))
					return true;
			} catch (Exception e) {
				throw new ExecutionException("Could not execute command", e);
			}
		}

		return false;
	}
}
