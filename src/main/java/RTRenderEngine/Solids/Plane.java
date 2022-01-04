package RTRenderEngine.Solids;

import RTRenderEngine.Math.Ray;
import RTRenderEngine.Math.Vector3;
import javafx.scene.paint.Color;

public class Plane extends Solid {
    private boolean checkerPattern;

    public Plane(float height, Color color, boolean checkerPattern, float reflectivity, float emission) {
        super(new Vector3(0, height, 0), color, reflectivity, emission);
        this.checkerPattern = checkerPattern;
    }

    @Override
    public Vector3 calculateIntersection(Ray ray) {
        float t = -(ray.getOrigin().getY()-position.getY()) / ray.getDirection().getY();
        if (t > 0 && Float.isFinite(t))
        {
            return ray.getOrigin().add(ray.getDirection().multiply(t));
        }

        return null;
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }

    @Override
    public Color getTextureColor(Vector3 point) {
        if (checkerPattern) {
            // in first or third quadrant of the checkerplane
            if (((point.getX() > 0) & (point.getZ() > 0)) || ((point.getX() < 0) & (point.getZ() < 0))) {
                if ((int)point.getX() % 2 == 0 ^ (int)point.getZ() % 2 != 0) {
                    return Color.GRAY;
                } else {
                    return Color.DARKGRAY;
                }
            } else {
                // in second or fourth quadrant of the checkerplane
                if ((int)point.getX() % 2 == 0 ^ (int)point.getZ() % 2 != 0) {
                    return Color.DARKGRAY;
                } else {
                    return Color.GRAY;
                }
            }
        } else {
            return getColor();
        }
    }
}
