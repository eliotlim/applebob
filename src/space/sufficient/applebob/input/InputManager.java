package space.sufficient.applebob.input;

import space.sufficient.applebob.entity.InputComponent;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class InputManager implements KeyListener {
    private static InputManager sInstance;

    protected HashMap<Integer, List<InputComponent>> inputMap = new HashMap<>();

    public static void setInstance(InputManager im) {
        sInstance = im;
    }

    public static InputManager getsInstance() {
        return sInstance;
    }


    public void register(Integer keyCode, InputComponent inComponent) {
        if (inputMap.containsKey(keyCode)) inputMap.get(keyCode).add(inComponent);
        else {
            inputMap.put(keyCode, new LinkedList<>());
            inputMap.get(keyCode).add(inComponent);
        }
    }

    public void deregister(Integer keyCode, InputComponent inComponent) {
        if (inputMap.containsKey(keyCode)) inputMap.get(keyCode).remove(inComponent);
    }

}
