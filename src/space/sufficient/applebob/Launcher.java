package space.sufficient.applebob;

import space.sufficient.applebob.entity.*;
import space.sufficient.applebob.input.ConsoleInputManager;
import space.sufficient.applebob.input.InputManager;
import space.sufficient.applebob.render.*;
import space.sufficient.applebob.world.*;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Launcher {
    public static void main(String[] args) {

        World world = createRandomWorld();

        InputManager im = new ConsoleInputManager();
        InputManager.setInstance(im);

        Tile[][] testTile = {{Tile.HEAD}, {Tile.BODY}};
        Entity test = new Entity(testTile);
        {
            test.setX(world.getWidth() / 2 - 5);
            test.setY(world.getHeight() / 2);

            test.setProperty("isplayer", true);

            PlayerControlComponent testPlayerControlComponent = new PlayerControlComponent();
            test.attachComponent(testPlayerControlComponent);

            RigidCollisionComponent collisionComponent = new RigidCollisionComponent();
            test.attachComponent(collisionComponent);

            ShooterComponent shooterComponent = new ShooterComponent();
            test.attachComponent(shooterComponent);
            im.register(KeyEvent.VK_UP, testPlayerControlComponent);
            im.register(KeyEvent.VK_DOWN, testPlayerControlComponent);
            im.register(KeyEvent.VK_LEFT, testPlayerControlComponent);
            im.register(KeyEvent.VK_RIGHT, testPlayerControlComponent);
            im.register(KeyEvent.VK_ENTER, shooterComponent);

            test.setProperty("score", 0);

            world.addEntity(test);
        }

        Tile[][] testTile2 = {{Tile.HEAD}, {Tile.BODY2}};
        Entity test2 = new Entity(testTile2);
        {
            test2.setX(world.getWidth() / 2 + 5);
            test2.setY(world.getHeight() / 2);

            test2.setProperty("isplayer", true);

            Player2ControlComponent testPlayerControlComponent = new Player2ControlComponent();
            test2.attachComponent(testPlayerControlComponent);

            RigidCollisionComponent collisionComponent = new RigidCollisionComponent();
            test2.attachComponent(collisionComponent);

            Shooter2Component shooterComponent = new Shooter2Component();
            test2.attachComponent(shooterComponent);

            im.register(KeyEvent.VK_W, testPlayerControlComponent);
            im.register(KeyEvent.VK_A, testPlayerControlComponent);
            im.register(KeyEvent.VK_S, testPlayerControlComponent);
            im.register(KeyEvent.VK_D, testPlayerControlComponent);
            im.register(KeyEvent.VK_SPACE, shooterComponent);

            test2.setProperty("score", 0);

            world.addEntity(test2);
        }

        RenderTarget renderTarget = new HPCRenderTarget(150, 40);
        renderTarget.setInputManager(im);
        SimpleRenderer renderer = new SimpleRenderer(renderTarget);
        renderer.setFPS(30);
        renderer.attach(world);

        Thread renderThread = new Thread(renderer);
        renderThread.start();
    }

    public static World createRandomWorld() {
        World world = new ArrayWorld(80, 30);

        // Populate the world with random blocks
        Random rand = new Random();
        for (int r = 0; r < world.getHeight(); r++) {
            for (int c = 0; c < world.getWidth(); c++) {
                Tile t;
                switch (rand.nextInt(10)) {
                    case 0:
                        t = Tile.WALL;
                        break;
                    case 1:
                        t = Tile.FLOOR;
                        break;
                    default:
                        t = Tile.FLOOR;
                        break;
                }
                world.setCell(c, r, t);
            }
        }
        // Clear the center area
        for (int r = 0; r < world.getHeight(); r++) {
            for (int c = 0; c < world.getWidth(); c++) {
                if (Math.abs(r - world.getHeight() / 2) < 10 &&
                        Math.abs(c - world.getWidth() / 2) < 10)
                    world.setCell(c, r, Tile.FLOOR);
            }
        }
        return world;
    }
}
