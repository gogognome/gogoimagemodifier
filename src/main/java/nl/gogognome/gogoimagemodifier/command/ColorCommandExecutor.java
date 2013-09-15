package nl.gogognome.gogoimagemodifier.command;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.gogognome.gogoimagemodifier.ImageModifier;

public class ColorCommandExecutor implements CommandExecutor {

	private final Pattern colorRGBAPattern = Pattern.compile("color:\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*");

	private final Pattern colorRGBPattern = Pattern.compile("color:\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*");

	public boolean execute(ImageModifier imageModifier, String command) {
		Matcher matcher = colorRGBAPattern.matcher(command);
		if (matcher.matches()) {
			imageModifier.setColor(new Color(Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
			return true;
		}
		matcher = colorRGBPattern.matcher(command);
		if (matcher.matches()) {
			imageModifier.setColor(new Color(Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
			return true;
		}

		return false;
	}

}
