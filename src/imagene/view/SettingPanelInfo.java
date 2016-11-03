package imagene.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class SettingPanelInfo extends JPanel implements ConstantArrayField {

    private JLabel infoCoordinate;
    private JLabel infoSymmetry;
    private JLabel infoImageWidth;
    private JLabel infoImageHeight;

    private JLabel info;

   public  SettingPanelInfo()
    {
        infoCoordinate=new JLabel();
        infoSymmetry=new JLabel();
        infoImageWidth=new JLabel();
        infoImageHeight=new JLabel();
        info=new JLabel("Info:");

        infoCoordinate.setForeground(colorBlue);
        infoSymmetry.setForeground(colorBlue);
        infoImageWidth.setForeground(colorBlue);
        infoImageHeight.setForeground(colorBlue);
        info.setForeground(colorBlue);


        setBorder(new EmptyBorder(10,10,0,0));

        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;

        constraint.gridx=0;
        constraint.gridy=0;
        add(info,constraint); //set labelCoordinate to 0,0


        constraint.gridx=0;
        constraint.gridy=1;
        add(infoCoordinate,constraint); //set labelCoordinate to 0,0

        constraint.gridx=0;
        constraint.gridy=2;
        add(infoSymmetry,constraint);//set radioButtonCartesian to 0,1

       // constraint.anchor=GridBagConstraints.LINE_START;
        constraint.gridx=0;
        constraint.gridy=3;
        add(infoImageWidth,constraint);//set radioButtonCartesian to 0,1

        constraint.gridx=0;
        constraint.gridy=4;
        add(infoImageHeight,constraint);//set radioButtonCartesian to 0,1


    }

    public JLabel getInfoCoordinate() {
        return infoCoordinate;
    }

    public JLabel getInfoSymmetry() {
        return infoSymmetry;
    }

    public JLabel getInfoImageWidth() {
        return infoImageWidth;
    }

    public JLabel getInfoImageHeight() {
        return infoImageHeight;
    }
}
