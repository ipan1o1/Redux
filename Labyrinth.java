import java.awt.*;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.swing.*;

public class Labyrinth extends JPanel {

    // Attributes
    protected final int tile = 50;
    private int width, height;
    private Square[][] map;
        
    // Constructor
    public Labyrinth(String file){
        this.setPreferredSize(new Dimension(1000,550));
        this.setBackground(Color.WHITE);
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.width = sc.nextInt();
            this.height = sc.nextInt();
            sc.nextLine();
            this.map = new Square[height][width];
            for(int l = 0; l < this.height; l++){
                String line = sc.nextLine();
                for(int c = 0; c < this.width; c++){
                    Square cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new SquareSolid(l, c); break;
                        case ' ': cc = new SquareOrdinary(l, c); break;
                        default: cc = null; break;
                    }
                    this.map[l][c] = cc;
                }
            }
            sc.close();
        } 
        catch (Exception e) { e.printStackTrace();}
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < map.length ; i++){
            for (int j = 0; j < map[0].length ; j++){
                int x = j * tile;
                int y = i * tile;

                g.setColor(Color.DARK_GRAY);
                g.drawRect(x, y, tile, tile);
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Enigma Game");
        String fileName = "laby.txt";
        Labyrinth panel = new Labyrinth(fileName);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
