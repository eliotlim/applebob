package space.sufficient.applebob.render;

import space.sufficient.applebob.world.World;

public abstract class Renderer implements IRenderer, Runnable {
    private RenderTarget mRenderTarget;
    protected boolean stop = false;
    private World mWorld;

    Renderer(RenderTarget renderTarget) {
        this.mRenderTarget = renderTarget;
    }

    public RenderTarget getRenderTarget() {
        return mRenderTarget;
    }

    @Override
    public void attach(World w) {
        this.mWorld = w;
    }

    public World getWorld() {
        return mWorld;
    }

    @Override
    public abstract void run() ;
}
