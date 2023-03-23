package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        try {
            runRight = ImageIO.read(getClass().getResourceAsStream("/player/runRight.gif"));
            runLeft = ImageIO.read(getClass().getResourceAsStream("/player/runLeft.gif"));
            idle = ImageIO.read(getClass().getResourceAsStream("/player/idle.gif"));
        }catch(IOException e) {
            System.out.println(e);
        }
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
        BufferedImage image = switch (action) {
            case "idle" -> idle;
            case "runLeft" -> runLeft;
            case "runRight" -> runRight;
            default -> null;
        };
        graphics2D.drawImage(image, positionX, positionY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
