package space.sufficient.applebob.entity;

import space.sufficient.applebob.world.Tile;

public class ExplosionComponent extends EntityComponent {

    private int mRadius;
    private static int sDuration = 10;
    private int mDuration = 0;

    public ExplosionComponent(int diameter) {
        mRadius = diameter;
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

        if (mDuration > sDuration) {
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
