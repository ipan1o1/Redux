import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.swing.*;

public class Labyrinth extends JPanel {

    // Attributes
    protected final int tile = 50;
    private int width, height;
    private Square[][] map;
    private Ball ball;
    private final double fa = 0.01;
    private Point lastMousePosition = null;

    //Setters + Getters
    public int getGridWidth(){return width; }
    public int getGridHeight(){return height; }
    public void setBall(Ball b){this.ball = b; }

    // Constructor
    public Labyrinth(String fileName){
        this.setBackground(Color.WHITE);
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName));
            this.width = sc.nextInt();
            this.height = sc.nextInt();
            sc.nextLine();

            this.map = new Square[width][height];

            for(int y = 0; y < this.height; y++){
                String line = sc.nextLine();
                for(int x = 0; x < this.width; x++){
                    
                    Character ch = line.charAt(x);
                    switch (ch) {
                        case '#':
                            map[x][y] = new Wall(x, y);
                            break;
                        case 'E':
                            map[x][y] = new Exit(x, y);
                            break;
                        case 'O':
                            map[x][y] = new Hole(x, y);
                            break;
                        default:
                            map[x][y] = new Floor(x, y);
                            break;
                    }
                }
            }
            sc.close();
        } 
        catch (Exception e) { e.printStackTrace();}

        this.setPreferredSize(new Dimension(width * tile, height * tile));
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                applyMouseAcceleration(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                applyMouseAcceleration(e);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lastMousePosition = null; // avoid big jump when re-entering
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lastMousePosition = e.getPoint();
            }
        });
    }

    //Methodes

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(map != null){
            for (int x = 0; x < map.length ; x++){
                for (int y = 0; y < map[0].length ; y++){
                    if(map[x][y] != null) map[x][y].draw(g, tile);
                }
            }
        }
        if (ball != null) this.ball.draw(g, tile);
    }

    private void applyMouseAcceleration(MouseEvent e) {
    if (ball == null) return;

    Point p = e.getPoint();
    if (lastMousePosition != null) {
        int dxPixels = p.x - lastMousePosition.x;
        int dyPixels = p.y - lastMousePosition.y;

        // convert pixels → grid units
        double sx = (double) dxPixels / tile;
        double sy = (double) dyPixels / tile;

        ball.setVx(ball.getVx() + fa * sx);
        ball.setVy(ball.getVy() + fa * sy);
    }
    lastMousePosition = p;
    }

    public Square getSquare(int gx, int gy) {
        if (gx < 0 || gy < 0 || gx >= width || gy >= height) return null;
        return map[gx][gy]; // because you store map[width][height] as map[x][y]
    }

}
