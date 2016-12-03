package imagene.view;

/*****************************************
 * Written by Avishkar Giri (s3346203),
 * Dorothea Baker (s3367422)
 * and Andrew Sanger (s3440468)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/


import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.imagegen.models.PixelMatrix;
import imagene.viewmodel.ImageneViewModel;
import imagene.watchmaker.UnexpectedParentsException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;



public class View_ViewModel_Integration implements ConstantArrayField{

    private String coordType;
    private String symmetryType;
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
    private String holdReturnData="";

    private BufferedImage[] image=new BufferedImage[ARRAY_INDEX];
    private ImageIcon [] imageIcon=new ImageIcon[ARRAY_INDEX];
    private Image[] imageResized=new Image[ARRAY_INDEX];
    private ImageIcon [] imageIconResized=new ImageIcon[ARRAY_INDEX];
    private String[] formulaMetadata;

     //constructor
    public View_ViewModel_Integration() {
        object=new ImageneViewModelObject();
        viewModel=object.getViewModel();
        imageWidth=SettingPanel.default_imageWidth;
        imageHeight=SettingPanel.default_imageHeight;
        coordType = SettingPanel.coordSetting;
        symmetryType = SettingPanel.symmetrySetting;
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
            List<PixelMatrix> pixelMatrices = viewModel.getPopulation(imageWidth, imageHeight, coordType, symmetryType);
            generateRealImages(pixelMatrices, imageWidth, imageHeight);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (IncorrectVariablesException e) {
            e.printStackTrace();
        }
    }




    public void generateRealImages(List<PixelMatrix> pixelMatrices, int width, int height) {

        BufferedImage[] image_1=new BufferedImage[ARRAY_INDEX];

        for (int i = 0; i < ARRAY_INDEX; i++) {
            image_1[i] = makeImage(width, height, pixelMatrices.get(i));
            this.image[i] = image_1[i];
            imageIcon[i] = new ImageIcon(image[i]);
        }

        resizeAllImages();



        String[][] formulaStrings = viewModel.getFormulaStrings();
        int numImages = pixelMatrices.size();
        formulaMetadata = new String[numImages];

        for(int i=0;i<formulaMetadata.length;i++)
        {
            formulaMetadata[i]="";
        }

        for (int image = 0; image < numImages; image++) {

            for (int channel = 0; channel < 3; channel++) {
                String channelName;
                switch(channel) {
                    case 0: channelName = "Red";
                        break;
                    case 1: channelName = "Green";
                        break;
                    default: channelName = "Blue";
                }
                formulaMetadata[image] += " " + channelName + ": " + formulaStrings[image][channel];
            }

        }

    }



    public String[] getFormulaMetadata() {
        return formulaMetadata;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinate(String coordinate) {
        this.coordType = coordinate;
    }

    public void setSymmetry(String symmetry) {
        this.symmetryType = symmetry;
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



    public void initiateImages(int x)
    {
        viewModel.chooseWinners(new int[]{x});
    }


    // resize actual size of the image to the fixed size of 200*200 to display it on the panel of the window
    public void resizeAllImages() {
        BufferedImage[] temp=new BufferedImage[4];
        for(int i=0;i<ARRAY_INDEX;i++) {
            imageResized[i] =  imageIcon[i].getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

            temp[i]= View_ViewModel_Integration.toBufferedImage(imageResized[i]);
            imageIconResized[i]=new ImageIcon(temp[i]);
        }

    }
    //end

    public ImageIcon[] returnImageIcon()
    {
        return imageIconResized;
    }



    //converts imageicon to bufferedimage
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
    //end


   // extract only "png" from the filename
    private static String getFileExtension(File file)
    {
        String fileName = file.getName();
        System.out.println("the file path is " +fileName);
        //int lastDot = fileName.lastIndexOf('.');
        int lastDot = fileName.indexOf('.');
        String temp=fileName.substring(lastDot + 1);
        System.out.println("the file path is " +temp);
        return fileName.substring(lastDot + 1);
    }
    //end


   // writes image generating algorithm to the image file
    public BufferedImage writeAlgorithmToPNG(File file,BufferedImage image, String key, String value) throws Exception {


        String fileExtension = file.toString();
        System.out.println("the file path is " +fileExtension);

        BufferedImage returnImage=image;
        BufferedWriter bufferWriter = null;
        String imageAlgorithm=key+" "+value;
        System.out.println("the value is " +imageAlgorithm);

        try {
            bufferWriter = new BufferedWriter(new FileWriter(file, true));
            //bufferWriter = new BufferedWriter(new FileWriter(fileExtension, true));
            System.out.println("the value is test: " +bufferWriter.toString());
            bufferWriter.write(imageAlgorithm);
            bufferWriter.newLine();
            bufferWriter.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {                       //close the file
            if (bufferWriter != null) try {
                bufferWriter.close();
            } catch (IOException ioe2) {

            }
        }

        //read algorithm from a file location
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String readAlgorithm;

            bufferedReader = new BufferedReader(new FileReader(file));

            while ((readAlgorithm = bufferedReader.readLine()) != null) {
                System.out.println(readAlgorithm);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }


        return returnImage;
    }
   //end

}
