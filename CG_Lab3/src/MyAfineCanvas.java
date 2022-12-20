import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.lang.Math;

public class MyAfineCanvas extends Canvas {

    final static int width = 960;
    final static int height = 960;
    final int x2 = 480;
    final int y2 = 480;
    final int angle = 80;

    public static void main(String[] args) {
        Frame myFrame = new Frame("Draw shape and text on Canvas");
        final Canvas picture = new MyAfineCanvas();

        myFrame.add(picture);

        myFrame.setSize(width, height);
        myFrame.setVisible(true);
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                savePicture(picture);
                System.exit(0);
            }
        });

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.BLUE);
        try(FileReader reader = new FileReader("src/DS7.txt");
            Scanner info = new Scanner(reader))
        {
            double[][] transformedMatrix = MatrixOperation.createRotationMatrixAroundPoint(angle, x2, y2);
            while(info.hasNextLine()){
                String[] someString = info.nextLine().split(" ");
                double[][] matrixOfCoordinate = {
                        {Integer.parseInt(someString[1]), Integer.parseInt(someString[0]), 1}};
                double[][] finalMatrix = MatrixOperation.multiplyMatrix(matrixOfCoordinate, transformedMatrix);

                g2.fillRect((int) Math.round(finalMatrix[0][0]), height - (int) Math.round(finalMatrix[0][1]),1,1);
                g2.fillRect((int) finalMatrix[0][0], height - (int) finalMatrix[0][1],1,1);
            }
        } catch (IOException e){
            System.out.println("Помилка вводу-виводу: " + e);
        }
    }

    public static void savePicture(Canvas picture) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = (Graphics2D)image.getGraphics();

        picture.paint(g2);

        try {
            ImageIO.write(image, "png", new File("MyCanvas.png"));
        } catch (IOException e) {
            System.out.println("Помилка в збереженні зображення: " + e);
        }
    }
}