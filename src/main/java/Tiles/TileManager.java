package Tiles;

import Utilities.GamePanel;
import Utilities.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] map;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        this.tiles = new Tile[3];
        loadTileImages();
        loadMapArray();
    }

    private void loadTileImages() {
        FileHandler files = new FileHandler();
        BufferedImage[] images = files.getImagesAsArray("/tiles/", "tile", ".png", 3);
        for(int i=0; i<images.length; i++)
        {
            tiles[i] = new Tile();
            tiles[i].image = images[i];
        }
    }

    private void loadMapArray() {
        FileHandler files = new FileHandler();
        map = files.readMapAsArrayFromFile("maps/map1.txt", gp.maxScreenRows, gp.maxScreenColumns);
    }

    public void draw(Graphics2D g2) {
        int posX, posY,tileCode;
        for(int i=0; i<gp.maxScreenRows; i++) {
            for(int j=0; j<gp.maxScreenColumns; j++) {
                posX = j*gp.tileSize;
                posY = i*gp.tileSize;
                tileCode = map[i][j]%100;
                g2.drawImage(tiles[tileCode].image, posX, posY, gp.tileSize, gp.tileSize, null);
            }
        }
    }


    //a temporary solution to generate new map
    public void regenerateMap() {
        int[][] result = new int[gp.maxScreenRows][gp.maxScreenColumns];
        for(int i = 0;i<gp.maxScreenRows; i++) {
            for(int j = 0; j<gp.maxScreenColumns; j++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
                result[i][j] = 100+randomNum;
            }
        }
        try {
            FileHandler files = new FileHandler();
            files.writeArrayToFile(result, "maps/map1.txt");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
