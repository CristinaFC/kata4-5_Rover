import org.junit.Test;
import refactoring.SimpleViewPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.SimpleViewPoint.Heading.North;

public class ViewPoint_ {

    @Test
    public void could_be_initialized_with_legacy_constructor(){
        assertThat(new SimpleViewPoint("N" ,5,5).getHeading()).isEqualTo(North);
        assertThat(new SimpleViewPoint("North" ,5,5).getHeading()).isEqualTo(North);
        assertThat(new SimpleViewPoint("North" ,5,5).getPosition()).isEqualTo(new SimpleViewPoint.Position(5,5));
    }

    @Test
    public void could_be_initialized_using_new_constructor(){
        assertThat(new SimpleViewPoint(North ,new SimpleViewPoint.Position(5,5)).getPosition()).isEqualTo(new SimpleViewPoint.Position(5,5));
        assertThat(new SimpleViewPoint("North" ,5,5).getPosition()).isEqualTo(new SimpleViewPoint.Position(5,5));
    }
}
