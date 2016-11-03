package imagene.gui;

import imagene.arithmeticParser.ParserInterface;
import imagene.arithmeticParser.SampleFormulaGenerator;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;
import imagene.imagegen.api.ProgramInterface;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/*****************************************
 * Written by Avishkar Giri (s3346203),
 * Dorothea Baker (s3367422)
 * and Andrew Sanger (s3440468)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class ImageHolder implements ConstantArrayField {
    private BufferedImage[] image=new BufferedImage[ARRAY_INDEX];
    private BufferedImage[] image1=new BufferedImage[ARRAY_INDEX];
    private Image[] imageResized=new Image[ARRAY_INDEX];
    private ImageIcon [] imageIcon=new ImageIcon[ARRAY_INDEX];
    private ImageIcon [] imageIconResized=new ImageIcon[ARRAY_INDEX];

    public void generateRealImages() {
        PixelMatrix pixelMatrix;
        String[][] formulas;

        // TODO population probably shouldn't be hardcoded here, but it'll do for now
        int POPULATION_SIZE = 4;

        /* TODO for some reason when setting to the user-defined dimensions,
        pixel matrix throws index out of bounds exception */
        int WIDTH = 200;
        int HEIGHT = 200;
        //int WIDTH = SettingPanel.default_imageWidth;
        //int HEIGHT = SettingPanel.default_imageHeight;

        // dummy formula generator (will eventually be replaced with watchmaker calls)
        SampleFormulaGenerator gen = new SampleFormulaGenerator();
        formulas = gen.getFormulaArray(POPULATION_SIZE);

        // ImageGen
        IProgramInterface api = new ProgramInterface();

        // Arithmetic parser
        ParserInterface arithParser = ParserInterface.getInstance();

        ArrayList<PixelMatrix> matrices = new ArrayList<PixelMatrix>();

        for (int a = 0; a < formulas.length; a++) {
            String formula1 = formulas[a][0];
            String formula2 = formulas[a][1];
            String formula3 = formulas[a][2];

            try {
                final ArithmeticNode node1 = arithParser.getArithmetic(formula1);
                final ArithmeticNode node2 = arithParser.getArithmetic(formula2);
                final ArithmeticNode node3 = arithParser.getArithmetic(formula3);

                IManipulator[] channels = new IManipulator[] {
                        (x, y) -> node1.operation(x, y),
                        (x, y) -> node2.operation(x, y),
                        (x, y) -> node3.operation(x, y)
                };

                pixelMatrix = api.CreateImage(WIDTH, HEIGHT, channels);
                matrices.add(pixelMatrix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < ARRAY_INDEX; i++) {
            image[i] = makeImage(WIDTH, HEIGHT, matrices.get(i));
            this.image[i] = image[i];
            imageIcon[i] = new ImageIcon(image[i]);
        }

        resizeAllImages();
    }

    // Creates the BufferedImage from a pixel matrix, for display in the gui
    public BufferedImage makeImage(int width, int height, PixelMatrix m)
    {
        int[] data = m.getIntArray();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);

        return image;
    }

    public void generateDummyImages() {
        try {
            image1[0] = ImageIO.read(ResourceLoader.load("res/individual.1.png"));
            image1[1] = ImageIO.read(ResourceLoader.load("res/individual.2.png"));
            image1[2] = ImageIO.read(ResourceLoader.load("res/individual.3.png"));
            image1[3] = ImageIO.read(ResourceLoader.load("res/individual.4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageIcon[0]=new ImageIcon(image1[0]);
        imageIcon[1]=new ImageIcon(image1[1]);
        imageIcon[2]=new ImageIcon(image1[2] );
        imageIcon[3]=new ImageIcon(image1[3]);

        resizeAllImages();
    }


    public ImageIcon resize(ImageIcon image)
    {
        Image image_hold;

        ImageIcon temp;

        image_hold = image.getImage().getScaledInstance(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight, Image.SCALE_DEFAULT);
        temp=new ImageIcon(image_hold);

        return temp;
    }


    public void resizeAllImages()
    {
        for(int i=0;i<ARRAY_INDEX;i++) {
            imageResized[i] =  imageIcon[i].getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            imageIconResized[i]=new ImageIcon(imageResized[i]);
        }
    }

    public ImageIcon[] returnImageIcon()
    {
        return imageIconResized;
    }
}
