package some.canvas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGWriter {
    private File file;
    private FileWriter fileWriter;

    public SVGWriter(String fileName) {
        try {
            file = new File(fileName + ".svg");
            if (!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter();
        } catch (IOException e) {
            System.err.println("Failed to open the file :c");
            e.printStackTrace();
        }
    }

    void fillHead() throws IOException{
        fileWriter.write("<?xml version=\"1.0\"?>\n<svg width=\"800\" height=\"800\"\nxmlns=\"http://www.w3.org/2000/svg\">");
    }

    void drawRect(int x, int y, int width, int height, String colorFill, String colorBorder){
        String.format("<rect x=\"%d\" y=\"%d\" width=\"100\" height=\"50\" style=\"fill:yellow;stroke:red\"/>");
    }
}
