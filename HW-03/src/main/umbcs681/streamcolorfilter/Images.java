package umbcs681.streamcolorfilter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Images{

   public Images(){ }

   public static Image transform(Image image, ColorAdjuster adjuster){
       Image adjusted = new Image(image.getHeight(), image.getWidth());

       IntStream.range(0, image.getHeight()).forEach(i -> {
           IntStream.range(0, image.getWidth()).forEach(j -> {
               adjusted.setColor(i, j, adjuster.adjust(image.getColor(i, j)));
           });
       });

       return adjusted;
   }
}
