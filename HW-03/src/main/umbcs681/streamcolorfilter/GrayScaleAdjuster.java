package umbcs681.streamcolorfilter;

public class GrayScaleAdjuster implements ColorAdjuster{

   public GrayScaleAdjuster(){

   }

   @Override
   public Color adjust(Color color) {
       int r, g, b;
       r = color.getRedScale();
       g = color.getGreenScale();
       b = color.getBlueScale();
       int avg = (r+g+b)/3;
       return new Color(avg, avg, avg);
   }
}