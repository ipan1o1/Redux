import javax.swing.*;

public class Game {
    //Attributs
    private Labyrinth lab;
    private Ball ball;
    private final int delayMs = 20;
    private final double friction = 0.0012;
    private Timer timer;
    private boolean gameOver = false;
    private final double startX = 2.0;
    private final double startY = 2.0;

    //Constructeur
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

        timer = new Timer(delayMs, e -> tick());
        timer.start();

        frame.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
            if (e.getKeyCode() == java.awt.event.KeyEvent.VK_R) {
                resetGame();
            }
        }
        });
    }

    //Methodes

    private void handleInternalWallCollisions() {
        int cx = (int) Math.floor(ball.getX());
        int cy = (int) Math.floor(ball.getY());

        for (int gx = cx - 1; gx <= cx + 1; gx++) {
            for (int gy = cy - 1; gy <= cy + 1; gy++) {
                collideWithCell(gx, gy);
            }
        }
    }

    private void collideWithCell(int gx, int gy) {
        Square s = lab.getSquare(gx, gy);
        if (s == null) return;
        if (s.isEmpty()) return;

        double closestX = clamp(ball.getX(), gx, gx + 1.0);
        double closestY = clamp(ball.getY(), gy, gy + 1.0);

        double dx = ball.getX() - closestX;
        double dy = ball.getY() - closestY;

        double r = ball.getRadius();
        double dist2 = dx * dx + dy * dy;

        if (dist2 >= r * r) return;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) ball.setX(closestX + r);
            else        ball.setX(closestX - r);
            ball.setVx(-ball.getVx());
        } else {
            if (dy > 0) ball.setY(closestY + r);
            else        ball.setY(closestY - r);
            ball.setVy(-ball.getVy());
        }
    }

    private double clamp(double v, double min, double max) {
        if (v < min) return min;
        if (v > max) return max;
        return v;
    }

    private void tick(){
        double vmax = 0.2;
        double v = ball.speed();
        if (v > vmax) {
            ball.setVx(ball.getVx() * (vmax / v));
            ball.setVy(ball.getVy() * (vmax / v));
        }

        ball.move();
        handleInternalWallCollisions();

        int cellX = (int) Math.floor(ball.getX());
        int cellY = (int) Math.floor(ball.getY());

        Square sq = lab.getSquare(cellX, cellY);
        if (sq != null) {
            sq.enter(ball);
            cellX = (int) Math.floor(ball.getX());
            cellY = (int) Math.floor(ball.getY());
            sq = lab.getSquare(cellX, cellY);
        }

        if (!gameOver && sq != null) {
            if (sq instanceof Exit) {
                gameOver = true;
                timer.stop();
                JOptionPane.showMessageDialog(lab, "YOU WIN!");
                System.exit(0);
            } else if (sq instanceof Hole) {
                gameOver = true;
                timer.stop();
                JOptionPane.showMessageDialog(lab, "YOU LOSE!");
                System.exit(0);
            }
        }

        double vx = ball.getVx();
        double vy = ball.getVy();
        double speed = Math.sqrt(vx * vx + vy * vy);


        if (speed > 0) {
            double fx = friction * vx / speed;
            double fy = friction * vy / speed;

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

    private void resetGame() {
        ball.setX(startX);
        ball.setY(startY);
        ball.setVx(0);
        ball.setVy(0);
    }

    public static void main(String[] args) {
        String fileName = "laby.txt";
        new Game(fileName);
    }
}
