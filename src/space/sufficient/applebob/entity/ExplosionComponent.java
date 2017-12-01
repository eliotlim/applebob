package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

import java.util.Iterator;

public class ExplosionComponent extends EntityComponent {

    private int mRadius;
    private static int sDuration = 10;
    private int mDuration = 0;
    private Entity mFirer;

    public ExplosionComponent(Entity firer, int diameter) {
        mRadius = diameter;
        mFirer = firer;
    }


    @Override
    protected void onTickImpl() {
        mDuration++;

        Tile[][] repr = new Tile[mRadius + 1][mRadius + 1];

        for (int r = 0; r < mRadius + 1; r++) {
            for (int c = 0; c < mRadius + 1; c++) {
                int stage = explosionIntensity((int) Math.sqrt(r*r+c*c), mDuration);
                repr[r][c] = explosionStage(stage);
            }
        }
        getEntity().setRepresentation(repr);

        int x = getEntity().getX();
        int y = getEntity().getY();
        int w = getEntity().getWidth();
        int h = getEntity().getHeight();

        for (Iterator iter = getEntity().getWorld().getmEntities().iterator(); iter.hasNext();){
            Entity bob = (Entity) iter.next();
            if (!bob.isPlayer){
                continue;
            }
            if (mFirer != bob) {
                if (bob.getX() >= x && bob.getX() < x + w && bob.getY() >= y && bob.getY() < y + h) {
                    getEntity().getWorld().score1++;
                }
            }

        }

        if (mDuration > sDuration) {


            for (int i = x; i< x+w; i++){
                for (int j = y; j< y+h; j++){
                    getEntity().getWorld().setCell(i,j,Tile.FLOOR);
                }
            }
            getEntity().getWorld().removeEntity(getEntity());
        }
    }

    @Override
    protected void onAttachImpl(Entity entity) {

    }

    @Override
    protected void onDetachImpl() {

    }

    private static int explosionIntensity(int radius, int duration) {
        float intensity = radius == 0 ? 4 : 2.0f / radius;
        int stage = (int) Math.min(duration * 0.4f, (sDuration - duration) * 0.4f);
        return stage;
    }

    private static Tile explosionStage(int stage) {
        switch(stage) {
            case 0:
                return Tile.EXP_1;
            case 1:
                return Tile.EXP_2;
            case 2:
                return Tile.EXP_3;
            case 3:
                return Tile.EXP_4;
            default:
                return Tile.EXP_5;
        }

    }
}
