package Entities;

import Utilities.FileHandler;
import Utilities.GamePanel;
import Utilities.KeyHandler;

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
        idle = files.getImagesAsArray("/player/idle/", "frame0", ".png", 10);
        runLeft = files.getImagesAsArray("/player/runLeft/", "frame0", ".png", 10);
        runRight = files.getImagesAsArray("/player/runRight/", "frame0", ".png", 10);
    }
    public void setDefaultValues() {
        positionX = gamePanel.tileSize;
        positionY = gamePanel.tileSize;
        movementSpeed = 3;
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
