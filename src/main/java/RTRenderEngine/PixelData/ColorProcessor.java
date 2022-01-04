package RTRenderEngine.PixelData;


import java.util.Collection;
import java.util.List;

public class ColorProcessor {

    private float red;
    private float green;
    private float blue;

    public ColorProcessor(float red, float green, float blue)
    {
        if (red > 1F || green > 1F || blue > 1F)
            throw new IllegalArgumentException("Color parameter(s) outside of expected range");

        if (Float.isNaN(red) || Float.isNaN(green) || Float.isNaN(blue))
            throw new IllegalArgumentException("One or more color parameters are NaN");

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public ColorProcessor multiply(ColorProcessor other) {
        return new ColorProcessor(red*other.red, green*other.green, blue*other.blue);
    }

    public ColorProcessor multiply(float brightness) {
        brightness = Math.min(1, brightness);
        return new ColorProcessor(red * brightness, green * brightness, blue * brightness);
    }

    public ColorProcessor add(ColorProcessor other) {
        return new ColorProcessor(Math.min(1, this.red+other.red), Math.min(1, this.green+other.green), Math.min(1, this.blue+other.blue));
    }

    public void addSelf(ColorProcessor other) {
        this.red = Math.min(1, this.red+other.red);
        this.green = Math.min(1, this.green+other.green);
        this.blue = Math.min(1, this.blue+other.blue);
    }

    public ColorProcessor add(float brightness) {
        return new ColorProcessor(Math.min(1, red+brightness), Math.min(1, green+brightness), Math.min(1, blue+brightness));
    }

    public int getRGB() {
        int redPart = (int)(red*255);
        int greenPart = (int)(green*255);
        int bluePart = (int)(blue*255);

            // Shift bits to right place
        redPart = (redPart << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        greenPart = (greenPart << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        bluePart = bluePart & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | redPart | greenPart | bluePart; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

        // https://en.wikipedia.org/wiki/Grayscale#Luma_coding_in_video_systems
    public float getLuminance() {
        return red*0.2126F + green*0.7152F + blue*0.0722F;
    }

    public static ColorProcessor fromInt(int argb) {
        int b = (argb)&0xFF;
        int g = (argb>>8)&0xFF;
        int r = (argb>>16)&0xFF;

        return new ColorProcessor(r/255F, g/255F, b/255F);
    }

   // public java.awt.Color toAWTColor() {
   //     return new java.awt.Color(red, green, blue);
   // }

    public static ColorProcessor average(Collection<ColorProcessor> colors) {
        float rSum = 0;
        float gSum = 0;
        float bSum = 0;

        for (ColorProcessor col : colors) {
            rSum += col.getRed();
            gSum += col.getGreen();
            bSum += col.getBlue();
        }


        int colorCount = colors.size();
        return new ColorProcessor(rSum/colorCount, gSum/colorCount, bSum/colorCount);
    }

    public static ColorProcessor average(List<ColorProcessor> colors, List<Float> weights) {
        if (colors.size() != weights.size())  {
            throw new IllegalArgumentException("Specified color count does not match weight count.");
        }

        float rSum = 0;
        float gSum = 0;
        float bSum = 0;
        float weightSum = 0;

        for (int i = 0; i<colors.size(); i++) {
            ColorProcessor col = colors.get(i);
            float weight = weights.get(i);
            rSum += col.getRed() * weight;
            gSum += col.getGreen() * weight;
            bSum += col.getBlue() * weight;
            weightSum+=weight;
        }

        return new ColorProcessor(rSum/weightSum, gSum/weightSum, bSum/weightSum);
    }

    public static ColorProcessor average(ColorProcessor... colors) {
        float rSum = 0;
        float gSum = 0;
        float bSum = 0;

        for (ColorProcessor col : colors) {
            rSum += col.getRed();
            gSum += col.getGreen();
            bSum += col.getBlue();
        }

        int colorCount = colors.length;
        return new ColorProcessor(rSum/colorCount, gSum/colorCount, bSum/colorCount);
    }

    private static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public static ColorProcessor lerp(ColorProcessor a, ColorProcessor b, float t) {
        return new ColorProcessor(lerp(a.getRed(), b.getRed(), t), lerp(a.getGreen(), b.getGreen(), t), lerp(a.getBlue(), b.getBlue(), t));
    }

    public static final ColorProcessor BLACK = new ColorProcessor(0F,0F,0F);
    public static final ColorProcessor WHITE = new ColorProcessor(1F, 1F, 1F);
    public static final ColorProcessor RED = new ColorProcessor(1F, 0F, 0F);
    public static final ColorProcessor GREEN = new ColorProcessor(0F, 1F, 0F);
    public static final ColorProcessor BLUE = new ColorProcessor(0F, 0F, 1F);
    public static final ColorProcessor MAGENTA = new ColorProcessor(1.0F, 0.0F, 1.0F);
    public static final ColorProcessor GRAY = new ColorProcessor(0.5F, 0.5F, 0.5F);
    public static final ColorProcessor DARK_GRAY = new ColorProcessor(0.2F, 0.2F, 0.2F);

}
