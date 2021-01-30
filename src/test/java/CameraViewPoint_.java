import org.junit.Before;
import org.junit.Test;
import refactoring.Camera;
import refactoring.CameraViewPoint;
import refactoring.ImageProcessor;
import refactoring.ViewPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CameraViewPoint_ {

    private CameraViewPoint initialViewPoint ;

    @Before
    public void setUp(){
        Camera camera = mock(Camera.class);
        ImageProcessor imageProcessor = mock(ImageProcessor.class);
        initialViewPoint = new CameraViewPoint(camera, imageProcessor);
    }

    @Test
    public void when_turning_left_should_return_a_new_point(){
        ViewPoint viewPoint = initialViewPoint.turnLeft();
        assertThat(viewPoint).isNotNull();
        assertThat(viewPoint).isNotEqualTo(initialViewPoint);
        verify(initialViewPoint.getCamera()).turnLeft(90);
    }
}
