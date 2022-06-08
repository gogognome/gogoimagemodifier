package nl.gogognome.gogoimagemodifier;

import java.io.File;
import java.io.IOException;

import nl.gogognome.gogoimagemodifier.command.AllCommandsExecutor;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mojo(name = "modifyImage", defaultPhase = LifecyclePhase.COMPILE, requiresProject = false)
public class ImageModifierMavenPlugin extends AbstractMojo {

    private static final Logger LOG = LoggerFactory.getLogger(ImageModifierMavenPlugin.class);

    private final ImageModifier imageModifier = new ImageModifier();

    /**
     * The source image file.
     */
    @Parameter(required = true)
    private File sourceImage;

    /**
     * The destination image file.
     */
    @Parameter(required = true)
    private File destinationImage;

    /**
     * A list of commands to modify the image.
     */
    @Parameter(required = true)
    private String[] commands;

    public void execute() throws MojoExecutionException, MojoFailureException {
        validateParameters();
        try {
            imageModifier.loadImage(sourceImage);
            LOG.info("Image loaded " + String.valueOf(sourceImage));
        } catch (IOException e) {
            throw new MojoFailureException("Failed to load source image: " + e.getMessage());
        }

        for (String command : commands) {
            executeCommand(command);
        }

        try {
            if(!imageModifier.saveImage(destinationImage, getFormatName())) {
                LOG.warn("Fail to save image, retrying without alpha");
                imageModifier.removeAlpha();
                if(!imageModifier.saveImage(destinationImage, getFormatName())) {
                    throw new MojoFailureException("Failed to save destination image: format is not supported");    
                }
            }
            LOG.info("Image saved " + String.valueOf(destinationImage));
        } catch (IOException e) {
            throw new MojoFailureException("Failed to save destination image: " + e.getMessage());
        }
    }

    private void executeCommand(String command) throws MojoFailureException {
        try {
            LOG.debug("Executing " + String.valueOf(command));
            boolean executed = new AllCommandsExecutor().execute(imageModifier, command);
            LOG.debug("Executed " + String.valueOf(command));
            if (!executed)
                throw new MojoFailureException("The command \"" + command + "\" could not be executed.");
        } catch (Exception e) {
            throw new MojoFailureException("The command \"" + command + "\" could not be executed: " + e.getMessage());
        }
    }

    private void validateParameters() throws MojoFailureException {
        if (sourceImage == null)
            throw new MojoFailureException("sourceImage was not set.");

        if (destinationImage == null)
            throw new MojoFailureException("destinationImage was not set.");
    }

    private String getFormatName() throws MojoFailureException {
        String name = destinationImage.getName();
        if (name.length() < 3)
            throw new MojoFailureException("File name of destinationImage is smaller than 3 characters. " +
                    "It must have an extension of .JPG, .GIF or .PNG");

        return name.substring(name.length() - 3).toUpperCase();
    }

}
