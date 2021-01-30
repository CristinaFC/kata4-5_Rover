package refactoring;

import java.awt.*;

public class CameraView {

    private Image image;

    public CameraView(Image image) {
        this.image = image;
    }

    public Image image() {
        return image;
    }
}
