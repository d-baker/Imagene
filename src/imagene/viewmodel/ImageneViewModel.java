package imagene.viewmodel;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
import imagene.watchmaker.endpoint.Watchmaker;
import imagene.watchmaker.gp.node.Node;

import javax.swing.*;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class ImageneViewModel 
{
	private final int populationSize = 4;
	
	private IProgramInterface imageGen;
	private ParserInterface parser;
	private Watchmaker<Node> watchmaker;
	
	public ImageneViewModel()
	{
		imageGen = new ProgramInterface();
		parser = ParserInterface.getInstance();
		watchmaker = new Watchmaker<Node>(populationSize);
	}

	// TODO errors with width and height passed in from static SettingPanel property. probably due to async stuff
	public List<PixelMatrix> getPopulation(int width, int height) throws InvalidArgumentException, IncorrectVariablesException
	{
		/*
		List<ArithmeticNode> formulas = new ArrayList<ArithmeticNode>();
		
		for(Node n : watchmaker.getPopulation())
		{
			formulas.add(parser.getArithmetic(n.toString()));
		}
		*/
		
		String[][] formulas;

		// dummy formula generator (will eventually be replaced with watchmaker calls)
		SampleFormulaGenerator gen = new SampleFormulaGenerator();
		formulas = gen.getFormulaArray(populationSize);

		ArrayList<PixelMatrix> matrices = new ArrayList<PixelMatrix>();

		for (int a = 0; a < formulas.length; a++) {
			String formula1 = formulas[a][0];
			String formula2 = formulas[a][1];
			String formula3 = formulas[a][2];

			try {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return matrices;
	}
	
	public void chooseWinners(int[] winners)
	{
		watchmaker.chooseWinners(winners);
	}
	
	public void chooseWinners(List<Integer> winners)
	{
		watchmaker.chooseWinners(winners);
	}
	
	public void newGeneration()
	{
		watchmaker.Evolve();
	}
}
