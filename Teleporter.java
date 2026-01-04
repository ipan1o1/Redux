import java.awt.Color;
import java.awt.Graphics;

public class Teleporter extends Square {

    //Attributs
    private Teleporter linked;
    private boolean active = true;

    //Setter
    public void setActive(boolean b){
        this.active = b;
    }

    //Constructeur
    public Teleporter(int x, int y) {
        super(x, y);
    }

    //Methodes
    public void link(Teleporter other) {
        this.linked = other;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void enter(Ball b) {
        if (!active) return;
        if (linked != null) {
            b.setX(linked.x + 0.5);
            b.setY(linked.y + 0.5);
        }
    }

    @Override
    public void leave(Ball b) { }

    @Override
    public void touch(Ball b) { }

    @Override
    public void draw(Graphics g, int pixelSize) {
        g.setColor(Color.CYAN);
        g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);

        g.setColor(Color.BLUE);
        g.drawRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
    }
}
