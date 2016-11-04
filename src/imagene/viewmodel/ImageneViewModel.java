package imagene.viewmodel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import imagene.arithmeticParser.ParserInterface;
import imagene.arithmeticParser.SampleFormulaGenerator;
import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;
import imagene.imagegen.api.*;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;
import imagene.view.ImageHolder;
import imagene.watchmaker.UnexpectedParentsException;
import imagene.watchmaker.endpoint.Watchmaker;
import imagene.watchmaker.gp.node.Node;

import javax.swing.*;

/*****************************************
 * Written by Callum McLennan (s3367407)
 * and Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class ImageneViewModel 
{
	private final int populationSize = 4;
	
	private IProgramInterface imageGen;
	private ParserInterface parser;
	private Watchmaker<Node> watchmaker;
	SampleFormulaGenerator dummyWatchmaker;
	
	public ImageneViewModel()
	{
		imageGen = new ProgramInterface();
		parser = ParserInterface.getInstance();
		watchmaker = new Watchmaker<Node>(populationSize);
		dummyWatchmaker = new SampleFormulaGenerator();
	}

	public List<PixelMatrix> getPopulation(int width, int height) throws InvalidArgumentException, IncorrectVariablesException
	{
		ArrayList<ArrayList<ArithmeticNode>> arithFormulas = new ArrayList<ArrayList<ArithmeticNode>>();

		int curNode = 0;
		List<Node> nodes = watchmaker.getPopulation();
		ArrayList<PixelMatrix> matrices = new ArrayList<PixelMatrix>();

		for(int i = 0; i < nodes.size(); i++)
		{
			ArrayList<ArithmeticNode> colorChannels = new ArrayList<ArithmeticNode>();

			for (int channel = 0; channel < 3; channel++) {
				Node n = nodes.get(channel + curNode);
				colorChannels.add(parser.getArithmetic(n.toString()));
			}

			arithFormulas.add(colorChannels);

			// TODO using watchmaker to parse nodes doesn't work yet cause I configured parameters wrong.
			//double x = 3.0;
			//double y = 15.0;
			//double nodeEval = n.evaluate(new double[] {0.0, 1.0});
		}

/*
		String[][] arithFormulas = dummyWatchmaker.getFormulaArray(populationSize);

		for (int a = 0; a < arithFormulas.length; a++) {
			String formula1 = arithFormulas[a][0];
			String formula2 = arithFormulas[a][1];
			String formula3 = arithFormulas[a][2];

			final ArithmeticNode node1 = parser.getArithmetic(formula1);
			final ArithmeticNode node2 = parser.getArithmetic(formula2);
			final ArithmeticNode node3 = parser.getArithmetic(formula3);

			IManipulator[] channels = new IManipulator[] {
					(x, y) -> node1.operation(x, y),
					(x, y) -> node2.operation(x, y),
					(x, y) -> node3.operation(x, y)
			};

			PixelMatrix pixelMatrix = imageGen.CreateImage(width, height, channels);
			matrices.add(pixelMatrix);
		}
*/
		// TODO check that nested array is actually working

		for (int a = 0; a < arithFormulas.size(); a++) {
			ArrayList<ArithmeticNode> colorChannels = arithFormulas.get(a);
			ArithmeticNode r = colorChannels.get(0);
			ArithmeticNode g = colorChannels.get(1);
			ArithmeticNode b = colorChannels.get(2);

			try {
				IManipulator[] channels = new IManipulator[] {
						(x, y) -> r.operation(x, y),
						(x, y) -> g.operation(x, y),
						(x, y) -> b.operation(x, y)
				};

				PixelMatrix pixelMatrix = imageGen.CreateImage(width, height, channels);
				matrices.add(pixelMatrix);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return matrices;
	}
	
	public void chooseWinners(int[] winners)
	{
		System.out.println("choosing winners");
		watchmaker.chooseWinners(winners);
	}
	
	public void chooseWinners(List<Integer> winners)
	{
		System.out.println("choosing winners");
		watchmaker.chooseWinners(winners);
	}
	
	public void newGeneration() throws UnexpectedParentsException
	{
		watchmaker.Evolve();
	}
}
