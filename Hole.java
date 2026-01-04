import java.awt.Color;
import java.awt.Graphics;

public class Hole extends Square {

    public Hole(int x, int y) {
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
        g.setColor(new Color(200, 200, 200));
        g.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);

        g.setColor(Color.BLACK);
        int margin = pixelSize / 6;
        g.fillOval(this.x * pixelSize + margin, this.y * pixelSize + margin,
                   pixelSize - 2 * margin, pixelSize - 2 * margin);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
    }
}
