import java.awt.*;
import java.util.List;
import java.util.Random;

public class Food extends Position implements Drawable {

    Color color = new Color(242, 185, 27);

    public static Food spawn(List<Position> unavailable) {
        do {
            int x = new Random().nextInt(Board.cols - 1);
            int y = new Random().nextInt(Board.rows - 1);

            final Food food = new Food(x, y);
            if (!unavailable.stream().anyMatch(pos -> pos.isAt(food))) {
                return food;
            }
        } while (true);
    }

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        Color drawColor = g.getColor();

        g.setColor(color);

        int x = getX() * Board.cellSize + Board.cellPadding;
        int y = getY() * Board.cellSize + Board.cellPadding;
        int ovalSize = Board.cellSize - 2 * Board.cellPadding;

        g.fillOval(x, y, ovalSize, ovalSize);

        g.setColor(drawColor);
    }
}
