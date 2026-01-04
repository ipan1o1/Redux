import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Square{

    // Constructeur
    public Wall(int x, int y){
        super(x, y);
    }

    // Methodes
    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public void enter(Ball b){
    }

    @Override
    public void leave(Ball b){
    }

    @Override
    public void touch(Ball b){
    }

    @Override
    public void draw(Graphics g, int pixelSize){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x * pixelSize, this.y * pixelSize,  pixelSize, pixelSize);
        g.setColor(Color.BLACK);
        g.drawRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
    }

}