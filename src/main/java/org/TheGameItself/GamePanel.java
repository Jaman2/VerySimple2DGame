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

    final int FPSCap = 60;

    //player here for now :)
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000.0/FPSCap;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        if(keyHandler.upPressed && playerY>playerSpeed)
        {
            playerY -= playerSpeed;
        }
        if(keyHandler.downPressed && playerY<(screenHeight-tileSize))
        {
            playerY += playerSpeed;
        }

        if(keyHandler.leftPressed && playerX>playerSpeed)
        {
            playerX -= playerSpeed;
        }
        if(keyHandler.rightPressed && playerX<(screenWidth-tileSize))
        {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.cyan);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
