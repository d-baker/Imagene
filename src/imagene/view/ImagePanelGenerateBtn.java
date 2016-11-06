package imagene.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by avishkar on 11/5/2016.
 */
public class ImagePanelGenerateBtn extends JPanel implements ConstantArrayField{

    private JButton generateBtn;


    public ImagePanelGenerateBtn()
    {
        setPreferredSize(new Dimension(500,200));
        setBackground(colorLightGray);
        generateBtn =new JButton("Generate");


        generateBtn.setFont(new Font("Serif",Font.BOLD,15));
        generateBtn.setBackground(colorRed);

        generateBtn.setEnabled(false);

        add(generateBtn);
        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START;
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.insets=new Insets(0,200,0,0);

        constraint.gridx=0;
        constraint.gridy=0;
        add(generateBtn,constraint);


    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }


}
