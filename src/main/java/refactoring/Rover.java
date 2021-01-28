package refactoring;

import java.util.*;

import static refactoring.Rover.Heading.*;

public class Rover {

	private Position position;
	private Heading heading;
	private final Map<Order, Action> actions = new HashMap<>();
	static Map<Position,Obstacle> obstacles = new HashMap<>();

	{
		actions.put(Order.Forward, () -> position = position.forward(heading));
		actions.put(Order.Backward, () -> position = position.backward(heading));
		actions.put(Order.Right, () -> heading = heading.turnRight());
		actions.put(Order.Left, () -> heading = heading.turnLeft());
	}

	public Rover(String facing, int x, int y) {
		this(Heading.of(facing), x,y);
	}

	public Rover(Heading heading, int x, int y) {
		this(heading, new Position(x,y));
	}

	public Rover(Heading heading, Position position){
		this.position = position;
		this.heading = heading;
	}

	public void addObstacles(Obstacle...obstacles){
		Arrays.stream(obstacles).forEach(obs -> this.obstacles.put(obs.getPosition(),obs));
	}

	public boolean detectedNextObstacle(){
		return !obstacles.containsKey(position().forward(heading));
	}

	public boolean detectedBackObstacle(){
		return !obstacles.containsKey(position().backward(heading));
	}

	public Heading heading() {
		return heading;
	}

	public Position position(){
		return position;
	}

	public void go(String instructions){
		Arrays.stream(instructions.split(""))
				.map(Order::of)
				.filter(Objects::nonNull)
				.forEach(order -> actions.get(order).execute());
	}

	public void go(Order... orders){
		for (Order order: orders) {
			if (order == Order.Forward && detectedNextObstacle()) return;
			if (order == Order.Backward && detectedNextObstacle()) return;
			actions.get(order).execute();
		}
	}

	public static class Position {
		private int x;
		private int y;

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

	public enum Order {
		Forward, Backward, Left, Right;

		public static Order of(char label){
			if (label == 'F') return Forward;
			if (label == 'B') return Backward;
			if (label == 'L') return Left;
			if (label == 'R') return Right;
			return null;
		}
		public static Order of(String label){
			return of(label.charAt(0));
		}

	}

	@FunctionalInterface
	private interface Action { void execute(); }

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

