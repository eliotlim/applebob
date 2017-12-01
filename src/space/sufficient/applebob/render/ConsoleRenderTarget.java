package space.sufficient.applebob.render;

import space.sufficient.applebob.world.World;

import java.io.PrintStream;

public class ConsoleRenderTarget extends RenderTarget {
    private int mViewportHeight, mViewportWidth;
    private PrintStream mTargetStream;

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
        for (int y = w.getCameraY() - mViewportHeight/2; y < w.getCameraY() + mViewportHeight/2; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = w.getCameraX() - mViewportWidth/2; x < w.getCameraX() + mViewportWidth/2; x++) {
                line.append(w.getCell(x, y));
            }
            mTargetStream.println(line);
        }
    }
}
