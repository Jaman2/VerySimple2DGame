package Utilities;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateMap {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Set map size:");
        System.out.println("Number of rows: ");
        int rows = input.nextInt();
        input.nextLine();
        System.out.println("Number of columns: ");
        int columns = input.nextInt();
        input.nextLine();
        System.out.println("Set map name: ");
        String mapName = input.nextLine();
        generateMap(rows, columns, mapName);
        System.out.println("Done!");

    }
    public static void generateMap(int mapRows, int mapColumns, String mapName) {
    int[][] result = new int[mapRows][mapColumns];
    for(int i = 0;i<mapRows; i++) {
        for(int j = 0; j<mapColumns; j++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
            result[i][j] = 100+randomNum;
        }
    }
    try {
        FileHandler files = new FileHandler();
        files.writeArrayToFile(result, "maps/" + mapName + ".txt");
    }catch(IOException e) {
        e.printStackTrace();
    }
}
}
