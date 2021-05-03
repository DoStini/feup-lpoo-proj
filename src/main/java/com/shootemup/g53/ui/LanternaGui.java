package com.shootemup.g53.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.shootemup.g53.controller.Action;
import com.shootemup.g53.model.util.Position;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGui implements Gui {

    TerminalScreen screen;
    TerminalSize terminalSize;
    private TextGraphics graphics;

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("font.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 10);
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
            graphics = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Action readInput() {
        try {
            KeyStroke key = screen.pollInput();

            if (key == null)
                return  Action.NONE;

            if (key.getKeyType() == KeyType.Escape)
                return  Action.ESC;

            if (key.getKeyType() == KeyType.Character) {
                switch (key.getCharacter()) {
                    case 'w':
                        return Action.W;
                    case 'a':
                        return Action.A;
                    case 's':
                        return Action.S;
                    case 'd':
                        return Action.D;
                    case 'f':
                        return Action.FIRE;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Action.NONE;
    }

    @Override
    public void drawColor(String color, Position pos) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.setCharacter(new TerminalPosition(pos.getX(), pos.getY()), ' ');
    }

    @Override
    public void drawLine(String color, Position pos, int width) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.drawLine(new TerminalPosition(pos.getX(), pos.getY()), new TerminalPosition(pos.getX() + width, pos.getY()), ' ');
    }

    @Override
    public void drawCharacter(String color, Character c, Position pos) {
        graphics.setCharacter(new TerminalPosition(pos.getX(), pos.getY()), c);
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
