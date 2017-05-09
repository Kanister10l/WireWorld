/**
 * Created on 16.04.2017 at 20:15 for usage in WireWorld.
 */
public class Selectable {
    private static Selectable instance = null;
    private String type = "";
    private int rotation = 0;

    public Selectable() {
    }

    public static Selectable getInstance() {
        if (instance == null) {
            instance = new Selectable();
        }
        return instance;
    }

    public void select(String type) {
        this.type = type;
    }

    public void deselect() {
        type = "";
        rotation = 0;
    }

    public String getSelected() {
        return type;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
