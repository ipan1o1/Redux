import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class EnigmaTests {

    // -------------------------
    // Helpers
    // -------------------------
    private Path writeTempLaby(String content) throws IOException {
        Path tmp = Files.createTempFile("laby_test_", ".txt");
        Files.writeString(tmp, content);
        tmp.toFile().deleteOnExit();
        return tmp;
    }

    // -------------------------
    // Ball tests
    // -------------------------
    @Test
    void ball_speed_isCorrect() {
        Ball b = new Ball(1.0, 1.0, 3.0, 4.0);
        assertEquals(5.0, b.speed(), 1e-9);
    }

    @Test
    void ball_move_updatesPosition() {
        Ball b = new Ball(2.0, 2.0, 0.1, -0.2);
        b.move();
        assertEquals(2.1, b.getX(), 1e-9);
        assertEquals(1.8, b.getY(), 1e-9);
    }

    @Test
    void ball_move_dxdy_updatesPosition() {
        Ball b = new Ball(2.0, 2.0, 0.0, 0.0);
        b.move(0.25, -0.5);
        assertEquals(2.25, b.getX(), 1e-9);
        assertEquals(1.5, b.getY(), 1e-9);
    }

    @Test
    void ball_setters_work() {
        Ball b = new Ball(0.0, 0.0);
        b.setX(1.2);
        b.setY(3.4);
        b.setVx(-0.3);
        b.setVy(0.9);
        b.setRadius(0.42);

        assertEquals(1.2, b.getX(), 1e-9);
        assertEquals(3.4, b.getY(), 1e-9);
        assertEquals(-0.3, b.getVx(), 1e-9);
        assertEquals(0.9, b.getVy(), 1e-9);
        assertEquals(0.42, b.getRadius(), 1e-9);
    }

    // -------------------------
    // Square types tests
    // -------------------------
    @Test
    void floor_isEmpty_true() {
        Floor f = new Floor(1, 1);
        assertTrue(f.isEmpty());
    }

    @Test
    void wall_isEmpty_false() {
        Wall w = new Wall(1, 1);
        assertFalse(w.isEmpty());
    }

    @Test
    void exit_isEmpty_true() {
        Exit e = new Exit(1, 1);
        assertTrue(e.isEmpty());
    }

    @Test
    void hole_isEmpty_true() {
        Hole h = new Hole(1, 1);
        assertTrue(h.isEmpty());
    }

    // -------------------------
    // Teleporter tests
    // -------------------------
    @Test
    void teleporter_enter_movesBallToLinkedCenter_whenActive() {
        Teleporter t1 = new Teleporter(2, 3);
        Teleporter t2 = new Teleporter(10, 1);
        t1.link(t2);

        Ball b = new Ball(2.5, 3.5, 0.0, 0.0);
        t1.enter(b);

        // linked cell center = (linked.x + 0.5, linked.y + 0.5)
        assertEquals(10.5, b.getX(), 1e-9);
        assertEquals(1.5, b.getY(), 1e-9);
    }

    @Test
    void teleporter_enter_doesNothing_whenInactive() {
        Teleporter t1 = new Teleporter(2, 3);
        Teleporter t2 = new Teleporter(10, 1);
        t1.link(t2);
        t1.setActive(false);

        Ball b = new Ball(4.0, 4.0, 0.0, 0.0);
        t1.enter(b);

        assertEquals(4.0, b.getX(), 1e-9);
        assertEquals(4.0, b.getY(), 1e-9);
    }

    // -------------------------
    // Labyrinth parsing + getSquare tests
    // -------------------------
    @Test
    void labyrinth_loadsAndCreatesCorrectSquares() throws IOException {
        // 5x4 map
        // #####
        // #E O#
        // # T #
        // #####
        String laby =
            "5 4\n" +
            "#####\n" +
            "#E O#\n" +
            "# T #\n" +
            "#####\n";

        Path p = writeTempLaby(laby);
        Labyrinth lab = new Labyrinth(p.toString());

        assertEquals(5, lab.getGridWidth());
        assertEquals(4, lab.getGridHeight());

        // corners are walls
        assertTrue(lab.getSquare(0, 0) instanceof Wall);
        assertTrue(lab.getSquare(4, 3) instanceof Wall);

        // Exit at (1,1)
        assertTrue(lab.getSquare(1, 1) instanceof Exit);

        // Hole at (3,1)
        assertTrue(lab.getSquare(3, 1) instanceof Hole);

        // Teleporter at (2,2)
        assertTrue(lab.getSquare(2, 2) instanceof Teleporter);

        // Floor at (1,2) (space)
        assertTrue(lab.getSquare(1, 2) instanceof Floor);
    }

    @Test
    void labyrinth_getSquare_returnsNull_outOfBounds() throws IOException {
        String laby =
            "3 3\n" +
            "###\n" +
            "# #\n" +
            "###\n";

        Path p = writeTempLaby(laby);
        Labyrinth lab = new Labyrinth(p.toString());

        assertNull(lab.getSquare(-1, 0));
        assertNull(lab.getSquare(0, -1));
        assertNull(lab.getSquare(3, 0));
        assertNull(lab.getSquare(0, 3));
    }
}
