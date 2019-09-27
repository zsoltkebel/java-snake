package model;

import ui.Board;

import java.awt.*;
import java.util.List;

public class Food implements Drawable {

    private Position position;
    private Color color = new Color(242, 185, 27);

    public static Food spawn(List<Position> excluded) {
        return new Food(Position.spawn(excluded));
    }

    public Food(int x, int y) {
        this(new Position(x, y));
    }

    public Food(Position position) {
        this.position = new Position(position);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void draw(Graphics g) {
        Color drawColor = g.getColor();

        g.setColor(color);

        int x = position.getX() * Board.cellSize + Board.cellPadding;
        int y = position.getY() * Board.cellSize + Board.cellPadding;
        int ovalSize = Board.cellSize - 2 * Board.cellPadding;

        g.fillOval(x, y, ovalSize, ovalSize);

        g.setColor(drawColor);
    }
}
