package refactoring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.Objects;


import static refactoring.Rover.Order.*;

public class Rover {

	private ViewPoint viewPoint;

	private Map<Order, Action> actions = new HashMap<>();

	{
		actions.put(Forward, ViewPoint::forward);
		actions.put(Backward,ViewPoint::backward);
		actions.put(Right, ViewPoint::turnRight);
		actions.put(Left, ViewPoint::turnLeft);
	}

	public Rover(ViewPoint viewPoint){
		this.viewPoint = viewPoint;
	}

	public ViewPoint getViewPoint() {
		return viewPoint;
	}

	public void set(ViewPoint viewPoint){
		if (viewPoint == null) return;
		this.viewPoint = viewPoint;
	}
	public void go(String instructions){
		set(go(Arrays.stream(instructions.split("")).map(Order::of)));
	}

	public void go(Order... orders){
		set(go(Arrays.stream(orders)));
	}

	private ViewPoint go(Stream<Order> orders){
		return orders.filter(Objects::nonNull)
				.reduce(this.viewPoint, this::execute, (v1,v2) -> null);
	}

	private ViewPoint execute(ViewPoint v, Order order) {
		if(v == null) return null;
		return actions.get(order).execute(v);
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
	private interface Action { ViewPoint execute(ViewPoint v); }

}

