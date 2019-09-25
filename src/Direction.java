public enum Direction {

    UP,
    RIGHT,
    DOWN,
    LEFT;

    Direction inverse() {
        switch (this) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                return null;
        }
    }
}
