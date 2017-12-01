package space.sufficient.applebob.render;

import space.sufficient.applebob.world.World;

import java.io.PrintStream;

public class ConsoleRenderTarget extends RenderTarget {
    private int mViewportHeight, mViewportWidth;
    private PrintStream mTargetStream;
    private long mFpsTime;

    private ConsoleRenderTarget() {

    }

    public ConsoleRenderTarget(int width, int height, PrintStream target) {
        this.mViewportWidth = width;
        this.mViewportHeight = height;
        this.mTargetStream = target;
    }

    @Override
    public void draw(World w) {
        // Draw the world row by row, centered at cameraY and cameraX.
        System.out.println("\033[H\033[2J");
        System.out.flush();
        w.onTick();
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = w.getCameraX() - mViewportWidth/2; x < w.getCameraX() + mViewportWidth/2; x++) {
                line.append(w.renderCell(x, y));
            }
            mTargetStream.println(line);
        }
        long frameDuration = (System.currentTimeMillis() - mFpsTime);
        System.out.println("Took " + frameDuration + "ms - drawing at " + (1000L / frameDuration) );
        mFpsTime = System.currentTimeMillis();
    }
}
