package imagene.view;

import imagene.imagegen.models.PixelMatrix;
import imagene.viewmodel.ImageneViewModel;

import javax.swing.*;
import java.awt.*;

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

public class ImagePanelImageContent extends JPanel implements ConstantArrayField {

    private JLabel[] holdImage = new JLabel[ARRAY_INDEX];
    private JPanel[] holdImageLabel = new JPanel[ARRAY_INDEX];
    private JPanel[] hold_imagePanel = new JPanel[ARRAY_INDEX];
    private ImageIcon[] icon = new ImageIcon[ARRAY_INDEX];

    private ImageHolder imageHolder;
    private ImageneViewModel viewModel;

    private Insets insets = new Insets(10, 10, 10, 10);

    public ImagePanelImageContent(ImageneViewModel viewModel, ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
        this.viewModel = viewModel;
        // TODO halp
        try {
            java.util.List<PixelMatrix> population = viewModel.getPopulation(SettingPanel.defaultImageWidth, SettingPanel.defaultImageHeight);
            imageHolder.generateRealImages(population, SettingPanel.defaultImageWidth, SettingPanel.defaultImageHeight);

            System.out.println("ImagePanelImageContent_class " +"imageWidth: " +SettingPanel.defaultImageWidth +" imageHeight: " +SettingPanel.defaultImageHeight);//delete later

        } catch (Exception e) {
            e.printStackTrace();
        }
        icon = imageHolder.returnImageIcon();


        setBackground(colorLightGray);

        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        for (int i = 0; i < ARRAY_INDEX; i++) {
            holdImage[i] = new JLabel(icon[i]);
            holdImage[i].setPreferredSize(new Dimension(200, 200));

            holdImageLabel[i] = new JPanel();
            holdImageLabel[i].setPreferredSize(new Dimension(200, 200));
            holdImageLabel[i].setBackground(colorLightGray);
            holdImageLabel[i].add(holdImage[i]);

            hold_imagePanel[i] = new JPanel();
            hold_imagePanel[i].setPreferredSize(new Dimension(215, 215));
            hold_imagePanel[i].setBackground(colorLightGray);
            hold_imagePanel[i].add(holdImageLabel[i]);
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
