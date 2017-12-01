package space.sufficient.applebob.render;

import space.sufficient.applebob.input.InputManager;
import space.sufficient.applebob.world.World;

public abstract class RenderTarget {
    public abstract void draw(World w);

    public abstract void setInputManager(InputManager inputManager);
}
