package nl.gogognome.gogoimagemodifier;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ImageModifierTest {

	private ImageModifier imageModifier = new ImageModifier();
	
	private File outputDirectory = new File("target/testfiles");
	
	@Before
	public void createOutputDirectory() {
		if (!outputDirectory.exists()) {
			outputDirectory.mkdirs();
		}
	}
	
	@Test
	public void loadFile_saveFile() throws IOException {
		imageModifier.loadImage(getClass().getClassLoader().getResource("./testimage.jpg"));
		imageModifier.saveImage(new File(outputDirectory, "testoutput.jpg"), "JPG");
	}
	
	@Test
	public void loadFile_addHelloWorldPlain_saveFile() throws IOException {
		imageModifier.loadImage(getClass().getClassLoader().getResource("./testimage.jpg"));
		imageModifier.setColor(Color.CYAN);
		imageModifier.setPlainFont("Courier", 100);
		imageModifier.writeText("Hello World!", 100, 100);
		imageModifier.saveImage(new File(outputDirectory, "testoutput-helloworld-plain.jpg"), "JPG");
	}

	@Test
	public void loadFile_addHelloWorldBold_saveFile() throws IOException {
		imageModifier.loadImage(getClass().getClassLoader().getResource("./testimage.jpg"));
		imageModifier.setColor(Color.BLACK);
		imageModifier.setBoldFont("Courier", 100);
		imageModifier.writeText("Hello World!", 100, 100);
		imageModifier.saveImage(new File(outputDirectory, "testoutput-helloworld-bold.jpg"), "JPG");
	}

}
