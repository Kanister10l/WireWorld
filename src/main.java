import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {


        EventQueue.invokeLater(() -> {
            Gui app = new Gui();
            app.setVisible(true);
        });

    }
}
