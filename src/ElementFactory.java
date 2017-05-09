import sun.reflect.misc.ConstructorUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created on 11.04.2017 at 21:47 for usage in WireWorld.
 */
public class ElementFactory {

    public ElementFactory(){

    }

    public WireElement newElement(String type, int posX, int posY, int rotation) {
        try {
            return (WireElement) Class.forName(type).getConstructor(int.class, int.class, int.class).newInstance(posX, posY, rotation);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
