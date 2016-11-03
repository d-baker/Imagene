/****************************
 * Arithmetic Parser v1.2   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserNodes;

// This is the abstract class that ArithmeticNode, DummyNode and
// PixelNode all extend.
public abstract class ParserNode {
	public ParserNode() {		
	}
	
	public abstract double operation(int xValue, int yValue);

	public abstract PixelNode operation(PixelNode xPixel, PixelNode yPixel);
}
