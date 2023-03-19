package org.TheGameItself;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //settings for screen
    final int originalTileSize = 32;
    final int scale = 2;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumns = 16;
    final int maxScreenRows = 12;
    final int screenWidth = tileSize * maxScreenColumns;
    final int screenHeight = tileSize * maxScreenRows;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null) {
            update();
            repaint();
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.cyan);
        g2.fillRect(tileSize*2, tileSize*2, tileSize, tileSize);
        g2.dispose();
    }
}
