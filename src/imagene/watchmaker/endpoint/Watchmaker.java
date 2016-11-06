package imagene.watchmaker.endpoint;

import java.util.ArrayList;
import java.util.List;

import imagene.watchmaker.UnexpectedParentsException;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import imagene.watchmaker.engine.ImageneEvolutionEngine;
import imagene.watchmaker.gp.tree.TreeFactory;

/*****************************************
 * Written by Callum McLennan (s3367407)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class Watchmaker<T> {
	private List<EvaluatedCandidate<T>> _currentPopulation;
	private final int _populationSize;
	private EvolutionaryOperator<T> _mutation;
	private TreeFactory _factory;
	private ImageneEvolutionEngine<T> _engine;
	
	public Watchmaker(final int populationSize)
	{
		_populationSize = populationSize;
		SetSpecies();
	}
	
	private void SetSpecies()
	{
        int parameterCount, maxDepth;
        Probability oneArgFuncProb, paramProb, constantProb;

		parameterCount = 3;
		maxDepth = 5;
        oneArgFuncProb = new Probability(0.3);
        paramProb = new Probability(0.3);
		constantProb = new Probability(0.1);

        _factory = new TreeFactory(parameterCount, maxDepth, oneArgFuncProb, paramProb, constantProb);
        _engine = new ImageneEvolutionEngine<T>(_populationSize, _factory, new MersenneTwisterRNG());

	}
	
	public void Evolve() throws UnexpectedParentsException
	{
		_engine.evolve();
	}	
	
	public List<T> getPopulation()
	{
		return _engine.getPopulation();
	}
	
	public void chooseWinners(int[] winners)
	{
		List<Integer> win = new ArrayList<Integer>();
		
		for(int i : winners)
			win.add(i);
		
		chooseWinners(win);
	}
	
	public void chooseWinners(List<Integer> winners)
	{
		_engine.survive(winners);
	}
}
