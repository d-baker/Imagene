package imagene.view;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/


public class SettingPanel extends JPanel implements ConstantArrayField {

    private JLabel labelCoordinate;
    private JLabel labelSymmetry;

    private JLabel labelDimension;

    private SettingPanelCoordinate coordinate;
    private SettingPanelSymmetry symmetry;
    private SettingPanelDimension imageDimension;
    private SettingPanelButton settingButton;
    private SettingPanelInfo info;

    private ButtonGroup groupCoordinate;
    private ButtonGroup groupSymmetry;

    private JButton btnSave;
    private  JButton btnDefault;
    public static  JLabel warning;
    private JLabel insertPixelValue;

    private JRadioButton radioButtonCartesian;
    private JRadioButton radioButtonPolar;

    private JRadioButton radioButtonSymmetric;
    private JRadioButton radioButtonAsymmetric;

    private JLabel infoCoordinate;
    private JLabel infoSymmetry;
    private JLabel infoImageWidth;
    private JLabel infoImageHeight;

    private JTextField imageWidth;
    private JTextField imageHeight;

    private int initial_imageWidth;
    private int initial_imageHeight;

    public static String coordSetting;
    public static String symmetrySetting;

    private String defaultCoordinate;
    private String defaultSymmetry;
    public static int default_imageWidth;
    public static int default_imageHeight;

    private String default_Coordinate;
    private String default_Symmetry;
    public static int defaultImageWidth;
    public static int defaultImageHeight;

    int k;

