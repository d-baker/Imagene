package imagene.imagegen.polar.models;

import imagene.imagegen.polar.models.Coordinate;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/


public class PolarCoordinate extends Coordinate 
{
	private double radius;
	private double theta; 

	public PolarCoordinate(int xOrigin, int yOrigin, int x, int y) {
		super(x - xOrigin, y - yOrigin);
		
		calculateRadius();
		calculateTheta();
	}
	
	public PolarCoordinate(Coordinate origin, int x, int y) {
		super(x - origin.x, y - origin.y);
	}
	
	public PolarCoordinate(Coordinate origin, Coordinate point) {
		super(point.x - origin.x, point.y - origin.y);
	}
	
	private void calculateRadius()
	{
		radius = Math.sqrt(Math.pow(Math.abs(x), 2) 
				+ Math.pow(Math.abs(y), 2));
	}
	
	private void calculateTheta()
	{
		if(x == 0)
			x++;
		radius = Math.atan(Math.abs(y) / Math.abs(x));
	}
	
	public double getRadius(){
		return radius;
	}
	
	public double getTheta(){
		return theta;
	}
}
