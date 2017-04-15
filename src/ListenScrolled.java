import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ListenScrolled implements ChangeListener {
    private Canvas canvas;
    public  ListenScrolled(Canvas can){
        canvas = can;
    }
    public void stateChanged(ChangeEvent e){
        canvas.repaint();
    }
}
