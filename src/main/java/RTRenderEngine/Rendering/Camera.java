package RTRenderEngine.Rendering;

import RTRenderEngine.Math.Vector3;
import javafx.scene.PerspectiveCamera;

public class Camera {
    private PerspectiveCamera camera;
    private float translateX;
    private float translateY;
    private float translateZ;

    public Camera(PerspectiveCamera camera, float translateX, float translateY, float translateZ){
        this.camera = camera;
        this.translateX = translateX;
        this.translateY = translateY;
        this.translateZ = translateZ;
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public PerspectiveCamera setCamera(){

    }
}
