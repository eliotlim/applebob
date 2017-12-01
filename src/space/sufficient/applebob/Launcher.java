package space.sufficient.applebob;

import space.sufficient.applebob.entity.Entity;
import space.sufficient.applebob.entity.InputComponent;
import space.sufficient.applebob.input.ConsoleInputManager;
import space.sufficient.applebob.input.InputManager;
import space.sufficient.applebob.render.*;
import space.sufficient.applebob.world.*;

import java.awt.event.KeyEvent;
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

        Tile[][] testTile = {{Tile.HEAD}, {Tile.BODY}};
        Entity test = new Entity(testTile);
        test.setX(world.getHeight()/2);
        test.setY(world.getHeight()/2);
        InputComponent testInputComponent = new InputComponent();
        test.attachComponent(testInputComponent);


        InputManager im = new ConsoleInputManager();
        im.attach();
        InputManager.setInstance(im);
        im.register(KeyEvent.VK_W, testInputComponent);

        world.addEntity(test);

        RenderTarget renderTarget = new HPCRenderTarget(200, 60);
        SimpleRenderer renderer = new SimpleRenderer(renderTarget);
        renderer.setFPS(3);
        renderer.attach(world);

        Thread renderThread = new Thread(renderer);
        renderThread.start();
    }
}
