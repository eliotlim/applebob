package space.sufficient.applebob.render;

public class SimpleRenderer extends Renderer {
    private int mFps = 30;

    public SimpleRenderer(RenderTarget renderTarget) {
        super(renderTarget);
    }

    public void setFPS(int fps) {
        this.mFps = fps;
    }

    @Override
    public void run() {
        while (!stop) {
            getRenderTarget().draw(getWorld());
            try {
                Thread.sleep(1000L/ mFps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
