package space.sufficient.applebob;

import space.sufficient.applebob.render.*;
import space.sufficient.applebob.world.*;

import java.util.Random;

public class Launcher {
    public static void main(String[] args) {
        World world = new ArrayWorld(20, 10);

        Random rand = new Random(1000);
        for (int r = 0; r < world.getHeight(); r++) {
            for (int c = 0; c < world.getWidth(); c++) {
                Tile t;
                switch(rand.nextInt( 5)) {
                    case 0:
                        t = Tile.WALL;
                        break;
                    case 1:
                        t = Tile.VOID;
                        break;
                    default:
                        t = Tile.FLOOR;
                        break;
                }
                world.setCell(c, r, t);
            }
        }

        RenderTarget renderTarget = new ConsoleRenderTarget(200, 60, System.out);
        Renderer renderer = new SimpleRenderer(renderTarget);
        renderer.attach(world);

        Thread renderThread = new Thread(renderer);
        renderThread.start();
    }
}
