import javax.swing.*;

public class Game {
    private Labyrinth lab;
    private Ball ball;
    private final int delayMs = 20;
    private final double friction = 0.0012;

    public Game(String fileName){
        lab = new Labyrinth(fileName);
        ball = new Ball(2.0, 2.0, 0.02, 0.02);
        lab.setBall(ball);

        JFrame frame = new JFrame("Enigma Game");
        frame.setContentPane(lab);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(delayMs, e -> tick());
        timer.start();
    }

    private void tick(){
        ball.move();
        double r = ball.getRadius();
        double x = ball.getX();
        double y = ball.getY();

        int W = lab.getGridWidth();
        int H = lab.getGridHeight();

        if (x - r < 0.0){
            ball.setX(r);
            ball.setVx(-ball.getVx());
        } else if (x + r > W){
            ball.setX(W - r);
            ball.setVx(-ball.getVx());
        }

        if (y - r < 0.0){
            ball.setY(r);
            ball.setVy(-ball.getVy());
        } else if (y + r > H){
            ball.setY(H - r);
            ball.setVy(-ball.getVy());
        }

        double vx = ball.getVx();
        double vy = ball.getVy();
        double speed = Math.sqrt(vx * vx + vy * vy);

        if (speed > 0) {
            double fx = friction * vx / speed;
            double fy = friction * vy / speed;

            // avoid reversing direction when speed is very small
            if (speed > friction) {
                ball.setVx(vx - fx);
                ball.setVy(vy - fy);
            } else {
                ball.setVx(0);
                ball.setVy(0);
            }
        }

        lab.repaint();

    }

    public static void main(String[] args) {
        String fileName = "laby.txt";
        new Game(fileName);
    }
}
