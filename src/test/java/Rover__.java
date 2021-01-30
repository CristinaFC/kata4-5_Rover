import org.junit.Test;
import refactoring.Rover;
import refactoring.SimpleViewPoint;


import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.Rover.Order.*;
import static refactoring.SimpleViewPoint.Heading.*;
import static refactoring.SimpleViewPoint.Position;

public class Rover__ {

    @Test
    public void could_turn_left(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go(Left);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(West);
        assertThat(((SimpleViewPoint)rover.getViewPoint()).getPosition()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_turn_right(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go(Right);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(East);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(5,5));

    }

    @Test
    public void could_go_forward(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go(Forward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(5,6));
    }

    @Test
    public void could_go_backward(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go(Backward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(5,4));
    }

    @Test
    public void could_execute_many_orders(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(4,5));
    }

    @Test
    public void could_execute_legacy_instructions(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go("BLFRF");
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(4,5));
    }

    @Test
    public void could_ignore_legacy_instructions(){
        Rover rover = new Rover(new SimpleViewPoint(North, new Position(5,5)));
        rover.go("BL*FRF");
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(North);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(4,5));
    }

}
