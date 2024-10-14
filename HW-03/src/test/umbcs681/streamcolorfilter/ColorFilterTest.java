package umbcs681.streamcolorfilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorFilterTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_GrayScaleAdjuster(){
        Image image = new Image(20, 20);
        image.setColor(7, 12, new Color(255, 255, 0));
        Image grayScaleImg = Images.transform(image, new GrayScaleAdjuster());
        assertEquals(170, grayScaleImg.getColor(7, 12).getRedScale());
        assertEquals(0, grayScaleImg.getColor(6, 12).getBlueScale());
    }

    @Test
    //Test Case 2: Negative Test
    public void verify_NegSetColor(){
        Image image = new Image(15, 17);
        try{
            image.setColor(16, 17, new Color(255, 0, 0));
            fail("setColor height is larger than image height");
        }
        catch(IndexOutOfBoundsException ex){
            assertEquals("Wrong values: x: 16, y: 17", ex.getMessage());
        }
    }

    @Test
    //Test Case 3: Negative Test
    public void verify_NegGetColor(){
        Image image = new Image(15, 17);
        try{
            image.getColor(16, 17);
            fail("getColor height is larger than image height");
        }
        catch(IndexOutOfBoundsException ex){
            assertEquals("Wrong values: x: 16, y: 17", ex.getMessage());
        }
    }

    @Test
    //Test Case 4: Verify methods of Color class
    public void verify_Color(){
        Image image = new Image(15, 17);
        image.setColor(7, 12, new Color(255, 255, 255));
        image.setColor(0, 0, new Color(0, 170, 255));
        assertEquals(255, image.getColor(7, 12).getGreenScale());
        assertEquals(255, image.getColor(0, 0).getBlueScale());
        Image grayScaleImg = Images.transform(image, new GrayScaleAdjuster());
        assertEquals(255, grayScaleImg.getColor(7, 12).getRedScale());
        assertEquals(141, grayScaleImg.getColor(0, 0).getBlueScale());
        assertEquals(0, grayScaleImg.getColor(0, 1).getBlueScale());
    }

    @Test
    //Test Case 5: Verify methods of Image class
    public void verify_Image(){
        Image image = new Image(15, 17);
        assertEquals(15, image.getHeight());
        assertEquals(17, image.getWidth());
    }

}
