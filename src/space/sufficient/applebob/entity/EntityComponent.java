package space.sufficient.applebob.entity;

public abstract class EntityComponent {
    private Entity mEntity;

    protected abstract void onTickImpl();
    protected abstract void onAttachImpl(Entity entity);
    protected abstract void onDetachImpl();

    public final void onTick() {
        // TODO: Any globally-applicable state changes
        onTickImpl();
    }

    public final void onAttach(Entity entity) {
        this.mEntity = entity;
        onAttachImpl(entity);
    }

    public final void onDetach() {
        onDetachImpl();
    }

    public Entity getEntity() {
        return mEntity;
    }
}
