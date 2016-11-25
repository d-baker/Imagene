package imagene.imagegen.api.interfaces;

import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.Pixel;
import imagene.imagegen.models.PixelMatrix;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.models.PixelValueBoundary;
import imagene.imagegen.polar.models.PolarCoordinate;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * and Dorothea Baker (s3367422)         *
 * and Andrew Sanger (s3440468)          *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/


public class ProgramInterface implements IProgramInterface
{
	
	public ProgramInterface()
	{
		
	}

	@Override
	public PixelMatrix CreateImage(int x, int y, IManipulator formula)
	{
		// If a single formula is provided, duplicate it 3 times so that each color channel is the same
		return CreateImage(x, y, new IManipulator[] {formula, formula, formula});
	}
	
	// BUG FIXED - Andrew Sanger
	//
	// Fixed error where Polar Coordinates didn't work properly
	@Override
	public PixelMatrix CreateSymmetricalPolarImage(int xOrigin, int yOrigin, int x, int y, IManipulator[] channels)
	{
		PixelMatrix matrix = new PixelMatrix(x, y);
		PolarCoordinate[][] pc = matrix.getPolarArray(xOrigin, yOrigin);
		
		PixelValueBoundary boundary = new PixelValueBoundary();
		
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				int symX = dimX;
				if(dimX > x/2) symX = x/2 - (dimX - x/2);
				
				Pixel pixel = new Pixel(channels[0].manipulate(pc[dimY][symX].getTheta(), pc[dimY][symX].getRadius()), 
						channels[1].manipulate(pc[dimY][symX].getTheta(), pc[dimY][symX].getRadius()), 
						channels[2].manipulate(pc[dimY][symX].getTheta(), pc[dimY][symX].getRadius()));
				
				double r, g, b;
				r = pixel.r().value();
				g = pixel.g().value();
				b = pixel.b().value();

				if ( r > boundary.rMax() ) boundary.setRMax(r);
				if ( g > boundary.gMax() ) boundary.setGMax(g);
				if ( g > boundary.bMax() ) boundary.setBMax(b);


				if ( r < boundary.rMin() ) boundary.setRMin(r);
				if ( g < boundary.gMin() ) boundary.setGMin(g);
				if ( g < boundary.bMin() ) boundary.setBMin(b);

				matrix.set(dimY, dimX, pixel);
			}
		}
		
		matrix = ScaleImage(matrix, boundary);
		
		return matrix;
	}
	
	@Override
	public PixelMatrix CreatePolarImage(int xOrigin, int yOrigin, int x, int y, IManipulator[] channels)
	{
		PixelMatrix matrix = new PixelMatrix(x, y);
		PolarCoordinate[][] pc = matrix.getPolarArray(xOrigin, yOrigin);

		PixelValueBoundary boundary = new PixelValueBoundary();

		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				Pixel pixel = new Pixel(channels[0].manipulate(pc[dimY][dimX].getTheta(), pc[dimY][dimX].getRadius()), 
						channels[1].manipulate(pc[dimY][dimX].getTheta(), pc[dimY][dimX].getRadius()), 
						channels[2].manipulate(pc[dimY][dimX].getTheta(), pc[dimY][dimX].getRadius()));

				double r, g, b;
				r = pixel.r().value();
				g = pixel.g().value();
				b = pixel.b().value();

				if ( r > boundary.rMax() ) boundary.setRMax(r);
				if ( g > boundary.gMax() ) boundary.setGMax(g);
				if ( g > boundary.bMax() ) boundary.setBMax(b);


				if ( r < boundary.rMin() ) boundary.setRMin(r);
				if ( g < boundary.gMin() ) boundary.setGMin(g);
				if ( g < boundary.bMin() ) boundary.setBMin(b);

				matrix.set(dimY, dimX, pixel);
			}
		}

		matrix = ScaleImage(matrix, boundary);

		return matrix;
	}
	@Override
	public PixelMatrix CreateSymmetricalImage(int x, int y, IManipulator[] channels)
	{
		PixelMatrix matrix = new PixelMatrix(x, y);
		PixelValueBoundary boundary = new PixelValueBoundary();

		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				int symX = dimX;
				if(dimX > x/2) symX = x/2 - (dimX - x/2);
				
				Pixel pixel = new Pixel(channels[0].manipulate(symX, dimY), channels[1].manipulate(symX, dimY), channels[2].manipulate(symX, dimY));

				double r, g, b;
				r = pixel.r().value();
				g = pixel.g().value();
				b = pixel.b().value();

				if ( r > boundary.rMax() ) boundary.setRMax(r);
				if ( g > boundary.gMax() ) boundary.setGMax(g);
				if ( g > boundary.bMax() ) boundary.setBMax(b);


				if ( r < boundary.rMin() ) boundary.setRMin(r);
				if ( g < boundary.gMin() ) boundary.setGMin(g);
				if ( g < boundary.bMin() ) boundary.setBMin(b);

				matrix.set(dimY, dimX, pixel);
			}
		}

		matrix = ScaleImage(matrix, boundary);

		return matrix;
	}

	@Override
	public PixelMatrix CreateImage(int x, int y, IManipulator[] channels)
	{
		PixelMatrix matrix = new PixelMatrix(x, y);

		PixelValueBoundary boundary = new PixelValueBoundary();

		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				Pixel pixel = new Pixel(channels[0].manipulate(dimX, dimY), channels[1].manipulate(dimX, dimY), channels[2].manipulate(dimX, dimY));

				double r, g, b;
				r = pixel.r().value();
				g = pixel.g().value();
				b = pixel.b().value();

				if ( r > boundary.rMax() ) boundary.setRMax(r);
				if ( g > boundary.gMax() ) boundary.setGMax(g);
				if ( g > boundary.bMax() ) boundary.setBMax(b);


				if ( r < boundary.rMin() ) boundary.setRMin(r);
				if ( g < boundary.gMin() ) boundary.setGMin(g);
				if ( g < boundary.bMin() ) boundary.setBMin(b);

				matrix.set(dimY, dimX, pixel);
			}
		}

		matrix = ScaleImage(matrix, boundary);

		return matrix;
	}
	
	@Override
	public boolean Validate(int x, int y, IManipulator m)
	{
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				try {
					m.manipulate(dimX, dimY);
				} catch(Exception ex) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private PixelMatrix ScaleImage(PixelMatrix matrix, PixelValueBoundary boundary)
	{
		int x = matrix.xSize(), y = matrix.ySize();
		
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				Pixel curPixel = matrix.get(dimY, dimX);
				short rScaled, gScaled, bScaled;

				rScaled = ScalePixel(curPixel.r().value(), boundary.rMin(), boundary.rMax());
				gScaled = ScalePixel(curPixel.g().value(), boundary.gMin(), boundary.gMax());
				bScaled = ScalePixel(curPixel.b().value(), boundary.bMin(), boundary.bMax());

				Pixel scaledPixel = new Pixel(rScaled, gScaled, bScaled);

				matrix.set(dimY, dimX, scaledPixel);
			}
		}

		return matrix;
	}
	
	private short ScalePixel(double value, double min, double max) {
		  return (short)Math.round(255 * (value - min) / (max - min));
	}
}
