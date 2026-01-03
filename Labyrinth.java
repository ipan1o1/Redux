import java.awt.*;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.swing.*;

public class Labyrinth extends JPanel {

    // Attributes
    protected final int tile = 50;
    private int width, height;
    private Square[][] map;
    private Ball ball;
        
    private Point lastMousePosition;
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
                    if(ch == '#'){
                        map[x][y] = new Wall(x, y);
                    }else{
                        map[x][y] = new Floor(x, y);
                    }
                }
            }
            sc.close();
        } 
        catch (Exception e) { e.printStackTrace();}

        this.setPreferredSize(new Dimension(width * tile, height * tile));
    }

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

    //Setters + Getters
    public int getGridWidth(){return width; }
    public int getGridHeight(){return height; }
    public void setBall(Ball b){this.ball = b; }
    
}
