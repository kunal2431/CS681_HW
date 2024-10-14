package umbcs681.streamcolorfilter;

import java.util.ArrayList;

public class Image{

    private int width;
    private int height;

    private ArrayList<ArrayList<Color>> pixels = new ArrayList<>();

    public Image(int height, int width){
        this.height = height;
        this.width = width;

        for (int i = 0; i < height; i++) {
            pixels.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                pixels.get(i).add(new Color(0, 0, 0));
            }
        }
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public Color getColor(int x, int y){
        if(x < 0 || x >= this.height || y < 0 || y >= this.width){
            throw new IndexOutOfBoundsException("Wrong values: x: "+ x +", y: " + y); }
        else {
            return pixels.get(x).get(y);
        }
    }

    public void setColor(int x, int y, Color color){
        if(x < 0 || x >= this.height || y < 0 || y >= this.width){
            throw new IndexOutOfBoundsException("Wrong values: x: "+ x +", y: " + y); }
        else {
            this.pixels.get(x).set(y, color);
        }
    }

}