package Entities;

import Utilities.FileHandler;
import Utilities.GamePanel;
import Utilities.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX, screenY;
    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        this.screenX =(gp.screenWidth/2) - (gp.tileSize/2);
        this.screenY = (gp.screenHeight/2) - (gp.tileSize/2);

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
        worldX = gamePanel.tileSize*20;
        worldY = gamePanel.tileSize*20;
        movementSpeed = 4;
        action = "idle";
        lastAction = "runRight";
    }

    public void update() {
        boolean moved = false;
        if(keyHandler.upPressed && worldY > movementSpeed) {
            worldY -= movementSpeed;
            action = lastAction;
            moved = true;
        }
        if(keyHandler.downPressed && worldY < (gamePanel.maxWorldRows * (gamePanel.tileSize - 1 ))) {
            worldY += movementSpeed;
            action = lastAction;
            moved = true;
        }

        if(keyHandler.leftPressed && worldX > movementSpeed) {
            worldX -= movementSpeed;
            action = "runLeft";
            lastAction = "runLeft";
            moved = true;
        }
        if(keyHandler.rightPressed && worldX < (gamePanel.maxWorldRows * gamePanel.tileSize)) {
            worldX += movementSpeed;
            action = "runRight";
            lastAction = "runRight";
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
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
