package nl.gogognome.gogoimagemodifier.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.gogognome.gogoimagemodifier.ImageModifier;

public class FontCommandExecutor implements CommandExecutor {

	private final Pattern fontPattern = Pattern.compile("font:\\s*([^,]+)\\s*,\\s*(\\d+)\\s*");

	public boolean execute(ImageModifier imageModifier, String command) {
		Matcher matcher = fontPattern.matcher(command);
		if (matcher.matches()) {
			imageModifier.setPlainFont(matcher.group(1), Integer.parseInt(matcher.group(2)));
			return true;
		}
		return false;
	}

}
