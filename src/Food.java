import java.awt.*;
import java.util.List;

public class Food extends Position implements Drawable {

    Color color = new Color(242, 185, 27);

    public static Food spawn(List<Position> excluded) {
        return new Food(Position.spawn(excluded));
    }

    public Food(int x, int y) {
        super(x, y);
    }

    public Food(Position position) {
        super(position);
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
