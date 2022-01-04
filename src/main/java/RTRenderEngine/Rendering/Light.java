package RTRenderEngine.Rendering;

import RTRenderEngine.Math.Vector3;

public class Light {
    private Vector3 position;

    public Light(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
