import java.awt.*;

public class DirectedPosition extends Position implements Drawable {

    private Direction direction;

    public DirectedPosition() {
        super();
    }

    public DirectedPosition(Position pos, Direction direction) {
        super(pos);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    void move() {
        this.translate(direction);
    }

    @Override
    public DirectedPosition translate(Direction direction) {
        Position newPos = super.translate(direction);

        return new DirectedPosition(newPos, direction);
    }

    @Override
    public void draw(Graphics g) {
        Color drawColor = g.getColor();

        g.setColor(Color.GREEN);
        int x = getX() * Board.cellSize + Board.cellPadding;
        int y = getY() * Board.cellSize + Board.cellPadding;
        int rectSize = Board.cellSize - 2 * Board.cellPadding;

        g.fillRect(x, y, rectSize, rectSize);

        g.setColor(drawColor);
    }
}
