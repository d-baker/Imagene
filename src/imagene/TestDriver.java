package imagene;

import imagene.arithmeticParser.*;
import imagene.arithmeticParser.parserExceptions.*;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import imagene.imagegen.Timer;
import imagene.imagegen.api.ProgramInterface;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;

import imagene.testGUI.TestFrame;
import imagene.testGUI.TestImageProgress;

/*****************************************
 * Written by Andrew Sanger (s3440468)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class TestDriver 
{
	private static IProgramInterface api;
	private static PixelMatrix p;
	private static String[][] formulas;
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;
	private final static int POPULATION_SIZE = 8;
	
	private static int currentImage = 1;
	
	public static void main(String[] args) throws InvalidArgumentException, IncorrectVariablesException 
	{
		long startTime = Timer.start();
		
		TestImageProgress currentProgress = new TestImageProgress();
		
		SampleFormulaGenerator gen = new SampleFormulaGenerator();
		api = new ProgramInterface();
		
		ParserInterface PS = ParserInterface.getInstance();

		formulas = gen.getFormulaArray(POPULATION_SIZE);
		
		currentProgress.appendString("Creating images");
		currentProgress.appendString("===============");
		
		for (int a = 0; a < formulas.length; a++) {
			String formula1 = formulas[a][0];
			String formula2 = formulas[a][1];
			String formula3 = formulas[a][2];
			
			currentProgress.appendString("Creating image number " + (a + 1));
			currentProgress.appendString("Formula 1 : " + formula1);
			currentProgress.appendString("Formula 2 : " + formula2);
			currentProgress.appendString("Formula 3 : " + formula3 + "\n");
			
			final ArithmeticNode node1 = PS.getArithmetic(formula1);
			final ArithmeticNode node2 = PS.getArithmetic(formula2);
			final ArithmeticNode node3 = PS.getArithmetic(formula3);
			
			IManipulator[] channels = new IManipulator[] {
					(x, y) -> node1.operation(x, y),
					(x, y) -> node2.operation(x, y),
					(x, y) -> node3.operation(x, y)
			};
			
			p = api.CreateImage(HEIGHT, WIDTH, channels);
			
			draw(p, p.getIntArray(), "Image" + (a + 1) + ".png");
		}
		
		long totalTime = Timer.stop(startTime);
		
		currentProgress.appendString("ALL IMAGES CREATED in "+totalTime+" milliseconds.");
		
		@SuppressWarnings("unused")
		TestFrame frame = new TestFrame();
	}
	
	public static RenderedImage makeImage(int width, int height, PixelMatrix m)
	{
		int[] data = m.getIntArray();
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    // System.out.println("WIDTH: " + width + ", HEIGHT: "+ height);
	    image.setRGB(0, 0, width, height, data, 0, width);
	    
	    return (RenderedImage) image;
	}
	
	public static void draw(PixelMatrix p, int[] style, String filename)
	{
		try {
			ImageIO.write(makeImage(p.xSize(), p.ySize(), p),
					"png", new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage returnCurrentImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Image" + currentImage + ".png"));
		} catch (IOException e) {}
		
		return img;
	}
	
	public static String[] returnCurrentFormula() {
		return formulas[currentImage - 1];
	}
	
	public static int returnImageCount() {
		return currentImage;
	}
	
	public static void incrementImageCount() {
		if (currentImage != POPULATION_SIZE) {
			currentImage++;
		}
	}
}