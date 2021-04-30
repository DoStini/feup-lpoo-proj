import model.util.Position;
import ui.Gui;
import ui.LanternaGui;
import view.element.ElementViewer;
import view.element.ExampleViewer;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(50,50);
        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
