package refactoring;

import java.util.HashMap;
import java.util.Map;

import static refactoring.SimpleViewPoint.Heading.*;

public class SimpleViewPoint implements ViewPoint{
    private Position position;
    private Heading heading;

    public SimpleViewPoint(String facing, int x, int y){
        position = new Position(x,y);
        heading = Heading.of(facing);
    }

    public SimpleViewPoint(Heading heading, int x, int y) {
        this.heading = heading;
        position = new Position(x,y);
    }

    public SimpleViewPoint(Heading heading, Position position){
        this.position = position;
        this.heading = heading;
    }

    public Position getPosition() {
        return position;
    }

    public Heading getHeading() {
        return heading;
    }

    @Override
    public ViewPoint forward() {
        position = position.forward(heading);
        return this;
    }

    @Override
    public ViewPoint backward() {
        position = position.backward(heading);
        return this;
    }

    @Override
    public ViewPoint turnLeft() {
        heading = heading.turnLeft();
        return this;
    }

    @Override
    public ViewPoint turnRight() {
        heading = heading.turnRight();
        return this;
    }

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position forward(Heading heading) {
            if(heading == North) return new Position(x,y+1);
            if(heading == South) return new Position(x,y-1);
            if(heading == East) return new Position(x+1,y);
            if(heading == West) return new Position(x-1,y);
            return null;
        }

        public Position backward(Heading heading){
            if(heading == North) return new Position(x,y-1);
            if(heading == South) return new Position(x,y+1);
            if(heading == East) return new Position(x-1,y);
            if(heading == West) return new Position(x+1,y);
            return this;
        }



        @Override
        public boolean equals(Object object) {
            return isSameClass(object) && equals((Position) object);
        }

        private boolean equals(Position position) {
            return position == this || (x == position.x && y == position.y);
        }

        private boolean isSameClass(Object object) {
            return object != null && object.getClass() == Position.class;
        }

    }

    public enum Heading {
        North, East, South, West;
        static Map<Character, Heading> headings = new HashMap<>();

        static {
            headings.put('N',North);
            headings.put('E',East);
            headings.put('S',South);
            headings.put('W',West);
        }

        public static Heading of(String label) {
            return of(label.charAt(0));
        }

        public static Heading of(char label) {
            return headings.get(label);
        }

        public Heading turnRight() {
            return values()[add(+1)];
        }

        public Heading turnLeft() {
            return values()[add(-1)];
        }

        private int add(int offset) {
            return (this.ordinal() + offset + values().length) % values().length;
        }

    }

}
