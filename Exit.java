import java.awt.Color;
import java.awt.Graphics;

public class Exit extends Square {

    public Exit(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return true; 
    }

    @Override
    public void enter(Ball b) { }

    @Override
    public void leave(Ball b) { }

    @Override
    public void touch(Ball b) { }

    @Override
    public void draw(Graphics g, int pixelSize) {
        g.setColor(new Color(120, 220, 120));
        g.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
        g.setColor(Color.BLACK);
        g.drawRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
    }
}
