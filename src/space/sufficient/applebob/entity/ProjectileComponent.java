package space.sufficient.applebob.entity;

public class ProjectileComponent extends EntityComponent {

    private int mVelX = 0, mVelY = 0;

    ProjectileComponent(int velX, int velY) {
        this.mVelX = velX;
        this.mVelY = velY;
    }

    @Override
    protected void onTickImpl() {
        getEntity().setX(getEntity().getX()+ mVelX);
        getEntity().setY(getEntity().getY()+ mVelY);
    }

    @Override
    protected void onAttachImpl(Entity entity) {

    }

    @Override
    protected void onDetachImpl() {

    }
}
