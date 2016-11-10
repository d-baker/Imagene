package imagene.view;
import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.imagegen.models.PixelMatrix;
import imagene.viewmodel.ImageneViewModel;
import imagene.watchmaker.UnexpectedParentsException;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * and Dorothea
 * and Andrew
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;

public class View_ViewModel_Integration implements ConstantArrayField{

    private String coordinate;
    private String symmetry;
    private int imageWidth;
    private int imageHeight;
    private BufferedImage image1;
    private BufferedImage image2;
    private ImageIcon image1_1;
    private ImageIcon image2_1;
    private ImageneViewModel viewModel;
    private int x;
    private int y;
    private ImageneViewModelObject object;

    private BufferedImage[] image=new BufferedImage[ARRAY_INDEX];
    private ImageIcon [] imageIcon=new ImageIcon[ARRAY_INDEX];
    private Image[] imageResized=new Image[ARRAY_INDEX];
    private ImageIcon [] imageIconResized=new ImageIcon[ARRAY_INDEX];

    public View_ViewModel_Integration()

    {

        //this.viewModel=VIEWMODEL;
        object=new ImageneViewModelObject();
        viewModel=object.getViewModel();
       // viewModel.chooseWinners(new int[]{0,1});
        initiateImages(0,1);
        imageWidth=SettingPanel.default_imageWidth;
        imageHeight=SettingPanel.default_imageHeight;
        setFormula( );


    }

    public void convertImageToBufferedImage()
    {
        Image image_getImage1=image1_1.getImage();
        Image image_getImage2=image2_1.getImage();
        image1=toBufferedImage(image_getImage1);
        image2=toBufferedImage(image_getImage2);
    }

    public void setGeneration()
    {
        try {
            viewModel.newGeneration();
        } catch (UnexpectedParentsException e) {
            e.printStackTrace();
        }
    }

    public void setFormula()
    {
        try {
            List<PixelMatrix> formulas = viewModel.getPopulation(imageWidth,imageHeight);
            generateRealImages(formulas, imageWidth, imageHeight);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (IncorrectVariablesException e) {
            e.printStackTrace();
        }
    }


    public void generateRealImages(List<PixelMatrix> pixelMatrices, int width, int height) {


        // i had to create new image_1 object to fix the error
        //earlier it was written this.image[i]=image[i];
        BufferedImage[] image_1=new BufferedImage[ARRAY_INDEX];
        for (int i = 0; i < ARRAY_INDEX; i++) {
            // TODO getting index out of bounds here when using real watchmaker, why?
            image_1[i] = makeImage(width, height, pixelMatrices.get(i));
            this.image[i] = image_1[i];
            imageIcon[i] = new ImageIcon(image[i]);
        }
       resizeAllImages();
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public void setSymmetry(String symmetry) {
        this.symmetry = symmetry;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImage1_1(ImageIcon image1_1) {
        this.image1_1 = image1_1;
    }

    public void setImage2_1(ImageIcon image2_1) {
        this.image2_1 = image2_1;
    }

    public BufferedImage makeImage(int width, int height, PixelMatrix m)
    {
        int[] data = m.getIntArray();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);

        return image;
    }





    public void initiateImages(int x,int y)
    {
        viewModel.chooseWinners(new int[]{x,y});
    }





    public void resizeAllImages()
    {
        BufferedImage[] temp=new BufferedImage[4];
        for(int i=0;i<ARRAY_INDEX;i++) {
            imageResized[i] =  imageIcon[i].getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

            temp[i]= View_ViewModel_Integration.toBufferedImage(imageResized[i]);
            imageIconResized[i]=new ImageIcon(temp[i]);
        }


    }

    public ImageIcon[] returnImageIcon()
    {
        return imageIconResized;
    }





    public static BufferedImage toBufferedImage(Image img) {

            if (img instanceof BufferedImage) {
                return (BufferedImage) img;
            }

            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();


            return bimage;



    }

    // function to delete  later
    //test function
    public static void testImagesToProcess(ImageIcon image1,ImageIcon image2,ImageIcon image3,ImageIcon image4)
    {
        JPanel panel=new JPanel();
        JLabel label1=new JLabel(image1);
        JLabel label2=new JLabel(image2);
        JLabel label3=new JLabel(image3);
        JLabel label4=new JLabel(image4);


        JFrame frame=new JFrame("About");

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;


        constraint.gridx=0;
        constraint.gridy=0;
        add(label1,constraint);

        constraint.gridx=0;
        constraint.gridy=1;
        add(label1,constraint);

        constraint.gridx=1;
        constraint.gridy=0;
        add(label1,constraint);

        constraint.gridx=1;
        constraint.gridy=1;
        add(label1,constraint);



        frame.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);


        panel.setBackground(Color.GRAY);

        frame.setVisible(true);
        frame.setTitle("test");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    // function to delete  later
    //test function
    public  void testImagesToProcess()
    {


        JPanel panel1=new JPanel();
        JLabel label1=new JLabel(image1_1);
        panel1.add(label1);

        JPanel panel2=new JPanel();
        JLabel label2=new JLabel(image2_1);
        panel2.add(label2);

        JPanel panel=new JPanel();

        JLabel label3=new JLabel(String.valueOf(imageWidth));
        JLabel label4=new JLabel(String.valueOf(imageHeight));
        JLabel label5=new JLabel(coordinate);
        JLabel label6=new JLabel(symmetry);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);



        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;


        constraint.gridx=0;
        constraint.gridy=0;
        add(panel,constraint);

        constraint.gridx=1;
        constraint.gridy=0;
        add(label3,constraint);

        constraint.gridx=2;
        constraint.gridy=0;
        add(label4,constraint);

        constraint.gridx=3;
        constraint.gridy=0;
        add(label5,constraint);

        constraint.gridx=4;
        constraint.gridy=0;
        add(label6,constraint);




        JFrame frame=new JFrame("About");
        frame.add(panel);

        panel.setBackground(Color.GRAY);

        frame.setVisible(true);
        frame.setTitle("test");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}