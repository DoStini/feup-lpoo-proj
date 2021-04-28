import model.util.Position;
import ui.Gui;
import ui.LanternaGui;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(30, 30);
        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
