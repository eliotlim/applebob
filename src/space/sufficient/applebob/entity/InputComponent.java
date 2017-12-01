package space.sufficient.applebob.entity;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public abstract class InputComponent extends EntityComponent {
    public abstract void onInputEvent(InputEvent e);
}
