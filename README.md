# Gogo Image Modifier
A tool to add text to an existing image. Can be useful to add a version number to a splash image during the build process.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/nl.gogognome/image-modifier-maven-plugin/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/nl.gogognome/image-modifier-maven-plugin)

## Typical usage

After adding the following snippet to your pom.xml, during compilation of your project the following happens:

- the file `splash.png` is read
- using the color (0, 0, 0) (black)
- and using the font Arial Bold with size 60 points
- at coordinates (40, 450) the text 'gogo account' followed by the project's version number is rendered
- the resulting image file is saved as `target/classes/splash.png`

```xml
<build>
    <plugins>
        <plugin>
            <groupId>nl.gogognome</groupId>
            <artifactId>image-modifier-maven-plugin</artifactId>
            <version>1.1.0</version>
            <executions>
                <execution>
                    <id>generateSplashImage</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>modifyImage</goal>
                    </goals>
                    <configuration>
                        <sourceImage>splash.png</sourceImage>
                        <destinationImage>target/classes/splash.png</destinationImage>
                        <commands>
                            <command>color: 0, 0, 0</command>
                            <command>font: Arial Bold, 60</command>
                            <command>text: 40, 450, gogo account ${project.version}</command>
                        </commands>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```                  
