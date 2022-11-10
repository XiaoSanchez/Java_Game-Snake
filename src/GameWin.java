
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
public class GameWin extends JFrame {
    public static int state = 0;
    public int score = 0;
    Image offScreenImage = null;
    int winWidth = 800;
    int winHeight = 600;
    public HeadObj headObj = new HeadObj(GameUtils.rightImg, 60, 570, this);
    public List<BodyObj> bodyObjList = new ArrayList<>();
    public FoodObj foodObj = new FoodObj().getFood();
    public void launch() {
        this.setVisible(true);
        this.setSize(winWidth, winHeight);
        this.setLocationRelativeTo(null);
        this.setTitle("Snake");
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 0, 570, this));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    switch (state) {
                        case 0:
                            state = 1;
                            break;
                        case 1:
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            state = 1;
                            break;
                        case 3:
                            state = 5;
                            break;
                        case 4:
                            state = 6;
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        while (true) {
            if (state == 1) {
                repaint();
            }
            if (state == 5) {
                state = 0;
                resetGame();
            }
            if (state == 6 && GameUtils.level != 3) {
                state = 1;
                GameUtils.level++;
                resetGame();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(winWidth, winHeight);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(Color.white);
        gImage.fillRect(0, 0, winWidth, winHeight);
        gImage.setColor(Color.black);
        for (int i = 0; i <= 20; i++) {
            gImage.drawLine(0, i * 30, 600, i * 30);
            gImage.drawLine(i * 30, 0, i * 30, 600);
        }
        for (int i = bodyObjList.size() - 1; i >= 0; i--) {
            bodyObjList.get(i).paintSelf(gImage);
        }
        headObj.paintSelf(gImage);
        foodObj.paintSelf(gImage);
        GameUtils.drawWord(gImage, "No. " + GameUtils.level, Color.BLUE, 40, 650, 260);
        GameUtils.drawWord(gImage, "Score: " + score, Color.BLUE, 30, 640, 330);
        gImage.setColor(Color.white);
        prompt(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    void prompt(Graphics g) {
        if (state == 0) {
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Press [Space] to Start", Color.DARK_GRAY, 35, 150, 290);
        }
        if (state == 2) {
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Press [Space] to Continue", Color.DARK_GRAY, 35, 150, 290);
        }
        if (state == 3) {
            g.fillRect(120, 240, 400, 70);
            GameUtils.drawWord(g, "Fail, Press [Space] to Restart", Color.red, 35, 150, 290);
        }
        if (state == 4) {
            g.fillRect(120, 240, 400, 70);
            if (GameUtils.level == 3) {
                GameUtils.drawWord(g, "Congratulations,You Win!", Color.green, 35, 150, 290);
            } else {
                GameUtils.drawWord(g, "Press [Space] to Next", Color.green, 35, 150, 290);
            }
        }
    }
    void resetGame() {
        this.dispose();
        String[] args = {};
        main(args);
    }
    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
