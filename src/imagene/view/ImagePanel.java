package imagene.view;

import imagene.imagegen.models.PixelMatrix;
import imagene.viewmodel.ImageneViewModel;
import imagene.watchmaker.UnexpectedParentsException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * and Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

/*
TODO have to fix image saving to the default image dimension
TODO fix button and label alignment

 */

public class ImagePanel extends JPanel implements ConstantArrayField {

    public int sumOfTotalClicked=0;
    private JLabel []  holdImage ;
    private JPanel [] holdImageLabel  ;
    private JPanel [] hold_imagePanel;
    private ImageIcon [] icon;
    private JLabel label;
    private JButton btnGenerate;
    private JPanel panelForButton;
    private JPanel panelForLabel;
    private Insets insets;
    private ImageHolder imageHolder;
    private ImagePanelImageContent image_Contents;
    private Border border ;
    private int count1=0;
    private int count2=0;
    private int count3=0;
    private int count4=0;
    private int countImageClicked1=0;
    private int countImageClicked2=0;
    private int countImageClicked3=0;
    private int countImageClicked4=0;
    private JMenuItem itemSaveImage1;
    private JMenuItem itemFullSizeImage1;
    private JMenuItem itemSaveImage2;
    private JMenuItem itemFullSizeImage2;
    private JMenuItem itemSaveImage3;
    private JMenuItem itemFullSizeImage3;
    private JMenuItem itemSaveImage4;
    private JMenuItem itemFullSizeImage4;
    private JPopupMenu holdMenuItemsImage1;
    private JPopupMenu holdMenuItemsImage2;
    private JPopupMenu holdMenuItemsImage3;
    private JPopupMenu holdMenuItemsImage4;
    private DataToProcess dataProcess;
    private int count=0;

    private ImageneViewModel viewModel;

