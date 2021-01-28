import org.junit.Test;
import refactoring.Obstacle;
import refactoring.Rover;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static refactoring.Rover.Heading.*;
import static refactoring.Rover.Order.*;
import static refactoring.Rover.Position;

public class Rover__ {


    @Test
    public void could_be_initialized_with_legacy_constructor(){
        assertThat(new Rover("N",5,5).heading()).isEqualTo(North);
        assertThat(new Rover("North",5,5).heading()).isEqualTo(North);
        assertThat(new Rover("North",5,5).position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_be_initialized_using_new_constructor(){
        assertThat(new Rover(North,new Position(5,5)).position()).isEqualTo(new Position(5,5));
        assertThat(new Rover(North,5,5).position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_turn_left(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go(Left);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_turn_right(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go(Right);
        assertThat(rover.heading()).isEqualTo(East);
        assertThat(rover.position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_go_forward(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go(Forward);
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(5,6));
    }

    @Test
    public void could_go_backward(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go(Backward);
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(5,4));
    }

    @Test
    public void could_execute_many_orders(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(4,5));
    }

    @Test
    public void could_execute_legacy_instructions(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go("BLFRF");
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(4,5));
    }

    @Test
    public void could_ignore_legacy_instructions(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.go("BL*FRF");
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(4,5));
    }

    @Test
    public void could_not_go_backward_if_there_is_an_obstacle_behind(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.addObstacles(new Obstacle(new Position(5,4)));
        rover.go(Backward);
        assertThat(rover.position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_not_go_backward_if_there_is_an_obstacle_in_front(){
        Rover rover = new Rover(North,new Position(5,5));
        rover.addObstacles(new Obstacle(new Position(5,6)));
        rover.go(Forward);
        assertThat(rover.position()).isEqualTo(new Position(5,5));
    }
}
