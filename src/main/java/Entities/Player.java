package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        setDefaultValues();
        getPlayerImage();
    }
    public void getPlayerImage () {
        FileHandler files = new FileHandler();
        idle = files.getImagesForAnimation("idle", 10);
        runLeft = files.getImagesForAnimation("runLeft", 10);
        runRight = files.getImagesForAnimation("runRight", 10);
    }
    public void setDefaultValues() {
        //Yes I'm not making anything usefull here, I could be deleted and defaults could be set in constructor, but whatever.
        positionX = 50;
        positionY = 50;
        movementSpeed = 5;
        action = "idle";
    }

    public void update() {
        boolean moved = false;
        if(keyHandler.upPressed && positionY>movementSpeed) {
            positionY -= movementSpeed;
            action = "runRight";
            moved = true;
        }
        if(keyHandler.downPressed && positionY<(gamePanel.screenHeight - gamePanel.tileSize)) {
            positionY += movementSpeed;
            action = "runLeft";
            moved = true;
        }

        if(keyHandler.leftPressed && positionX>movementSpeed) {
            positionX -= movementSpeed;
            action = "runLeft";
            moved = true;
        }
        if(keyHandler.rightPressed && positionX<(gamePanel.screenWidth-gamePanel.tileSize)) {
            positionX += movementSpeed;
            action = "runRight";
            moved = true;
        }

        if (!moved) {
            action = "idle";
        }
    }

    public void draw(Graphics2D graphics2D) {
        int whichFrameToDraw = (gamePanel.frame - 1) / 6;
        BufferedImage image = switch (action) {
            case "idle" -> idle[whichFrameToDraw];
            case "runLeft" -> runLeft[whichFrameToDraw];
            case "runRight" -> runRight[whichFrameToDraw];
            default -> null;
        };
        graphics2D.drawImage(image, positionX, positionY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
