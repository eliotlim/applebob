package space.sufficient.applebob.world;

import space.sufficient.applebob.entity.Entity;

public interface IGameWorld {
    void setFocus(Entity focus);
    Entity getFocus();
}
