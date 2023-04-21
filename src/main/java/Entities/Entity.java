package Entities;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, movementSpeed;
    public BufferedImage [] runRight, runLeft, idle;
    public String action, lastAction;
}
