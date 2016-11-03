package GUI;

import java.awt.*;

/**
 * Created by avishkar on 10/22/2016.
 */
public interface ConstantArrayField {

    public static final int ARRAY_INDEX=4;
    public static final int GRID_ROW=2;
    public static final int GRID_COlUMN=2;
    public static final String WARNING_IMAGE_SIZE="!!!warning..takes longer to process";
    public static final String WARNING_IMAGE_VALUE="InValid user input";
    public static final String WARNING_IMAGE_VALUE_EXCEEDS="Input values too large to process";

    public static final Color colorWhite=new Color(255,255,255);
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

    public static final String APP_ABOUT="Testing testing testing Testing testing testing \n" +
            "Testing testing testing Testing testing testing Testing testing testing Testing\n testing testing Testing testing testing" +
            " Testing testing testing Testing testing\n testing Testing testing testing Testing\n testing testing" +
            " Testing testing testingTesting \ntesting testing Testing testing testing";
}
