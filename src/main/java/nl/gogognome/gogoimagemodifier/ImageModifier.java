package nl.gogognome.gogoimagemodifier;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageModifier {

	private BufferedImage image;
	private Graphics g;
	
	public void loadImage(URL url) throws IOException {
		 image = ImageIO.read(url);
		 g = image.getGraphics();
	}

	public void loadImage(File file) throws IOException {
		 image = ImageIO.read(file);
		 g = image.getGraphics();
	}

	public void saveImage(File outputFile, String formatName) throws IOException {
		ImageIO.write(image, formatName, outputFile);
	}

	public void writeText(String text, int x, int y) {
		g.drawString(text, x, y);
	}
	
	public void setColor(Color color) {
		g.setColor(color);
	}
	
	public void setFontSize(float size) {
		g.setFont(g.getFont().deriveFont(size));
	}
	
	public void setPlainFont(String name, int size) {
		g.setFont(new Font(name, Font.PLAIN, size));
	}
	
	public void setBoldFont(String name, int size) {
		g.setFont(new Font(name, Font.BOLD, size));
	}

}
