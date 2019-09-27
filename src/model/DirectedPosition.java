package model;

public class DirectedPosition extends Position {

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
}
