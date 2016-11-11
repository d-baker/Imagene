package imagene.view;

import javax.swing.*;
import java.awt.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class ImagePanelLabel extends JPanel implements ConstantArrayField {

    private JLabel label;

    public ImagePanelLabel()
    {
        setPreferredSize(new Dimension(500,300));
        setBackground(colorLightGray);
        label=new JLabel("Please click to select 2 Images before generating.");
        add(label);

        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START;
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.insets=new Insets(0,130,0,0);

        constraint.gridx=0;
        constraint.gridy=0;
        add(label,constraint);
    }


    public JLabel getLabel() {
        return label;
    }
}
