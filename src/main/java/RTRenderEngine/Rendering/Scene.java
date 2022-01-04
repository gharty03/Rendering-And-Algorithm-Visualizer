package RTRenderEngine.Rendering;

import RTRenderEngine.Solids.Solid;

import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    private Camera camera;
    private Light light;
    private CopyOnWriteArrayList<Solid> solids;
}
