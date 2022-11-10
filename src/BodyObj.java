
import java.awt.*;
public class BodyObj extends GameObj {
    public BodyObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
    @Override
    public String toString() {
        return "BodyObj{" +
                "img=" + img +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
