package refactoring;

import java.util.Random;

import static refactoring.Rover.Position;

public class Obstacle {

    private final Position position;

    public Obstacle(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
