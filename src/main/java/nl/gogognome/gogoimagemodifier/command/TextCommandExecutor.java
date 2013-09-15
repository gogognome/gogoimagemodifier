package nl.gogognome.gogoimagemodifier.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.gogognome.gogoimagemodifier.ImageModifier;

public class TextCommandExecutor implements CommandExecutor {

	private final Pattern textPattern = Pattern.compile("text:\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(.*)");

	public boolean execute(ImageModifier imageModifier, String command) {
		Matcher matcher = textPattern.matcher(command);
		if (matcher.matches()) {
			imageModifier.writeText(matcher.group(3), Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
			return true;
		}
		return false;
	}

}
