package model;

import ui.Board;

import java.util.List;
import java.util.Random;

public class Position {

    private int x, y;

    public static Position spawn(List<Position> excluded) {
        do {
            int x = new Random().nextInt(Board.cols - 1);
            int y = new Random().nextInt(Board.rows - 1);

            final Position position = new Position(x, y);
            if (excluded.stream().noneMatch(pos -> pos.isAt(position))) {
                return position;
            }
        } while (true);
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position translate(Direction direction) {
        int x = this.x;
        int y = this.y;

        switch (direction) {
            case UP:
                y -= 1;
                if (y < 0) y = Board.cols - 1;
                break;
            case RIGHT:
                x += 1;
                if (x >= Board.rows) x = 0;
                break;
            case DOWN:
                y += 1;
                if (y >= Board.cols) y = 0;
                break;
            case LEFT:
                x -= 1;
                if (x < 0) x = Board.rows - 1;
                break;
        }

        return new Position(x, y);
    }

    public Position copy() {
        return new Position(this);
    }

    public boolean isAt(Position otherPos) {
        return this.x == otherPos.x && this.y == otherPos.y;
    }

    boolean isAdjacentPosition(Position otherPos) {
        if ((Math.abs(otherPos.x - x) == 1 && Math.abs(otherPos.y - y) == 0) ||
                (Math.abs(otherPos.x - x) == 0 && Math.abs(otherPos.y - y) == 1) ||
                (Math.abs(otherPos.x - x) == Board.cols - 1 && Math.abs(otherPos.y - y) == 0) ||
                (Math.abs(otherPos.x - x) == 0 && Math.abs(otherPos.y - y) == Board.cols - 1)) {
            return true;
        } else {
            return false;
        }
    }

    Direction getDirectionTo(Position otherPos) {
        return null;
    }
}
