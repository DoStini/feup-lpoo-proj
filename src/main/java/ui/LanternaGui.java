package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import model.util.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGui implements Gui {

    TerminalScreen screen;
    TerminalSize terminalSize;

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("font.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 2);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    private TerminalScreen loadTerminal(int hSize, int vSize, AWTTerminalFontConfiguration fontConfig) throws IOException {
        terminalSize = new TerminalSize(vSize, hSize);
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        defaultTerminalFactory.setForceAWTOverSwing(true);
        defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

        Terminal terminal = defaultTerminalFactory.createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    public LanternaGui(int hSize, int vSize) {
        try {
            AWTTerminalFontConfiguration font = loadFont();
            screen = loadTerminal(hSize, vSize, font);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawColor(String color, Position pos) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), " ");
    }

    @Override
    public void drawLine(String color, Position pos, int width) {
        for (int i = 0; i < width; i++)
            drawColor(color, new Position(pos.getX() + i, pos.getY()));
    }

    @Override
    public void drawCharacter(String color, Character c, Position pos) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "" + c);
    }

    @Override
    public void refresh() {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        screen.clear();
    }
}