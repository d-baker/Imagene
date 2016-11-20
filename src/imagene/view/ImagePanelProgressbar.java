package imagene.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by avishkar on 11/20/2016.
 */
public class ImagePanelProgressbar extends JPanel implements  ConstantArrayField{

    public final static int interval=1000;
    private int m;
    private Timer t;
    JProgressBar progressBar;

    public ImagePanelProgressbar()
    {
        setPreferredSize(new Dimension(500,300));
        setBackground(colorLightGray);
        progressBar=new JProgressBar(20,20);


        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START;
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.insets=new Insets(0,130,0,0);

        constraint.gridx=0;
        constraint.gridy=0;
        add(progressBar,constraint);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
