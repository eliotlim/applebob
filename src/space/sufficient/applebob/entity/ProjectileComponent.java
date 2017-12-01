package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

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

        if (collisionCheck()) {
            int explosionSize = 5;
            Entity explosionEntity = new Entity(new Tile[explosionSize][explosionSize]);
            explosionEntity.setX(getEntity().getX() - explosionSize / 2);
            explosionEntity.setY(getEntity().getY() - explosionSize / 2);
            ExplosionComponent explosionComponent = new ExplosionComponent(getEntity(), explosionSize);
            explosionEntity.attachComponent(explosionComponent);
            // Spawn explosion
            getEntity().getWorld().addEntity(explosionEntity);

            // Despawn projectile
            getEntity().getWorld().removeEntity(getEntity());
        }
    }

    @Override
    protected void onAttachImpl(Entity entity) {

    }

    @Override
    protected void onDetachImpl() {

    }

    public boolean collisionCheck() {
        boolean collision = false;
        for (int r = 0; r < getEntity().getHeight(); r++) {
            for (int c = 0; c < getEntity().getWidth(); c++) {
                if (Tile.isSolid(getEntity().getWorld().getCell(getEntity().getX() + c, getEntity().getY() + r))) {
                    collision = true;
                }
            }
        }
        return collision;
    }
}
