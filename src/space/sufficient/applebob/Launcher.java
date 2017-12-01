package space.sufficient.applebob;

import space.sufficient.applebob.render.ConsoleRenderTarget;
import space.sufficient.applebob.render.RenderTarget;
import space.sufficient.applebob.render.Renderer;
import space.sufficient.applebob.render.SimpleRenderer;
import space.sufficient.applebob.world.ArrayWorld;
import space.sufficient.applebob.world.World;

public class Launcher {
    public static void main(String[] args) {
        World world = new ArrayWorld(20, 10);
        RenderTarget renderTarget = new ConsoleRenderTarget(20, 12, System.out);
        Renderer renderer = new SimpleRenderer(renderTarget);
        renderer.attach(world);
        Thread renderThread = new Thread(renderer);
        renderThread.start();

    }
}