    // constructor
    public SettingPanel(){
//        default_imageWidth = 300;
//        default_imageHeight = 300;
//        k=0;

        Dimension size = getPreferredSize();
        size.width=300;
        setPreferredSize(size);

        labelCoordinate=new JLabel("Coordinate:");
        labelCoordinate.setForeground(colorGray);

        labelSymmetry=new JLabel("Symmetry:");
        labelSymmetry.setForeground(colorGray);

        labelDimension=new JLabel("Dimension: Width x Height");
        labelDimension.setForeground(colorGray);

        coordinate=new SettingPanelCoordinate();
        symmetry=new SettingPanelSymmetry();
        imageDimension=new SettingPanelDimension();
        settingButton=new SettingPanelButton();
        info=new SettingPanelInfo();


        this.radioButtonCartesian=coordinate.getRadioButtonCartesian();
        this.radioButtonPolar=coordinate.getRadioButtonPolar();

        this.radioButtonSymmetric=symmetry.getRadioButtonSymmetric();
        this.radioButtonAsymmetric=symmetry.getRadioButtonAsymmetric();

        this.btnSave=settingButton.getBtnSave();
        this.btnDefault=settingButton.getBtnDefault();
        this.btnDefault.setEnabled(false);

        warning=new JLabel();
        //warning.setForeground(colorRed);
        warning.setForeground(colorBlue);

        insertPixelValue=new JLabel();


        this.infoCoordinate=info.getInfoCoordinate();
        this.infoSymmetry=info.getInfoSymmetry();
        this.infoImageWidth =info.getInfoImageWidth();
        this.infoImageHeight =info.getInfoImageHeight();

        this.imageWidth=imageDimension.getImageWidth();
        this.imageHeight=imageDimension.getImageHeight();

        //final int limit = 10;

        /* limits textField to max SETTEXTFIELD_LIMIT digits */
        this.imageWidth .setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= SETTEXTFIELD_LIMIT) {
                    super.insertString(offs, str, a);
                    warning.setForeground(colorBlue);
                    warning.setText("press ok to save new user settings");
                }
            }
        });

        this.imageHeight .setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= SETTEXTFIELD_LIMIT) {
                    super.insertString(offs, str, a);
                    warning.setForeground(colorBlue);
                    warning.setText("press ok to save new user settings");
                }
            }
        });
        /* end */



        setBorder(new EmptyBorder(10,40,10,10));
        setLayout(new GridBagLayout()); // layout type GridLayout
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;

        //set window coordinate to 0,0
        //set components to column 0
        constraint.gridx=0;
        constraint.gridy=0;
        add(labelCoordinate,constraint); //set labelCoordinate to 0,0

        constraint.gridx=0;
        constraint.gridy=1;
        add(coordinate,constraint);//set radioButtonCartesian to 0,1


        constraint.gridx=0;
        constraint.gridy=2;
        add(labelSymmetry,constraint);//set labelSymmetry to 0,2

        constraint.gridx=0;
        constraint.gridy=3;
        add(symmetry,constraint);//set radioButtonSymmetric to 0,3


        constraint.gridx=0;
        constraint.gridy=4;
        add(labelDimension,constraint);//set labelDimension to 0,4

        constraint.gridx=0;
        constraint.gridy=5;
        add(imageDimension,constraint);//set imageWidth(text field) to 0,5

        constraint.gridx=0;
        constraint.gridy=6;
        add(warning,constraint);//set imageWidth(text field) to 0,5

        constraint.gridx=0;
        constraint.gridy=7;
        add(settingButton,constraint);//set btnSave(button) to 0,6

        constraint.weighty=2;
        constraint.weightx=2;
        constraint.gridx=0;
        constraint.gridy=8;
        add(info,constraint);//set btnSave(button) to 0,6

         /* calls and set setting panel  */
        setSettingPanel();
    }
    /*end */


    /* function to set Setting Panel */
    public void setSettingPanel()
    {
        groupCoordinate=new ButtonGroup();
        groupCoordinate.add(coordinate.getRadioButtonCartesian());
        groupCoordinate.add(coordinate.getRadioButtonPolar());

        groupSymmetry=new ButtonGroup();
        groupSymmetry.add(symmetry.getRadioButtonSymmetric());
        groupSymmetry.add(symmetry.getRadioButtonAsymmetric());

        /* set user setting from xml file */
        readUserSettingFromXML();

        if (defaultCoordinate.equals("Cartesian")) {
            radioButtonCartesian.setSelected(true);
            coordSetting="Cartesian";
        }
        if (defaultCoordinate.equals("Polar")) {
            radioButtonPolar.setSelected(true);
            coordSetting="Polar";
        }
        if (defaultSymmetry.equals("Symmetric"))
        {
            radioButtonSymmetric.setSelected(true);
            symmetrySetting="Symmetric";
        }
        if (defaultSymmetry.equals("Asymmetric")) {
            radioButtonAsymmetric.setSelected(true);
            symmetrySetting="Asymmetric";
        }

        imageWidth.setText(String.valueOf(default_imageWidth));
        infoImageWidth.setText("ImageWidth: " + String.valueOf(default_imageWidth));
        imageHeight.setText(String.valueOf(default_imageHeight));
        infoImageHeight.setText("ImageHeight: " + String.valueOf(default_imageHeight));




        warning.setText("press ok to save new user settings");

         /*  event handler for save button  */
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                String textFieldValue1 = imageWidth.getText();
                String textFieldValue2 = imageHeight.getText();



                try {
                    initial_imageWidth = Integer.parseInt(textFieldValue1);
                    initial_imageHeight = Integer.parseInt(textFieldValue2);
                    if ((initial_imageWidth >= 700) || (initial_imageHeight >= 700)) {
                        JOptionPane.showMessageDialog(null, WARNING_IMAGE_SIZE);
                        //label.setText("!!!!!warning..image with larger dimension will take longer to process ");
                        //int i = JOptionPane.showConfirmDialog(null, "image with larger dimension will take longer to process...do you want to continue?", "", JOptionPane.YES_NO_OPTION);
                    }
                } catch (NumberFormatException nfe) {
                    warning.setText(WARNING_IMAGE_VALUE_EXCEEDS);
                }

                System.out.println("Image width: " + initial_imageWidth);
                System.out.println("Image width: " + initial_imageHeight);

                if (coordSetting.equals("Cartesian")) {
                    infoCoordinate.setText("Coordinate: " + "Cartesian");
                }
                if (coordSetting.equals("Polar")) {
                    infoCoordinate.setText("Coordinate: " + "Polar");
                }

                if (symmetrySetting.equals("Symmetric")) {
                    infoSymmetry.setText("Symmetry: " + "Symmetric");
                }
                if (symmetrySetting.equals("Asymmetric")) {
                    infoSymmetry.setText("Symmetry: " + "Asymmetric");
                }

                infoImageWidth.setText("ImageWidth: " +String.valueOf(initial_imageWidth));
                infoImageHeight.setText("ImageHeight: " +String.valueOf(initial_imageHeight));

                if ((initial_imageWidth <=0)||(initial_imageHeight<=0)) {
                    warning.setText(WARNING_IMAGE_VALUE);
                    warning.setForeground(colorRed);
                    initial_imageWidth = default_imageWidth;
                    initial_imageHeight = default_imageHeight;
                    infoImageWidth.setText("ImageWidth: " + String.valueOf(default_imageWidth));
                    infoImageHeight.setText("ImageHeight: " + String.valueOf(default_imageHeight));
                }
//                } else if ((initial_imageWidth >800)||(initial_imageHeight >800)) {
//                    warning.setText("press ok to save new user settings");
//                    warning.setForeground(colorRed);
//                }
                else {
                    warning.setText(SAVED_USER_INPUT);
                    warning.setForeground(colorBlue);
                }

                default_imageWidth = initial_imageWidth;
                String hold1=String.valueOf(initial_imageWidth);

                default_imageHeight = initial_imageHeight;
                String hold2=String.valueOf(initial_imageHeight);

                btnDefault.setEnabled(true);
                writeUserSettingToXML(hold1,hold2); // function called
            }
        });
        /* end */


        default_Coordinate=defaultCoordinate;
        default_Symmetry=defaultSymmetry;
        defaultImageHeight=default_imageHeight;
        defaultImageWidth=default_imageWidth;

        /* event handler for default button */


              btnDefault.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {

                      btnDefault.setEnabled(false);
                      if (defaultCoordinate.equals("Cartesian")) {
                          radioButtonCartesian.setSelected(true);
                          coordSetting = "Cartesian";
                      }
                      if (defaultCoordinate.equals("Polar")) {
                          radioButtonPolar.setSelected(true);
                          coordSetting = "Polar";
                      }
                      if (defaultSymmetry.equals("Symmetric")) {
                          radioButtonSymmetric.setSelected(true);
                          symmetrySetting = "Symmetric";
                      }
                      if (defaultSymmetry.equals("Asymmetric")) {
                          radioButtonAsymmetric.setSelected(true);
                          symmetrySetting = "Asymmetric";
                      }

                      imageWidth.setText(String.valueOf(defaultImageWidth));
                      imageHeight.setText(String.valueOf(defaultImageHeight));

                      infoCoordinate.setText("Coordinate: " + default_Coordinate);
                      infoSymmetry.setText("Symmetry: " + default_Symmetry);

                      int specifiedWidth = Integer.valueOf(defaultImageWidth);
                      int specifiedHeight = Integer.valueOf(defaultImageHeight);

                      validateImageDimensions(specifiedWidth, specifiedHeight);

                      default_imageWidth = Integer.valueOf(imageWidth.getText());
                      default_imageHeight = Integer.valueOf(imageHeight.getText());

                      infoImageWidth.setText("ImageWidth: " + defaultImageWidth);
                      infoImageHeight.setText("ImageHeight: " + defaultImageHeight);
                      warning.setText(USER_INPUT);
                  }

              });


        radioButtonCartesian.addActionListener(radioButtonActionListener_Coordinate);
        radioButtonPolar.addActionListener(radioButtonActionListener_Coordinate);
        radioButtonSymmetric.addActionListener(radioButtonActionListener_Symmetry);
        radioButtonAsymmetric.addActionListener(radioButtonActionListener_Symmetry);
    }

    ActionListener radioButtonActionListener_Coordinate = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            AbstractButton aButton = (AbstractButton) actionEvent.getSource();
            String hold = aButton.getText();
            warning.setText(USER_INPUT);
            if (hold.equals("Cartesian")) {
                coordSetting = "Cartesian";
            } else {
                coordSetting = "Polar";
            }
        }
    };

    ActionListener radioButtonActionListener_Symmetry = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            AbstractButton aButton = (AbstractButton) actionEvent.getSource();
            String hold=aButton.getText();
            warning.setText(USER_INPUT);
            if (hold.equals("Symmetric")) {
                symmetrySetting="Symmetric";
            } else {
                symmetrySetting="Asymmetric";
            }
        }
    };

    public void readUserSettingFromXML() {
        String sys = System.getProperty("user.home");
        String userSetting_fileName="UserSettings.xml";
        String imageEvolver_folder="ImageEvolver";
        String filename="";
        String operatingSys_name=System.getProperty("os.name").toLowerCase();
        System.out.println(operatingSys_name);

        if (operatingSys_name.indexOf("win") >= 0) {


            filename =   sys+"\\" +imageEvolver_folder+"\\"+userSetting_fileName;

        } else if (operatingSys_name.indexOf("nix") >= 0 ||
                operatingSys_name.indexOf("nux") >= 0 ||
                operatingSys_name.indexOf("mac") >= 0) {


            filename =  sys + "/"+imageEvolver_folder+ "/" + userSetting_fileName;

        }else{


            filename = sys + "/"+imageEvolver_folder+ "/" + userSetting_fileName;

        }
        //filename=createPath(userSetting_fileName);

        // String absolutePath = FileSystems.getDefault().getPath(fileurl).normalize().toAbsolutePath().toString();



        // String fileurl = sys + "\\desktop\\ImageEvolver\\UserSettings.xml";


          //String fileurl = sys + File.separator + "ImageEvolver" + File.separator + "UserSettings.xml";

        File xmlFile = new File(filename);
        if (xmlFile.exists()) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                passObjectToReadFromXML(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //writeUserSettingToXML("50","50","default");
            try {
                InputStream readFile = ResourceLoader.load("imagene/view/resources/DefaultSettings.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(readFile);
                passObjectToReadFromXML(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }



    public void passObjectToReadFromXML(Document doc) {
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("setting");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                defaultCoordinate = eElement.getElementsByTagName("coordinate").item(0).getTextContent();
                defaultSymmetry = eElement.getElementsByTagName("symmetry").item(0).getTextContent();
                String hold1 = String.valueOf(eElement.getElementsByTagName("imageWidth").item(0).getTextContent());
                String hold2 = String.valueOf(eElement.getElementsByTagName("imageHeight").item(0).getTextContent());

                System.out.println("coordinate : " + defaultCoordinate);
                System.out.println("symmetry : " + defaultSymmetry);
                System.out.println("imageWidth : " + hold1);
                System.out.println("imageHeight : " + hold2);

                coordSetting = defaultCoordinate;
                symmetrySetting = defaultSymmetry;

                infoCoordinate.setText("Coordinate: " + defaultCoordinate);
                infoSymmetry.setText("Symmetry: " + defaultSymmetry);

                int specifiedWidth = Integer.valueOf(hold1);
                int specifiedHeight = Integer.valueOf(hold2);

                validateImageDimensions(specifiedWidth, specifiedHeight);

                default_imageWidth = specifiedWidth;
                default_imageHeight = specifiedHeight;
            }
        }

    }




    // Display an appropriate warning if image dimensions are wrong
    public void validateImageDimensions(int dimX, int dimY) {
        if ((dimX <= 0) || (dimY <= 0)) {
            warning.setText(WARNING_IMAGE_VALUE);
        } else if ((dimX > 800) || (dimY > 800)) {
            warning.setText(WARNING_IMAGE_SIZE);
        } else {
            warning.setText(null);
        }
    }

    public boolean writeUserSettingToXML(String imageWidth,String imageHeight) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("setting");
        doc.appendChild(rootElement);

        Element coordinateInfo = doc.createElement("coordinate");
        coordinateInfo.setTextContent(coordSetting);
        rootElement.appendChild(coordinateInfo);

        Element symmetryInfo = doc.createElement("symmetry");
        symmetryInfo.setTextContent(symmetrySetting);
        rootElement.appendChild(symmetryInfo);

        Element imageWidthInfo = doc.createElement("imageWidth");
        imageWidthInfo.setTextContent(imageWidth);
        rootElement.appendChild(imageWidthInfo);

        Element imageHeightInfo = doc.createElement("imageHeight");
        imageHeightInfo.setTextContent(imageHeight);
        rootElement.appendChild(imageHeightInfo);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "5");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        DOMSource source = new DOMSource(doc);

        StreamResult result = null;

        String sys = System.getProperty("user.home");
        String userSetting_fileName="UserSettings.xml";
        String imageEvolver_folder="ImageEvolver";
        String filename="";
        String operatingSys_name=System.getProperty("os.name").toLowerCase();
        System.out.println(operatingSys_name);

        if (operatingSys_name.indexOf("win") >= 0) {


            filename =   sys+"\\"+imageEvolver_folder;

        } else if (operatingSys_name.indexOf("nix") >= 0 ||
                operatingSys_name.indexOf("nux") >= 0 ||
                operatingSys_name.indexOf("mac") >= 0) {


            filename =  sys +  "/" + imageEvolver_folder;

        }else{


            filename = sys + "/" + imageEvolver_folder;

        }

        System.out.println(filename);


        File folder = new File(filename);
        folder.mkdir();



        if (operatingSys_name.indexOf("win") >= 0) {


            filename =  filename+"\\"+userSetting_fileName;

        } else if (operatingSys_name.indexOf("nix") >= 0 ||
                operatingSys_name.indexOf("nux") >= 0 ||
                operatingSys_name.indexOf("mac") >= 0) {


            filename =  filename+ "/" + userSetting_fileName;

        }else{


            filename = filename+ "/" + userSetting_fileName;

        }

//        File folder = new File(folderPath);
//       folder.mkdir();
        System.out.println(filename);
        File file=new File(filename);

       // File file=new File(folderPath+File.separator+"UserSettings.xml");



        //System.out.println(folderPath.toString());
        result=new StreamResult(file);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return true;
    }



}
