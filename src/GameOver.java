import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {

    private OnRestartListener onRestartListener;

    public GameOver(OnRestartListener onRestartListener) {
        this.onRestartListener = onRestartListener;

        this.setPreferredSize(new Dimension(Board.getBoardWidth(), Board.getBoardHeight()));
        this.setBounds(1, 1, Board.getBoardWidth() - 2, Board.getBoardHeight() - 2);
        this.setBackground(new Color(255, 255, 255, 125));
        this.setLayout(new GridBagLayout());

        JLabel title = new JLabel("Game Over");
        title.setForeground(Color.BLUE);

        this.add(title);

        JButton newGame = new JButton("Restart");
        newGame.addActionListener(actionEvent -> onRestartListener.onRestart());

        this.add(newGame);
    }

    public interface OnRestartListener {
        void onRestart();
    }
}
