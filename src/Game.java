import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game extends JPanel {

    private JLayeredPane layeredPane;

    JLabel scoreLabel;
    JSlider cellSizeSlider;

    Board board;
    GameOver gameOver;

    int margin = 50;

    public Game() {
        scoreLabel = new JLabel("0");

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(Board.getBoardWidth(), Board.getBoardHeight()));

        board = new Board(score -> scoreLabel.setText(Integer.toString(score)), new OnGameOverListener());
        gameOver = new GameOver(() -> {
            board.reset();
            layeredPane.remove(gameOver);
            scoreLabel.setText("0");
        });

        layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.add(scoreLabel, BorderLayout.NORTH);
    }

    class OnGameOverListener implements Board.OnGameOverListener {
        @Override
        public void onGameOver() {
            layeredPane.add(gameOver, JLayeredPane.POPUP_LAYER);
        }
    }
}
