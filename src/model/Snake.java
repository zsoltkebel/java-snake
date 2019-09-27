package model;

import ui.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Snake implements Drawable {

    private Direction heading;
    public List<DirectedPosition> body;

    public Snake() {
        this(Position.spawn(new ArrayList<>()), 1, Direction.values()[new Random().nextInt(4)]);
    }

    public Snake(Position position, int length, Direction heading) {
        this.heading = heading;
        this.body = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            body.add(new DirectedPosition(position, heading));
            position = position.translate(heading.inverse());
        }
    }

    public List<Position> getBodyPositions() {
        return body.stream().map(part -> new Position(part.getX(), part.getY())).collect(Collectors.toList());
    }

    public DirectedPosition getHead() {
        return body.get(0);
    }

    public DirectedPosition getTail() {
        return body.get(body.size() - 1);
    }

    public boolean setHeading(Direction heading) {
        if (heading == null || heading.inverse() == this.heading) return false;

        this.heading = heading;
        return true;
    }

    public void move() {
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

    public void appendSnake() {
        DirectedPosition newTail =
                new DirectedPosition(getTail().translate(getTail().getDirection().inverse()), getTail().getDirection());

        body.add(newTail);
    }

    @Override
    public void draw(Graphics g) {
        body.forEach(part -> drawBodyPart(g, part));
    }

    private void drawBodyPart(Graphics g, DirectedPosition directedPosition) {
        Color drawColor = g.getColor();

        g.setColor(Color.GREEN);
        int x = directedPosition.getX() * Board.cellSize + Board.cellPadding;
        int y = directedPosition.getY() * Board.cellSize + Board.cellPadding;
        int rectSize = Board.cellSize - 2 * Board.cellPadding;

        g.fillRect(x, y, rectSize, rectSize);

        g.setColor(drawColor);
    }
}
