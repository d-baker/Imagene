package imagene.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import imagene.arithmeticParser.ParserInterface;
import imagene.arithmeticParser.SampleFormulaGenerator;
import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;
import imagene.imagegen.api.*;
import imagene.imagegen.api.interfaces.IProgramInterface;
import imagene.imagegen.api.interfaces.ProgramInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;
import imagene.watchmaker.UnexpectedParentsException;
import imagene.watchmaker.endpoint.Watchmaker;
import imagene.watchmaker.gp.node.Node;

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
	private SampleFormulaGenerator dummyWatchmaker;

	private static ImageneViewModel instance = null;

	private final double[] randomNumbers = new double[]{
			new Random().nextInt(10),
			new Random().nextInt(10),
			new Random().nextInt(10)
	};


	private ImageneViewModel() {
		imageGen = new ProgramInterface();
		parser = ParserInterface.getInstance();
		watchmaker = new Watchmaker<Node>(populationSize);
		dummyWatchmaker = new SampleFormulaGenerator();
		System.out.println("RANDOM NUMBERS: " + randomNumbers.toString());
	}

	public static ImageneViewModel getInstance()
	{
		if(instance == null) {
			instance = new ImageneViewModel();
		}
		return instance;
	}

	public List<PixelMatrix> getPopulation(int width, int height, String coordType, String symmetryType) throws InvalidArgumentException, IncorrectVariablesException
	{
		ArrayList<PixelMatrix> matrices = new ArrayList<PixelMatrix>();

		List<Node> nodes = watchmaker.getPopulation();
		ArrayList<ArrayList<Node>> formulas = new ArrayList<ArrayList<Node>>();

		System.out.println(">>>>>>POPULATION<<<<<");

		for(int curNode = 0; curNode < nodes.size() / 3; curNode++)
		{
			ArrayList<Node> colorChannels = new ArrayList<Node>();

			for (int channel = curNode * 3; channel < (curNode * 3) + 3; channel++) {
				Node n = nodes.get(channel);
				System.out.println(n.toString());

				colorChannels.add(n);
			}

			formulas.add(colorChannels);
		}


		for (int a = 0; a < formulas.size(); a++) {
			ArrayList<Node> colorChannels = formulas.get(a);
			Node r = colorChannels.get(0);
			Node g = colorChannels.get(1);
			Node b = colorChannels.get(2);

			try {
				IManipulator[] channels = new IManipulator[] {
						(x, y) -> r.evaluate(new double[] {x, y, randomNumbers[0], randomNumbers[1], randomNumbers[2]}),
						(x, y) -> g.evaluate(new double[] {x, y, randomNumbers[0], randomNumbers[1], randomNumbers[2]}),
						(x, y) -> b.evaluate(new double[] {x, y, randomNumbers[0], randomNumbers[1], randomNumbers[2]})
				};

				PixelMatrix pixelMatrix;

				if (coordType == "Cartesian") {
					if (symmetryType == "Symmetric") {
						pixelMatrix = imageGen.CreateSymmetricalImage(width, height, channels);
					} else {
						pixelMatrix = imageGen.CreateImage(width, height, channels);
					}
				} else if (coordType == "Polar") {
					if (symmetryType == "Symmetric") {
						pixelMatrix = imageGen.CreateSymmetricalPolarImage(width / 2, height / 2, width, height, channels);
					} else {
						pixelMatrix = imageGen.CreatePolarImage(width / 2, height / 2, width, height, channels);
					}
				} else {
					throw new InvalidArgumentException("Unexpected coordinate type");
				}

				matrices.add(pixelMatrix);

			} catch (Exception e) {
				e.printStackTrace();
			}
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
