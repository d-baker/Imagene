package GUI; /**
 * Created by avishkar on 10/29/2016.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

        this.coordinate= SettingPanel.strHold1;
        this.symmetry= SettingPanel.strHold2;
        this.imageWidth= SettingPanel.default_imageWidth;
        this.imageHeight= SettingPanel.default_imageHeight;

        System.out.println("\n\nafter save button \n");
        System.out.println("coordinate " +coordinate);
        System.out.println("symmetry: " +symmetry);
        System.out.println("image width " +imageWidth);
        System.out.println("image height " +imageHeight);

    }


    // function to delete  later
    //test function
    public void testImagesToProcess()
    {
        JPanel panel=new JPanel();
        JLabel label1=new JLabel(image1_1);
        JLabel label2=new JLabel(image2_1);
        JLabel label3=new JLabel(coordinate);
        JLabel label4=new JLabel(symmetry);
        JLabel label5=new JLabel(String.valueOf(imageWidth));
        JLabel label6=new JLabel(String.valueOf(imageHeight));

        JFrame frame=new JFrame("About");

        panel.setLayout(new GridLayout(6,0));

        frame.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        panel.setBackground(Color.GRAY);

        frame.setVisible(true);
        frame.setTitle("test");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }




}
