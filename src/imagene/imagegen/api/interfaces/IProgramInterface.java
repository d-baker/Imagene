package imagene.imagegen.api.interfaces;

import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;
/*****************************************
 * Written by Callum McLennan (s3367407) *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public interface IProgramInterface 
{
	public PixelMatrix CreateImage(int x, int y, IManipulator m);
	public PixelMatrix CreateImage(int x, int y, IManipulator[] m);
	public boolean Validate(int x, int y, IManipulator m);
	PixelMatrix CreatePolarImage(int xOrigin, int yOrigin, int x, int y, IManipulator[] channels);
}
