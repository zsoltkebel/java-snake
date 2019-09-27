package ui;

import ui.Board;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    private OnRestartListener onRestartListener;

    public GameOver(OnRestartListener onRestartListener) {
        this.onRestartListener = onRestartListener;

        this.setPreferredSize(new Dimension(Board.getBoardWidth(), Board.getBoardHeight()));
        this.setBounds(1, 1, Board.getBoardWidth() - 2, Board.getBoardHeight() - 2);
        this.setBackground(new Color(255, 255, 255, 125));
        this.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 255, 0));

        JLabel title = new JLabel("ui.Game Over");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 26));
        title.setForeground(Color.BLUE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);

        JButton newGame = new JButton("Restart");
        newGame.addActionListener(actionEvent -> onRestartListener.onRestart());
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(newGame);

        this.add(panel);
    }

    public interface OnRestartListener {
        void onRestart();
    }
}
