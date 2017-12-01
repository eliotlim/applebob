package space.sufficient.applebob;

import space.sufficient.applebob.entity.Entity;
import space.sufficient.applebob.entity.PlayerControlComponent;
import space.sufficient.applebob.entity.ShooterComponent;
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
        PlayerControlComponent testPlayerControlComponent = new PlayerControlComponent();
        test.attachComponent(testPlayerControlComponent);
        ShooterComponent shooterComponent = new ShooterComponent();
        test.attachComponent(shooterComponent);


        InputManager im = new ConsoleInputManager();
        InputManager.setInstance(im);
        im.register(KeyEvent.VK_UP, testPlayerControlComponent);
        im.register(KeyEvent.VK_DOWN, testPlayerControlComponent);
        im.register(KeyEvent.VK_LEFT, testPlayerControlComponent);
        im.register(KeyEvent.VK_RIGHT, testPlayerControlComponent);
        im.register(KeyEvent.VK_1, shooterComponent);

        world.addEntity(test);
        world.setFocus(test);

        RenderTarget renderTarget = new HPCRenderTarget(150, 40);
        renderTarget.setInputManager(im);
        SimpleRenderer renderer = new SimpleRenderer(renderTarget);
        renderer.setFPS(30);
        renderer.attach(world);

        Thread renderThread = new Thread(renderer);
        renderThread.start();
    }
}
