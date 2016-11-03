package imagene.view;//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Base class for examples that run as applets.
 * @author Daniel Dyer
 */
public abstract class AbstractExampleGUI extends JFrame implements ConstantArrayField
{
    /**
     * {@inheritDoc}
     */

    public void init()
    {
        configure(this);
    }


    /**
     * Configure the program to display its imagene.view in the specified container.
     * @param container The container to place the imagene.view components in.
     */
    private void configure(final Container container)
    {
        try
        {
            // Use invokeAndWait so that we can be sure that initialisation is complete
            // before continuing.
            SwingUtilities.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }
                    catch (Exception ex)
                    {
                        // This should never happen as we are installing a known look-and-feel.
                        System.err.println("Failed to load System look-and-feel.");
                    }
                    prepareGUI(container);
                }
            });
        }
        catch (InterruptedException ex)
        {
            // Restore interrupt flag.
            Thread.currentThread().interrupt();
        }
        catch (InvocationTargetException ex)
        {
            ex.getCause().printStackTrace();
            JOptionPane.showMessageDialog(container, ex.getCause(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Implemented in sub-classes to initialise and layout the imagene.view.
     * @param container The container that this method should add components to.
     */
    protected abstract void prepareGUI(Container container);


    /**
     * Display this example program using a JFrame as the top-level imagene.view container (rather
     * than running the example as an applet).
     * @param title The text to use for the frame's title bar.
     */
    protected void displayInFrame(String title)
    {
        JFrame frame = new JFrame("Image Evolver v1.0");

        JPanel panelAbout=new JPanel();
        JPanel panelInstruction=new JPanel();
        JMenuBar menuBar=new JMenuBar();
        //menuBar.setBackground(new Color(255,178,102));
        JMenu file=new JMenu("File");
        JMenu help=new JMenu("Help");
        JMenuItem close=new JMenuItem("Close");

        File directory = new File("temp/ImageEvolver");
        directory.mkdirs();
        File tmp = new File(directory, "UserSettings.xml");


        close.setMnemonic(KeyEvent.VK_Q);
        close.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        close.setToolTipText("Exit application");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });


        JMenuItem about=new JMenuItem("About");
        JMenuItem instruction=new JMenuItem("Instruction");

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, APP_ABOUT,"About",JOptionPane.INFORMATION_MESSAGE);
            }

        });

        instruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JOptionPane.showMessageDialog(null, APP_INSTRUCTION,"Instruction",JOptionPane.INFORMATION_MESSAGE);

            }

        });

        file.add(close);
        help.add(about);
        help.add(instruction);
        menuBar.add(file);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,680);
        configure(frame);
        //frame.pack();
        frame.setVisible(true);
    }
}
