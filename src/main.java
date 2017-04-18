import java.awt.*;

public class main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Gui app = new Gui();
            app.setVisible(true);
        });

    }
}
