package imagene.testGUI;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TestImageProgress extends JFrame {
	private JTextArea currentProgress;
	private String progressString = "";
	public TestImageProgress() {
		currentProgress = new JTextArea();
		currentProgress.setLineWrap(false);
		currentProgress.setWrapStyleWord(false);
		currentProgress.setEditable(false);
		currentProgress.setMaximumSize(new Dimension(1000, 750));
		
		Container pane = getContentPane();
		
		pane.add(currentProgress);
		
		setTitle("Imagene Test Image Generation Progress");
		
		setSize(1000, 750);
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void appendString(String appendage) {
		progressString = progressString + appendage + "\n";
		
		currentProgress.setText(progressString);
		currentProgress.setSize(1000, 750);
	}
}
