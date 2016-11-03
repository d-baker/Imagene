package GUI;

import javax.swing.*;
import java.awt.*;


/**
 * Created by avishkar on 10/23/2016.
 */

/*
-have to fix image saving to the default image dimension
-fix button and label alignment

 */

public class ImagePanelImageContent extends JPanel implements ConstantArrayField {

    private JLabel[] holdImage = new JLabel[ARRAY_INDEX];
    private JPanel[] holdImageLabel = new JPanel[ARRAY_INDEX];
    private JPanel[] hold_imagePanel = new JPanel[ARRAY_INDEX];
   // private JPanel hold_image_content;
    private ImageIcon[] icon = new ImageIcon[ARRAY_INDEX];
    private ImageHolder imageHolder=new ImageHolder();

    private Insets insets = new Insets(10, 10, 10, 10);


    public ImagePanelImageContent() {

//        Dimension size = getPreferredSize();
//        size.width=600;
//        setPreferredSize(size);
        setBackground(colorLightGray);

        imageHolder.generateRealImages();
        //imageHolder.generateDummyImages();
        //setBackground(Color.lightGray);

        //setBorder(new EmptyBorder(100, 100, 100, 100));
        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        icon = imageHolder.returnImageIcon();

        for (int i = 0; i < ARRAY_INDEX; i++) {
            holdImage[i] = new JLabel(icon[i]);
            holdImage[i].setPreferredSize(new Dimension(200, 200));
            // holdImage[i].setBorder(border);

            holdImageLabel[i] = new JPanel();
            holdImageLabel[i].setPreferredSize(new Dimension(200, 200));
            holdImageLabel[i].setBackground(colorLightGray);
            holdImageLabel[i].add(holdImage[i]);

            hold_imagePanel[i] = new JPanel();
            hold_imagePanel[i].setPreferredSize(new Dimension(215, 215));
            hold_imagePanel[i].setBackground(colorLightGray);
            hold_imagePanel[i].add(holdImageLabel[i]);
            //hold_imagePanel[i].setBorder(border);
        }



        constraint.anchor = GridBagConstraints.LINE_END;
        constraint.weightx = 0.5;
        constraint.weighty = 0.5;
        constraint.insets = insets;

        constraint.gridx = 0;
        constraint.gridy = 0;
        add(hold_imagePanel[0], constraint);

        constraint.gridx = 0;
        constraint.gridy = 1;
        add(hold_imagePanel[1], constraint);


        constraint.anchor = GridBagConstraints.LINE_START;
//        constraint.weightx = 2;
//        constraint.weighty = 2;
        constraint.gridx = 1;
        constraint.gridy = 0;
        add(hold_imagePanel[2], constraint);


        constraint.gridx = 1;
        constraint.gridy = 1;
        add(hold_imagePanel[3], constraint);

    }

    public JLabel[] getHoldImage() {
        return holdImage;
    }

    public JPanel[] getHoldImageLabel() {
        return holdImageLabel;
    }

    public JPanel[] getHold_imagePanel() {
        return hold_imagePanel;

    }

    public ImageIcon[] getIcon() {
        return icon;
    }

    public ImageHolder getImageHolder() {
        return imageHolder;
    }

    @Override
    public Insets getInsets() {
        return insets;
    }
}