    public ImagePanel(ImageneViewModel viewModel)  {
        // The viewModel is called to get new images.
        this.viewModel = viewModel;

        // Viewmodel is passed around to those components that will need to access it.
        // TODO is this really needed?
        imageHolder = new ImageHolder(viewModel);
        image_Contents = new ImagePanelImageContent(viewModel, imageHolder);

        viewModel.chooseWinners(new int[] {0, 1});

        try {
            java.util.List<PixelMatrix> population = viewModel.getPopulation(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight);
            imageHolder.generateRealImages(population, SettingPanel.default_imageWidth, SettingPanel.default_imageHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension size = getPreferredSize();
        size.width = 600;
        setPreferredSize(size);

        setBackground(colorLightGray);
        setBorder(BorderFactory.createTitledBorder(" "));

        setLayout(new GridBagLayout()); // layout type GridLayout
        GridBagConstraints constraint=new GridBagConstraints();

        setBorder(new EmptyBorder(0,25,0,0));

        label=new JLabel("Please click to select 2 Images before generating.");
        btnGenerate=new JButton("Generate");
        btnGenerate.setPreferredSize(new Dimension(150,35));
        btnGenerate.setFocusPainted(true);
        btnGenerate.setFont(new Font("Serif",Font.BOLD,15));
        btnGenerate.setBackground(colorBlue);
        btnGenerate.setForeground(colorWhite);
        btnGenerate.setEnabled(false);

        itemSaveImage1 = new JMenuItem("Save");
        itemFullSizeImage1 = new JMenuItem("Full_Size");
        holdMenuItemsImage1 = new JPopupMenu();
        holdMenuItemsImage1.add(itemSaveImage1);
        holdMenuItemsImage1.add(itemFullSizeImage1);

        itemSaveImage2 = new JMenuItem("Save");
        itemFullSizeImage2 = new JMenuItem("Full_Size");
        holdMenuItemsImage2 = new JPopupMenu();
        holdMenuItemsImage2.add(itemSaveImage2);
        holdMenuItemsImage2.add(itemFullSizeImage2);

        itemSaveImage3 = new JMenuItem("Save");
        itemFullSizeImage3 = new JMenuItem("Full_Size");
        holdMenuItemsImage3 = new JPopupMenu();
        holdMenuItemsImage3.add(itemSaveImage3);
        holdMenuItemsImage3.add(itemFullSizeImage3);

        itemSaveImage4 = new JMenuItem("Save");
        itemFullSizeImage4 = new JMenuItem("Full_Size");
        holdMenuItemsImage4 = new JPopupMenu();
        holdMenuItemsImage4.add(itemSaveImage4);
        holdMenuItemsImage4.add(itemFullSizeImage4);

        panelForButton = new JPanel();
        panelForLabel = new JPanel();

        this.holdImage = image_Contents.getHoldImage();
        this.icon = image_Contents.getIcon();
        this.insets = image_Contents.getInsets();
        this.hold_imagePanel = image_Contents.getHold_imagePanel();
        this.holdImageLabel = image_Contents.getHoldImageLabel();

        panelForButton.add(btnGenerate);
        panelForButton.setBackground(colorLightGray);
        panelForButton.setBorder(new EmptyBorder(0,200,0,0));

        panelForLabel.add(label);
        panelForLabel.setBackground(colorLightGray);
        panelForLabel.setBorder(new EmptyBorder(0,0,0,0));

        constraint.anchor=GridBagConstraints.LINE_START;
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.insets=new Insets(10,30,10,10);

        constraint.gridx=0;
        constraint.gridy=0;
        add(image_Contents,constraint);

        constraint.gridx=0;
        constraint.gridy=1;
        add(panelForLabel,constraint);

        constraint.insets=new Insets(10,0,10,10);
        constraint.gridx=0;
        constraint.gridy=2;
        add(panelForButton,constraint);

        setImagesPanel();
    }



    public void setImagesPanel() {
        /*
        -mouse event right click to show pop up options
        -will show save and full_size
         */

        //panel 1
        // TODO "no such instance field" error here
        holdImage[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(SwingUtilities.isRightMouseButton(e))
                {
                    System.out.println("image 1 right-clicked");
                    holdMenuItemsImage1.show( holdImage[0],e.getX(),e.getY());
                }
            }
        });

        itemSaveImage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage(icon[0],holdImageLabel[0]);

            }
        });

        itemFullSizeImage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullImage(icon[0]);
            }
        });


        //panel 2
        holdImage[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(SwingUtilities.isRightMouseButton(e))
                {
                    holdMenuItemsImage2.show( holdImage[1],e.getX(),e.getY());
                }
            }
        });

        itemSaveImage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage(icon[1],holdImageLabel[1]);
            }
        });

        itemFullSizeImage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullImage(icon[1]);
            }
        });


        //panel 3
        holdImage[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(SwingUtilities.isRightMouseButton(e))
                {
                    holdMenuItemsImage3.show( holdImage[2],e.getX(),e.getY());
                }
            }
        });

        itemSaveImage3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage(icon[2],holdImageLabel[2]);
            }
        });

        itemFullSizeImage3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullImage(icon[2]);
            }
        });


        //panel 4
        holdImage[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(SwingUtilities.isRightMouseButton(e))
                {
                    holdMenuItemsImage4.show( holdImage[3],e.getX(),e.getY());
                }
            }
        });

        itemSaveImage4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage(icon[3],holdImageLabel[3]);
            }
        });

        itemFullSizeImage4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullImage(icon[3]);
            }
        });

        //end

        selectImages();
    }



    public void selectImages()
    {
        holdImage[0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (count1 == 0) {
                    System.out.println("count is: " + count);

                    if ((e.getClickCount() == 1)) {
                        ++count;

                        validateSelection(count);

                        hold_imagePanel[0].setBackground(colorWhite);
                        holdImageLabel[0].setBackground(colorWhite);
                        e.consume();
                        count1=1;
                        System.out.println("image1 pressed " );
                        countImageClicked1=1;

                        System.out.println("count is: " +count);
                        // set image to process stringImage1="image1";
                    }
                } else {
                    hold_imagePanel[0].setBackground(colorLightGray);
                    holdImageLabel[0].setBackground(colorLightGray);
                    count1 = 0;
                    System.out.println("image1 is unchecked ");
                    countImageClicked1 = 0;

                    --count;

                    validateSelection(count);
                    System.out.println("count is: " + count);

                }

            }
        });

        holdImage[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (count2 == 0) {
                    System.out.println("count is: " + count);

                    if ((e.getClickCount() == 1)) {
                        ++count;

                        validateSelection(count);

                        hold_imagePanel[1].setBackground(colorWhite);
                        holdImageLabel[1].setBackground(colorWhite);
                        e.consume();
                        count2=1;
                        System.out.println("image2 pressed " );
                        countImageClicked2=1;

                        System.out.println("count is: " +count);
                        // set image to process stringImage1="image1";
                    }

                } else {
                    hold_imagePanel[1].setBackground(colorLightGray);
                    holdImageLabel[1].setBackground(colorLightGray);
                    count2 = 0;
                    System.out.println("image2 is unchecked ");
                    countImageClicked2 = 0;

                    --count;

                    validateSelection(count);

                    System.out.println("count is: " + count);
                }
            }
        });

        holdImage[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (count3 == 0) {

                    if ((e.getClickCount() == 1)) {
                        ++count;

                        validateSelection(count);

                        hold_imagePanel[2].setBackground(colorWhite);
                        holdImageLabel[2].setBackground(colorWhite);
                        e.consume();
                        count3=1;
                        System.out.println("image3 pressed " );
                        countImageClicked3=1;

                        System.out.println("count is: " +count);
                        // set image to process stringImage1="image1";

                    }
                } else {
                    hold_imagePanel[2].setBackground(colorLightGray);
                    holdImageLabel[2].setBackground(colorLightGray);
                    count3 = 0;
                    System.out.println("image3 is unchecked ");
                    countImageClicked3 = 0;

                    --count;

                    validateSelection(count);
                    System.out.println("count is: " + count);
                }
            }
        });

        holdImage[3].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (count4 == 0) {

                    if ((e.getClickCount() == 1)) {
                        ++count;

                        validateSelection(count);

                        hold_imagePanel[3].setBackground(colorWhite);
                        holdImageLabel[3].setBackground(colorWhite);
                        e.consume();
                        count4=1;
                        System.out.println("image4 pressed " );
                        countImageClicked4=1;

                        System.out.println("count is: " +count);
                        // set image to process stringImage1="image1";

                    }
                } else {
                    hold_imagePanel[3].setBackground(colorLightGray);
                    holdImageLabel[3].setBackground(colorLightGray);
                    count4 = 0;
                    System.out.println("image4 is unchecked ");
                    countImageClicked4 = 0;

                    --count;

                    validateSelection(count);

                    System.out.println("count is: " + count);
                }
            }
        });

        processGenerateButton();
    }

    // check how many images the user selected and display an appropriate error message if it's wrong
    private void validateSelection(int count) {
        if (count == 2) {
            label.setText(null);
            btnGenerate.setEnabled(true);
        } else {
            label.setText("Please click to select 2 Images before generating.");
            btnGenerate.setEnabled(false);
        }
    }

    public void processGenerateButton()
    {
        btnGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sumOfTotalClicked=countImageClicked1+countImageClicked2+countImageClicked3+countImageClicked4;
                count=0;
                btnGenerate.setEnabled(false);
                label.setText("Please click to select 2 Images before generating.");

                // TODO I don't think these conditionals cover all possibilities
                // TODO handle selection of a single image, at some point
                // TODO 4 parents being selected, apparently - why?
                // TODO 1 "parent" is actually 3 formulas - how to handle this?
                if (sumOfTotalClicked==2) {

                    if ((count1 == 1) && (count2 == 1)) {
                        JOptionPane.showMessageDialog(null,"image 1 and 2 selected");
                        viewModel.chooseWinners(new int[] {0, 1});
                        setImagesToProcess(icon[0],icon[1]);
                    }

                    if ((count1 == 1) && (count3 == 1)) {
                        JOptionPane.showMessageDialog(null,"image 1 and 3 selected");
                        viewModel.chooseWinners(new int[] {0, 2});

                        setImagesToProcess(icon[0],icon[2]);
                    }

                    if ((count1 == 1) && (count4 == 1)) {
                        JOptionPane.showMessageDialog(null,"image 1 and 4 selected");
                        viewModel.chooseWinners(new int[] {0, 3});

                        setImagesToProcess(icon[0],icon[3]);
                    }

                    if ((count2 == 1) && (count3 == 1)) {
                        JOptionPane.showMessageDialog(null,"image 2 and 3 selected");
                        viewModel.chooseWinners(new int[] {1, 2});

                        setImagesToProcess(icon[1],icon[2]);
                    }

                    if ((count2 == 1) && (count4 == 1))
                    {
                        JOptionPane.showMessageDialog(null,"image 2 and 4 selected");
                        viewModel.chooseWinners(new int[] {1, 3});

                        setImagesToProcess(icon[1],icon[3]);
                    }

                    if ((count3 == 1) && (count4 == 1))
                    {
                        JOptionPane.showMessageDialog(null,"image 3 and 4 selected");
                        viewModel.chooseWinners(new int[] {2, 3});

                        setImagesToProcess(icon[2],icon[3]);
                    }

                    count1=0;
                    count2=0;
                    count3=0;
                    count4=0;

                    countImageClicked1=0;
                    countImageClicked2=0;
                    countImageClicked3=0;
                    countImageClicked4=0;

                    sumOfTotalClicked=0;

                    try {
                        viewModel.newGeneration();

                        // TODO array out of bounds here
                        java.util.List<PixelMatrix> formulas = viewModel.getPopulation(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight);
                        imageHolder.generateRealImages(formulas, SettingPanel.default_imageWidth, SettingPanel.default_imageHeight);
                        icon = imageHolder.returnImageIcon();

                        for (int i = 0; i < ARRAY_INDEX; i++) {
                            holdImage[i].setIcon(null);
                            holdImage[i].setIcon(icon[i]);
                            holdImageLabel[i].add(holdImage[i]);
                            holdImageLabel[i].setBackground(colorLightGray);
                            hold_imagePanel[i].setBackground(colorLightGray);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
    }


    public void saveImage(ImageIcon image, JPanel panel) {
        System.out.println("saved");

        BufferedImage imageToSave = new BufferedImage(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageToSave.createGraphics();
        ImageIcon imageIcon = imageHolder.resize(image);
        imageIcon.paintIcon(null, g, 0,0);
        g.dispose();

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith("*.png");
                }
            }

            @Override
            public String getDescription() {
                return "Portable Network Graphics (*.png)"; // sets to .png
            }
        });


        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int hold = fileChooser.showSaveDialog(ImagePanel.this);

        if (hold == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                ImageIO.write(imageToSave, "png", file);

            } catch (IOException e) {

            }

        }
    }


    public void fullImage(ImageIcon image)
    {
        System.out.println("full_size");

        ImageIcon temp=imageHolder.resize(image);
        JPanel panel=new JPanel();

        JLabel label=new JLabel(temp);

        label.setPreferredSize(new Dimension(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight));
        panel.setPreferredSize(new Dimension(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight));
        panel.add(label);
        JScrollPane scrollPane=new JScrollPane(panel);
        System.out.println("width in image window: " + SettingPanel.default_imageWidth);
        System.out.println("width in image window: " + SettingPanel.default_imageHeight);
        JFrame frameShowImageInLargeSize=new JFrame("Full Size");

        frameShowImageInLargeSize.add(scrollPane);
        panel.setBackground(Color.GRAY);

        frameShowImageInLargeSize.setVisible(true);
        frameShowImageInLargeSize.setSize(SettingPanel.default_imageWidth, SettingPanel.default_imageHeight);
        frameShowImageInLargeSize.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }


    public void setImagesToProcess(ImageIcon image1,ImageIcon image2)
    {
        ImageIcon returnImage1=imageHolder.resize(image1);
        ImageIcon returnImage2=imageHolder.resize(image2);
        //dataProcess=new DataToProcess(returnImage1,returnImage2);
        //dataProcess.testImagesToProcess(); // test function call....delete later
    }

}
