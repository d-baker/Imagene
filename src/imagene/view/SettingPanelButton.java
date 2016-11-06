package imagene.view;

import javax.swing.*;
import java.awt.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class SettingPanelButton extends JPanel {

    private JButton btnSave;
    private  JButton btnDefault;


    public SettingPanelButton()
    {
        btnSave=new JButton("OK");
        btnDefault=new JButton("Default");
       // warning=new JLabel();


        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

       constraint.anchor= GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;



        constraint.gridx=0;
        constraint.gridy=0;
        add(btnSave,constraint); //set labelCoordinate to 0,0


        constraint.anchor= GridBagConstraints.LINE_END; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.gridx=1;
        constraint.gridy=0;
        add(btnDefault,constraint);//set radioButtonCartesian to 0,1
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnDefault() {
        return btnDefault;
    }


}
