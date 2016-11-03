package GUI; /**
 * Created by avishkar on 10/26/2016.
 */
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
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by avishkar on 10/26/2016.
 */
public class ImageHolder implements ConstantArrayField {

    private BufferedImage[] image=new BufferedImage[ARRAY_INDEX];
    private BufferedImage[] image1=new BufferedImage[ARRAY_INDEX];
    private Image[] imageResized=new Image[ARRAY_INDEX];
    private ImageIcon [] imageIcon=new ImageIcon[ARRAY_INDEX];
    private ImageIcon [] imageIconResized=new ImageIcon[ARRAY_INDEX];
    private ArrayList<Image> imageList=new ArrayList<>();

//    public ImageHolder(ImageBuffered [] image)
//    {
//        for(int i=0;i<ARRAY_INDEX;i++)
//        {
//            image[i]=new BufferedImage();
//            this.image[i]=image;
             // imageIcon[i]=new ImageIcon(image[i]);

//        }
//    }


    public void generateRealImages() {
        PixelMatrix pixelMatrix;
        String[][] formulas;
        int WIDTH = 200;
        int HEIGHT = 200;
        int POPULATION_SIZE = 4;

        // dummy formula generator
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

                pixelMatrix = api.CreateImage(HEIGHT, WIDTH, channels);
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

        resize();
    }

    public BufferedImage makeImage(int width, int height, PixelMatrix m)
    {
        int[] data = m.getIntArray();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);

        return image;
    }

    public void generateDummyImages() {
       //  String[] holdImageName=new String[]{"src/res/individual.1.png","src/res/individual.2.png"
       //    ,"src/res/individual.3.png","src/res/individual.4.png","src/res/individual.5.png","src/res/individual.5.png"};


        // int index=(int)(Math.random()*(holdImageName.length-1));

//         InputStream is = this.getClass().getClassLoader().getResourceAsStream("icon/individual.1.png");
//
//        try {
//            BufferedImage image =ImageIO.read(this.getClass().getResource("./res/icon/individual.1.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        imageIcon[0]=new ImageIcon(holdImageName[0]);
//        imageIcon[1]=new ImageIcon(holdImageName[1]);
//        imageIcon[2]=new ImageIcon(holdImageName[2]);
//        imageIcon[3]=new ImageIcon(holdImageName[3]);

//        for(int i=0;i<3;i++)
//        {
//            imageIcon[i]=new ImageIcon(holdImageName[i]);
//        }


        try {
            image1[0] = ImageIO.read(ResourceLoader.load("res/individual.1.png"));
            image1[1] = ImageIO.read(ResourceLoader.load("res/individual.2.png"));
            image1[2] = ImageIO.read(ResourceLoader.load("res/individual.3.png"));
            image1[3] = ImageIO.read(ResourceLoader.load("res/individual.4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(int y = 0; y < 200; y++){
//            for(int x = 0; x < 200; x++) {
//                int a = (int) (Math.random() * 256); //alpha
//                int r = (int) (Math.random() * 256); //red
//                int g = (int) (Math.random() * 256); //green
//                int b = (int) (Math.random() * 256); //blue
//
//                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel
//
//                //image1[0].setRGB(x, y, p);
////                image1[1].setRGB(x, y, p);
////                image1[2].setRGB(x, y, p);
////                image1[3].setRGB(x, y, p);
//
//            }}

//            for (int i = 0; i < 3; i++) {
//                imageIcon[i] = new ImageIcon(image1[i]);
//            }

        imageIcon[0]=new ImageIcon(image1[0]);
        imageIcon[1]=new ImageIcon(image1[1]);
        imageIcon[2]=new ImageIcon(image1[2] );
        imageIcon[3]=new ImageIcon(image1[3]);

            resize();

        }






    public ImageIcon resize(ImageIcon image)
    {
        Image image_hold;

        ImageIcon temp=null;

            image_hold= image.getImage().getScaledInstance(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight, Image.SCALE_DEFAULT);
            temp=new ImageIcon(image_hold);

        return temp;
    }

    public BufferedImage resize_save(BufferedImage image)
    {
        Image image_hold;
        BufferedImage bufferedImage;
        ImageIcon icon=new ImageIcon(image);


            image_hold= icon.getImage().getScaledInstance(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight, Image.SCALE_DEFAULT);


        if (image_hold instanceof BufferedImage)
        {
            return (BufferedImage) image_hold;
        }

        int width=image_hold.getWidth(null);
        int height=image_hold.getWidth(null);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        System.out.println(width);
        System.out.println(height);

        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(bufferedImage, 0, 0, null);
        bGr.dispose();




        return bufferedImage;
    }

    public void resize()
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
