import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity{

    // Attributs
    private double x, y;
    private double vx, vy;
    private double radius = 0.3;


    //Constructeurs
    public Ball(double x, double y) {
        this(x, y, 0.0, 0.0, 0.3);
    }

    public Ball(double x, double y, double vx, double vy) {
        this(x, y, vx, vy, 0.3);
    }

    public Ball(double x, double y, double vx, double vy, double radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    //Getters + Setters
    public double getX() { return x; }
    public double getY() { return y; }

    public double getVx() { return vx; }
    public double getVy() { return vy; }

    public double getRadius() { return radius; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setVx(double vx) { this.vx = vx; }
    public void setVy(double vy) { this.vy = vy; }

    public void setRadius(double radius) { this.radius = radius; }

    // Methodes 

    public double speed() {
        return Math.sqrt(vx * vx + vy * vy);
    }

    public void move(){
        x += vx;
        y += vy;
    }
    
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g, int pixelSize){ // FIX Math ball bounce is OFFSET -> North + West
        g.setColor(new Color(255, 153, 204));
        int rPixels = (int)(this.radius * pixelSize);
        int dPixels = rPixels * 2;
        int xPixels = (int)((this.x - this.radius) * pixelSize);
        int yPixels = (int)((this.y - this.radius) * pixelSize);

        g.fillOval(xPixels, yPixels, dPixels, dPixels);
    }
}