package some.canvas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SVGWriter {
    private File file;
    private FileWriter fileWriter;

    public SVGWriter(String fileName) {
        try {
            file = new File(fileName + ".svg");
            if (!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            System.err.println("Failed to open the file :c");
            e.printStackTrace();
        }
    }

    public void fillHead() throws IOException {
        fileWriter.write("<?xml version=\"1.0\"?>\n<svg width=\"800\" height=\"800\"\nxmlns=\"http://www.w3.org/2000/svg\">\n");
    }

    public void drawRect(int x, int y, int width, int height, String colorFill, String colorBorder) throws IOException {
        String query = String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"fill:%s;stroke:%s\"/>\n",
                                        x, y, width, height, colorFill, colorBorder);
        fileWriter.write(query);
    }

    public void drawRoundRect(int x, int y, int width, int height, String colorFill, String colorBorder) throws IOException {
        String query = String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" rx=\"5\" ry=\"25\" style=\"fill:%s;stroke:%s\"/>\n",
                                        x, y, width, height, colorFill, colorBorder);
        fileWriter.write(query);
    }

    public void drawEllipse(int cx, int cy, int rx, int ry, String colorFill, String colorBorder) throws IOException{
        String query = String.format("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" style=\"fill:%s;stroke:%s\" stroke-width=\"1\"/>\n",
                                        cx, cy, rx, ry, colorFill, colorBorder);
        fileWriter.write(query);
    }

    public void fillEndOfFile() throws IOException{
        fileWriter.write("</svg>");

        fileWriter.flush();
        fileWriter.close();
    }
}
