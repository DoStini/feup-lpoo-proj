import model.util.Position;
import ui.Gui;
import ui.LanternaGui;
import view.element.ElementViewer;
import view.element.ExampleViewer;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(20,20);
        gui.drawColor("#adadad", new Position(10, 5));
        gui.drawCharacter("#adadad", 'c', new Position(11, 5));
        ElementViewer elem = new ExampleViewer();
        elem.draw(gui);
        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
