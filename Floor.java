import java.awt.Color;
import java.awt.Graphics;

public class Floor extends Square {

    protected Entity content;
    
    // Constructor
    public Floor(int x, int y){
        super(x, y);
    }

    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public void enter(Ball b){ }

    @Override
    public void leave(Ball b){ }

    @Override
    public void touch(Ball b){ }

    @Override
    public void draw(Graphics g, int pixelSize){
        g.setColor(new Color(200, 200, 200));
        g.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
        g.setColor(new Color(220, 220, 220));
        g.drawRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
    }
    
}