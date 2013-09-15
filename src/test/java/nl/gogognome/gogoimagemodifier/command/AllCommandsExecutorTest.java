package nl.gogognome.gogoimagemodifier.command;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.awt.Color;

import nl.gogognome.gogoimagemodifier.ImageModifier;

import org.junit.Test;

public class AllCommandsExecutorTest {

	private final ImageModifier imageModifier = mock(ImageModifier.class);
	private final AllCommandsExecutor allCommandsExecutor = new AllCommandsExecutor();

	@Test
	public void shouldParseColorRGB() throws ExecutionException {
		assertTrue(allCommandsExecutor.execute(imageModifier, "color: 1, 2, 3"));
		verify(imageModifier).setColor(eq(new Color(1, 2, 3)));

		assertTrue(allCommandsExecutor.execute(imageModifier, "color:101,102,103"));
		verify(imageModifier).setColor(eq(new Color(101, 102, 103)));

		assertTrue(allCommandsExecutor.execute(imageModifier, "color:  201  ,  202  ,  203  "));
		verify(imageModifier).setColor(eq(new Color(201, 202, 203)));
	}

	@Test
	public void shouldParseColorRGBA() throws ExecutionException {
		assertTrue(allCommandsExecutor.execute(imageModifier, "color: 1, 2, 3, 4"));
		verify(imageModifier).setColor(eq(new Color(1, 2, 3, 4)));

		assertTrue(allCommandsExecutor.execute(imageModifier, "color:101,102,103,104"));
		verify(imageModifier).setColor(eq(new Color(101, 102, 103, 104)));

		assertTrue(allCommandsExecutor.execute(imageModifier, "color:  201  ,  202  ,  203  ,  204  "));
		verify(imageModifier).setColor(eq(new Color(201, 202, 203, 204)));
	}

	@Test
	public void shouldParsePlainFont() throws ExecutionException {
		assertTrue(allCommandsExecutor.execute(imageModifier, "font: Courier, 10"));
		verify(imageModifier).setPlainFont(eq("Courier"), eq(10));

		assertTrue(allCommandsExecutor.execute(imageModifier, "font: Courier Plain, 123"));
		verify(imageModifier).setPlainFont(eq("Courier Plain"), eq(123));

		assertTrue(allCommandsExecutor.execute(imageModifier, "font:Helvetica Plain,50"));
		verify(imageModifier).setPlainFont(eq("Helvetica Plain"), eq(50));
	}

	@Test
	public void shouldParseText() throws ExecutionException {
		assertTrue(allCommandsExecutor.execute(imageModifier, "text: 10, 20, Hello, World!"));
		verify(imageModifier).writeText(eq("Hello, World!"), eq(10), eq(20));

		assertTrue(allCommandsExecutor.execute(imageModifier, "text:30,40,Another message."));
		verify(imageModifier).writeText(eq("Another message."), eq(30), eq(40));

		assertTrue(allCommandsExecutor.execute(imageModifier, "text:  100 ,  200  ,  And another message.  "));
		verify(imageModifier).writeText(eq("And another message.  "), eq(100), eq(200));
	}

	@Test
	public void parseInvalidCommandShouldFail() throws ExecutionException {
		assertFalse(allCommandsExecutor.execute(imageModifier, "bla"));
	}

	@Test(expected = ExecutionException.class)
	public void parseCausingExceptionShouldFail() throws ExecutionException {
		assertFalse(allCommandsExecutor.execute(imageModifier, "text: 12345678901234567890, 0, Bla"));
	}
}
