package imagene.imagegen.models;

import imagene.imagegen.polar.models.PolarCoordinate;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * and Dorothea Baker (s3367422)         *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class PixelMatrix 
{
	private Pixel[][] matrix;
	
	public PixelMatrix(int xDim, int yDim)
	{
		matrix = new Pixel[yDim][xDim];
		set(new Pixel(0, 0, 0));
	}
	
	public PixelMatrix(Pixel p, int size) 
	{ 
		matrix = new Pixel[size][size]; 
		set(p);
	}
	
	public PixelMatrix() { }
	public PixelMatrix(PixelMatrix m) {	set(m); }
	public PixelMatrix(Pixel[][] m)	{ set(m); }

	public Pixel[][] get()
	{
		return this.matrix;
	}
	
	public Pixel get(int y, int x)
	{
		return this.matrix[y][x];
	}
	
	public void set(int y, int x, Pixel p)
	{
		matrix[y][x] = p;
	}
	
	public void set(Pixel[][] m)
	{
		matrix = m;
	}
	
	public void set(PixelMatrix m)
	{
		matrix = m.get();
	}
	
	public void set(Pixel p)
	{
		for(int i = 0; i < matrix[0].length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				matrix[j][i] = p;
			}
		}
	}
	
	public void set(int y, int x, short a, int colourChannel)
	{
		switch(colourChannel)
		{
			case 0: 
				setR(y, x, a); 
				break;
			case 1: 
				setG(y, x, a); 
				break;
			case 2: 
				setB(y, x, a); 
				break;
		}
	}
	
	private void setR(int y, int x, short r)
	{
		matrix[y][x].setR(r);
	}
	
	private void setG(int y, int x, short g)
	{
		matrix[y][x].setG(g);
	}
	
	private void setB(int y, int x, short b)
	{
		matrix[y][x].setB(b);
	}
	
	public void print()
	{
		for(int i = 0; i < matrix[0].length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				System.out.print(matrix[j][i].ToString());
			}
			System.out.println();
		}
	}
	
	public int xSize()
	{
		return matrix[0].length; 
	}
	
	public int ySize()
	{
		return matrix.length;
	}

	public byte[] getByteArray()
	{
		byte[] bArray = new byte[getSize()*3];

		for(int i = 0; i < matrix[0].length - 1; i++)
		{
			for(int j = 0; j < matrix.length - 1; j++)
			{
				bArray[(3 * (i + j))] = (byte) matrix[i][j].r().value();
				bArray[(3 * (i + j)) + 1] = (byte) matrix[i][j].g().value();
				bArray[(3 * (i + j)) + 2] = (byte) matrix[i][j].b().value();
			}
		}

		return bArray;
	}
	
	public int[] getIntArray()
	{
		int i = 0,	width = matrix[0].length, height = matrix.length;
		int[] iArray = new int[width*height];
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				iArray[i++] = getRGB(matrix[y][x].r().value(), matrix[y][x].g().value(), matrix[y][x].b().value());
			}
		}
		
		return iArray;
	}
	
	public PolarCoordinate[][] getPolarArray(int xOrigin, int yOrigin)
	{
		int  width = matrix[0].length, height = matrix.length;
		PolarCoordinate[][] pArray = new PolarCoordinate[height][width];
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				PolarCoordinate pc = new PolarCoordinate(xOrigin, yOrigin, x, y);
				pArray[y][x] = pc;
			}
		}
		
		return pArray;
	}
	
	public int getSize()
	{
		return matrix[0].length * matrix.length;
	}
	
	private int getRGB(int r, int g, int b)
	{
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}
}
