import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game extends JPanel {

    JLabel scoreLabel;
    JSlider cellSizeSlider;

    Board board;

    int margin = 50;

    public Game() {
        board = new Board(score -> scoreLabel.setText(Integer.toString(score)), () -> {

        });

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(margin, margin, margin, margin));

        scoreLabel = new JLabel("0");
        cellSizeSlider = new JSlider(JSlider.VERTICAL, 15, 30, 20);
        cellSizeSlider.addChangeListener(e -> {
            Board.cellSize = cellSizeSlider.getValue();
            board.setPreferredSize(new Dimension(Board.getBoardWidth(), Board.getBoardHeight()));
            board.setSize(new Dimension(Board.getBoardWidth(), Board.getBoardHeight()));

            panel.setPreferredSize(new Dimension(
                    Board.getBoardWidth() + 2 * margin,
                    Board.getBoardHeight() + 2 * margin));
            panel.setSize(new Dimension(
                    Board.getBoardWidth() + 2 * margin,
                    Board.getBoardHeight() + 2 * margin));
        });

        panel.add(cellSizeSlider);
        panel.add(scoreLabel);
        panel.add(board);

        this.add(panel);
    }
}
