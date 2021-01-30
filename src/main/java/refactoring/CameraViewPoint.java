package refactoring;

public class CameraViewPoint implements ViewPoint{
    private Camera camera;

    private ImageProcessor imageProcessor;

    public CameraViewPoint(Camera camera, ImageProcessor imageProcessor) {
        this.camera = camera;
        this.imageProcessor = imageProcessor;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public ViewPoint forward() {
        return null;
    }

    @Override
    public ViewPoint backward() {
        return null;
    }

    @Override
    public ViewPoint turnLeft() {
        camera.turnLeft(90);
        return new CameraViewPoint(camera,imageProcessor);
    }

    @Override
    public ViewPoint turnRight() {
        camera.turnRight(90);
        return new CameraViewPoint(camera,imageProcessor);
    }

}
