package space.sufficient.applebob.input;

import space.sufficient.applebob.entity.InputComponent;
import space.sufficient.applebob.entity.PlayerControlComponent;

import java.awt.event.KeyEvent;

public class ConsoleInputManager extends InputManager  {

    public ConsoleInputManager() {
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        if (inputMap.containsKey(e.getKeyCode())) {
            for (InputComponent ic : inputMap.get(e.getKeyCode())) {
                ic.onInputEvent(e);
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        dispatchKeyEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        dispatchKeyEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        dispatchKeyEvent(e);
    }
}
