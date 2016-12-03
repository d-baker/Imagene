package imagene.view;

import java.awt.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public interface ConstantArrayField {

    public static final int ARRAY_INDEX=4;
    public static final int GRID_ROW=2;
    public static final int GRID_COlUMN=2;
    public static final String WARNING_IMAGE_SIZE="!!!warning..image with larger dimension will take longer to process";
    public static final String WARNING_IMAGE_VALUE="InValid user input";
    public static final String WARNING_IMAGE_VALUE_EXCEEDS="Input values too large to process";
    public static final String USER_INPUT="press ok to save new user settings";
    public static final String SAVED_USER_INPUT="input saved.Proceed with image generation";

    public static final int SETTEXTFIELD_LIMIT = 5; //increasing the limit more than 5 will cause java.lang.outofmemory ERROR

    public static final Color colorWhite=new Color(255,255,255);
   //public static final Color colorWhite=new Color(255,0,0);
    public static final Color colorBlue_generateBtn=new Color(0,51,204);
    public static final Color colorBlue=new Color(0,0,255);
    public static final Color colorLightGray=new Color(192,192,192);
    public static final Color colorGray=new Color(128,128,128);
    public static final Color colorRed=new Color(255,0,0);
    public static final Color colorOrange=new Color(255,178,102);

    public static final String APP_INSTRUCTION=
            "How to generate images:\n" +
            "\n" +
            "1)\tFrom default user setting\n" +
            "Please select any two images and press generate.\n" +
            "\n" +
            "2)\tFrom New Image settings\n" +
            "-Please select image properties to either Cartesian or polar and symmetric or asymmetric.\n" +
            "-fill width and height section with the new image dimensions.\n" +
            "-press ok to save the user input.\n" +
            "-select any two images and press generate.\n" +
            "\n" +
            "How to save image and view in full size:\n" +
            "\n" +
            "Right mouse click when the cursor is on the image will pop open options to save image or view that image in full size.\n";

    public static final String APP_ABOUT="<html><p>IMAGENE\n" +
            "This program was created utilising a  genetic programming algorithm with the purpose of generating aesthetically  appealing and interesting images. \n" +
            "\n\n" +
            "HOW IT WORKS\n" +
            "When the application first opens, the  program utilises the genetic programming framework Watchmaker to randomly  generate a population of 12\n" +
            "mathematical formulas, which are then used to  generate the RGB pixel color values for 4 images that are displayed to the  user. Users then have\n" +
            "the opportunity to select  either one or two of their favourite images before clicking Generate to get a  new set of images.Should they choose a \n" +
            "single image, the next  generation of images will consist of the selected image (the parent) plus 3 new  images created by randomly mutating the parent.\n" +
            "If the user selects two favourite  images,  the next generation will consist  of the two selected parents plus 2 new images created by breeding the 2  \n" +
            "parents.By choosing the most interesting images in  the population, the most visually pleasing images should evolve.\n" +
            "\n\n" +
            "CREDITS\n" +
            "Avishkar Giri, Andrew Sanger, Callum  McLennan, Dorothea Baker and Monique O'Reilly";



}
