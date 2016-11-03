package imagene.view;

import imagene.arithmeticParser.ParserInterface;
import imagene.arithmeticParser.SampleFormulaGenerator;
import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;
import imagene.imagegen.api.ProgramInterface;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;
import imagene.viewmodel.ImageneViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

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

    private ImageneViewModel viewModel;

    public ImageHolder(ImageneViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void generateRealImages(List<PixelMatrix> pixelMatrices, int width, int height) {
        for (int i = 0; i < ARRAY_INDEX; i++) {
            // TODO getting index out of bounds here when using real watchmaker, why?
            image[i] = makeImage(width, height, pixelMatrices.get(i));
            this.image[i] = image[i];
            imageIcon[i] = new ImageIcon(image[i]);
        }

        resizeAllImages();
    }

    // Creates the BufferedImage from a pixel matrix, for display in the view
    public BufferedImage makeImage(int width, int height, PixelMatrix m)
    {
        int[] data = m.getIntArray();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);

        return image;
    }

    public void generateDummyImages() {
        try {
            image1[0] = ImageIO.read(ResourceLoader.load("imagene/view/resources/individual.1.png"));
            image1[1] = ImageIO.read(ResourceLoader.load("imagene/view/resources/individual.2.png"));
            image1[2] = ImageIO.read(ResourceLoader.load("imagene/view/resources/individual.3.png"));
            image1[3] = ImageIO.read(ResourceLoader.load("imagene/view/resources/individual.4.png"));
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
