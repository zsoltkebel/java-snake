import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class Board extends JPanel {

    public static int rows = 25;
    public static int cols = 25;
    public static int cellSize = 18;

    public static int margin = 50;
    public static int cellPadding = 3;


    private Timer timer;
    private Snake snake;
    private Food food;
    private OnScoreChangedListener onScoreChangedListener;
    private OnGameOverListener onGameOverListener;

    private boolean firstPressInTick = true;

    public Board(OnScoreChangedListener onScoreChangedListener, OnGameOverListener onGameOverListener) {
        this.onScoreChangedListener = onScoreChangedListener;
        this.onGameOverListener = onGameOverListener;

        snake = new Snake(10, 10, 1);

        food = new Food(10, 20);

        timer = new Timer(100, new TickListener());

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(keyEvent -> {
                    System.out.println("Got key event!");

                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED && firstPressInTick) {
                        firstPressInTick = false;
                        Direction turnTo = getKeyDirection(keyEvent.getKeyCode());
                        if (turnTo != null) {
                            snake.setHeading(turnTo);
                            return true;
                        }
                    }
                    return true;
                });

        timer.start();

        // UI
        this.setPreferredSize(new Dimension(getBoardWidth(), getBoardHeight()));
        this.setSize(new Dimension(getBoardWidth(), getBoardHeight()));
        this.setBackground(Color.black);
    }

    public static int getBoardHeight() {
        return rows * cellSize;
    }

    public static int getBoardWidth() {
        return cols * cellSize;
    }

    Direction getKeyDirection(int keyCode) {
        switch (keyCode) {
            case 37:
                return Direction.LEFT;
            case 38:
                return Direction.UP;
            case 39:
                return Direction.RIGHT;
            case 40:
                return Direction.DOWN;
            default:
                return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (food.isAt(snake.getHead())) {
            food = Food.spawn(snake.getBodyPositions());
            snake.appendSnake();
            onScoreChangedListener.onScoreChanged(snake.body.size() - 1);
        }

        g.setColor(new Color(222, 222, 222));
        g.setColor(Color.black);
        // rows
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(0, i * cellSize, getBoardWidth(), i * cellSize);
        }

        // cols
        for (int i = 0; i < cols + 1; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getBoardHeight());
        }

        food.draw(g);
        snake.draw(g);
    }

    private class TickListener implements ActionListener {
        public void actionPerformed (ActionEvent event){

            if (snake.body.stream().filter(part -> part.isAt(snake.getHead())).count() > 1) {
                onGameOverListener.onGameOver();
                timer.stop();
            } else {
                snake.move();
            }

            repaint();

            firstPressInTick = true;
        }
    }

    public void reset() {
        timer.stop();

        snake = new Snake(10, 10, 1);

        timer.start();
    }

    public interface OnScoreChangedListener {
        void onScoreChanged(int score);
    }

    public interface OnGameOverListener {
        void onGameOver();
    }
}
