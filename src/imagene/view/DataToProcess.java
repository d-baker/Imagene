package imagene.view;

/*****************************************
 * Written by Avishkar Giri (s3346203)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.sun.javafx.fxml.expression.Expression.add;

public class DataToProcess {

    private String coordinate;
    private String symmetry;
    private int imageWidth;
    private int imageHeight;
    private BufferedImage image1;
    private BufferedImage image2;
    private ImageIcon image1_1;
    private ImageIcon image2_1;

    public DataToProcess(ImageIcon image1_1, ImageIcon image2_1)
    {
        this.image1_1=image1_1;
        this.image2_1=image2_1;
        Image image_getImage1=image1_1.getImage();
        Image image_getImage2=image2_1.getImage();
        image1=toBufferedImage(image_getImage1);
        image2=toBufferedImage(image_getImage2);
        this.coordinate=SettingPanel.coordSetting;
        this.symmetry=SettingPanel.symmetrySetting;

        this.imageWidth= SettingPanel.default_imageWidth;
        this.imageHeight= SettingPanel.default_imageHeight;



        System.out.println("\n\nafter save button \n");
        System.out.println("coordinate " + SettingPanel.coordSetting);
        System.out.println("symmetry: " + SettingPanel.symmetrySetting);
        System.out.println("image width " + imageWidth);
        System.out.println("image height " + imageHeight);

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



}
