package Utilities;

import Entities.Player;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //settings for screen
    final int originalTileSize = 32;
    final int scale = 2;

    final public int tileSize = originalTileSize * scale;
    final public int maxScreenColumns = 16;
    final public int maxScreenRows = 12;
    final public int screenWidth = tileSize * maxScreenColumns;
    final public int screenHeight = tileSize * maxScreenRows;
    final public int maxWorldColumns = 50;
    final public int  maxWorldRows = 50;
    final int FPSCap = 60;
    public int frame;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
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
        long timer = 0;
        frame = 0;
        //tileM.regenerateMap();
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                frame++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + frame);
                System.out.println("Player Y: " + player.worldY);
                System.out.println("Player X: " + player.worldX);
                frame = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
