import java.awt.Graphics;

public abstract class Square {

    //Atributes
    protected final int x, y;

    // Constructor
    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Methods
    public abstract boolean isEmpty();
    public abstract void enter(Ball b);
    public abstract void leave(Ball b);
    public abstract void touch(Ball b); 
    public abstract void draw(Graphics g, int pixelSize);
}
