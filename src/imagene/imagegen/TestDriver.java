package imagene.imagegen;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagene.imagegen.models.PixelMatrix;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class TestDriver 
{
	static imagene.imagegen.api.interfaces.IProgramInterface api;
	static PixelMatrix p;
	static imagene.imagegen.manipulator.interfaces.IManipulator function;
	
	public static void main(String[] args) 
	{
		api = new imagene.imagegen.api.ProgramInterface();
		p = api.CreatePolarImage(50, 50, 100, 100, new imagene.imagegen.manipulator.interfaces.IManipulator[] { (x, y) -> (x + y), (x, y) -> (x - y), (x, y) -> (x * y) });
		
		draw(p, p.getIntArray(), "x+y.png");
	}
	
	public static RenderedImage makeImage(int width, int height, int[] m)
	{
		int[] data = m;
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    System.out.println("WIDTH: " + width + ", HEIGHT: "+ height);
	    image.setRGB(0, 0, width, height, data, 0, width);
	    
	    return (RenderedImage) image;
	}
	
	public static void draw(PixelMatrix p, int[] style, String filename)
	{
		try {
			ImageIO.write(makeImage(p.xSize(), p.ySize(), style), 
					"png", new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}