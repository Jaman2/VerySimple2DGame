package Tiles;

import Utilities.GamePanel;
import Utilities.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] map;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        this.tiles = new Tile[4];
        loadTileImages();
        loadMapArray("map1");
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

    private void loadMapArray(String mapName) {
        FileHandler files = new FileHandler();
        map = files.readMapAsArrayFromFile("maps/" + mapName + ".txt", gp.maxWorldRows, gp.maxWorldColumns);
    }

    public void draw(Graphics2D g2) {
        for(int i=0; i<gp.maxWorldRows; i++) {
            for(int j=0; j<gp.maxWorldColumns; j++) {
                int worldX = i *gp.tileSize;
                int worldY = j *gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                int tileCode = map[i][j]%100;
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tiles[tileCode].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
