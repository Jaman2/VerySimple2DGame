package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        setDefaultValues();
    }

    public void setDefaultValues() {
        positionX = 50;
        positionY = 50;
        movementSpeed = 5;
    }

    public void update() {
        if(keyHandler.upPressed && positionY>movementSpeed)
        {
            positionY -= movementSpeed;
        }
        if(keyHandler.downPressed && positionY<(gamePanel.screenHeight - gamePanel.tileSize))
        {
            positionY += movementSpeed;
        }

        if(keyHandler.leftPressed && positionX>movementSpeed)
        {
            positionX -= movementSpeed;
        }
        if(keyHandler.rightPressed && positionX<(gamePanel.screenWidth-gamePanel.tileSize))
        {
            positionX += movementSpeed;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.cyan);
        graphics2D.fillRect(positionX, positionY, gamePanel.tileSize, gamePanel.tileSize);
    }
}
