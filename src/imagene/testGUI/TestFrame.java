package imagene.testGUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import imagene.TestDriver;

/*****************************************
 * Written by Andrew Sanger (s3440468)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

@SuppressWarnings("serial")
public class TestFrame extends JFrame {
	private JLabel imageLabel, formulaLabel, currentImage, empty;
	private JTextArea currentFormula;
	private JButton nextImageButton;
	private NextImageHandler nextImageHandler;
	
	public TestFrame() {
		imageLabel = new JLabel("Current: Image " + TestDriver.returnImageCount());
		formulaLabel = new JLabel("Current Formulas");
		
		BufferedImage thisImage = TestDriver.returnCurrentImage();
		ImageIcon icon = new ImageIcon(thisImage);
		
		currentImage = new JLabel(icon);
		currentImage.setMaximumSize(new Dimension(500, 500));
		
		String[] currentFormulas = TestDriver.returnCurrentFormula();
		
		currentFormula = new JTextArea();
		currentFormula.setText("Formula 1: " + currentFormulas[0] +
				"\n\nFormula 2: " + currentFormulas[1] +
				"\n\nFormula 3: " + currentFormulas[2]);
		currentFormula.setLineWrap(true);
		currentFormula.setWrapStyleWord(true);
		currentFormula.setEditable(false);
		currentFormula.setMaximumSize(new Dimension(500, 500));
		empty = new JLabel();
		
		nextImageButton = new JButton("Next Image");
		nextImageHandler = new NextImageHandler();
		nextImageButton.addActionListener(nextImageHandler);
		
		Container pane = getContentPane();
		
		//pane.setLayout(new GridLayout(3, 2));
		
		JPanel labelPanel = new JPanel(new GridLayout());
		
		labelPanel.add(imageLabel);
		labelPanel.add(formulaLabel);
		pane.add(labelPanel, BorderLayout.NORTH);
		
		JPanel imageFormPanel = new JPanel(new GridLayout());
		imageFormPanel.add(currentImage);	
		imageFormPanel.add(currentFormula);
		pane.add(imageFormPanel);
		
		JPanel buttonPanel = new JPanel(new GridLayout());
		buttonPanel.add(empty);
		buttonPanel.add(nextImageButton);
		pane.add(buttonPanel, BorderLayout.SOUTH);
		
		setTitle("Imagene Test");
		
		pack();
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		TestDriver.incrementImageCount();		
	}
	
	private class NextImageHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			BufferedImage thisImage = TestDriver.returnCurrentImage();
			ImageIcon icon = new ImageIcon(thisImage);
			
			imageLabel.setText("Current: Image " + TestDriver.returnImageCount());
			
			String[] currentFormulas = TestDriver.returnCurrentFormula();
			
			currentImage.setIcon(icon);
			currentFormula.setText("Formula 1: " + currentFormulas[0] +
					"\n\nFormula 2: " + currentFormulas[1] +
					"\n\nFormula 3: " + currentFormulas[2]);
			currentFormula.setSize(500, 500);
			pack();
			
			TestDriver.incrementImageCount();
		}
	}
}
