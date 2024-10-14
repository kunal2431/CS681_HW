package umbcs681.streamcolorfilter;

public class Color{

   private int redScale;
   private int greenScale;
   private int blueScale;

   public Color(int r, int g, int b){
       this.redScale = r;
       this.greenScale = g;
       this.blueScale = b;
   }

    public int getRedScale() {
        return this.redScale;
    }

    public int getGreenScale() {
        return this.greenScale;
    }

    public int getBlueScale() {
        return this.blueScale;
    }
}