package space.sufficient.applebob.input;

import space.sufficient.applebob.entity.InputComponent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ConsoleInputManager extends InputManager implements KeyEventDispatcher {

    public ConsoleInputManager() {
    }

    public void attach() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public void detach() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (inputMap.containsKey(e)) {
            for (InputComponent ic : inputMap.get(e.getKeyCode())) {
                ic.onInputEvent(e);
            }
        }
        return false;
    }
}
