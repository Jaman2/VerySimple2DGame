package Utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class FileHandler {
    public BufferedImage[] getImagesAsArray(String filesLocation, String filesPrefix, String filesSuffix, int amount) {
        BufferedImage[] output = new BufferedImage[amount];
        try {
            for (int i = 0; i<amount;i++) {
                String temp = filesLocation + filesPrefix + i + filesSuffix;
                output[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(temp)));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return output;
    }
    public int[][] readMapAsArrayFromFile(String filePath, int numRows, int numCols){
        int[][] array = new int[numRows][numCols];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] rowValues = line.split(",");
                for (int col = 0; col < rowValues.length; col++) {
                    array[row][col] = Integer.parseInt(rowValues[col]);
                }
                row++;
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }



    //Used to write new map, temporary.
    public void writeArrayToFile(int[][] array, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                writer.write(Integer.toString(array[i][j]));
                if (j < array[i].length - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }
        writer.close();
    }
}
