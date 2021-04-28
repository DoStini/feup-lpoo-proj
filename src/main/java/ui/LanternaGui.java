package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.util.Position;

import java.io.IOException;

public class LanternaGui implements Gui {

    TerminalScreen screen;
    TerminalSize terminalSize;

    public LanternaGui(int hSize, int vSize) {
        try {
            terminalSize = new TerminalSize(vSize, hSize);
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            Terminal terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
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
    public void drawText(String color, String text, Position pos) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), text);
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
