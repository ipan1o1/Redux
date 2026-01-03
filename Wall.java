import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Square{

    // Constructor
    public Wall(int x, int y){
        super(x, y);
    }

    // Methods
    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public void enter(Ball b){
        // Does nothing
    }

    @Override
    public void leave(Ball b){
        // Does nothing
    }

    @Override
    public void touch(Ball b){
        // Makes the ball bounce
    }

    @Override
    public void draw(Graphics g, int pixelSize){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x * pixelSize, this.y * pixelSize,  pixelSize, pixelSize);
        g.setColor(Color.BLACK);
        g.drawRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
    }

}