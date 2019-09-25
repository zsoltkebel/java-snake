import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Snake implements Drawable {

    private Direction heading;
    public List<DirectedPosition> body;

    public Snake() {
        heading = Direction.UP;
        body = new ArrayList<>();
    }

    List<Position> getBodyPositions() {
        return body.stream().map(part -> new Position(part.getX(), part.getY())).collect(Collectors.toList());
    }

    DirectedPosition getHead() {
        return body.get(0);
    }

    DirectedPosition getTail() {
        return body.get(body.size() - 1);
    }

    boolean setHeading(Direction heading) {
        if (heading == null || heading.inverse() == this.heading) return false;

        this.heading = heading;
        return true;
    }

    void move() {
        Direction previousHeading = getHead().getDirection();

        // turn head
        body.set(0, getHead().translate(heading));

        // turn the rest of the snake
        for (int i = 1; i < body.size(); i++) {
            DirectedPosition bodyPart = body.get(i);
            Direction tempDirection = bodyPart.getDirection();
            body.set(i, bodyPart.translate(previousHeading));

            previousHeading = tempDirection;
        }
    }

    boolean isInSnake(Position pos) {
        return body.stream().anyMatch(part -> part.isAt(pos));
    }

    void appendSnake() {
        DirectedPosition newTail =
                new DirectedPosition(getTail().translate(getTail().getDirection().inverse()), getTail().getDirection());

        body.add(newTail);
    }

    @Override
    public void draw(Graphics g) {
        body.forEach(part -> part.draw(g));
    }
}
