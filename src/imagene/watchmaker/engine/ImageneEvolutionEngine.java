package imagene.watchmaker.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.tree.TreeCrossover;
import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;

import imagene.watchmaker.gp.tree.TreeFactory;

public class ImageneEvolutionEngine<T> extends AbstractEvolutionEngine<T> {
	private final double WinScore = 1d, LossScore = 0d;

	private List<T> _population;
	private int _populationSize;
	private List<T> _parents;

	private List<EvaluatedCandidate<T>> _evaluatedCandidates;

	private TreeFactory _factory;
	private TreeCrossover _crossover;
	private int crossoverPoints = 2;

	private Random _rng;

	
	public ImageneEvolutionEngine(final int populationSize, TreeFactory factory, Random rng)
	{
		super(null, null, null);
		_parents = new ArrayList<T>();
		_evaluatedCandidates = new ArrayList<EvaluatedCandidate<T>>();

		_factory = factory;
		_crossover = new TreeCrossover();
		_rng = rng;

		_populationSize = populationSize;
		_population = GenerateInitialPopulation();
	}

	@Override
	protected List<EvaluatedCandidate<T>> nextEvolutionStep(List<EvaluatedCandidate<T>> arg0, int arg1, Random arg2) 
	{
		return null;
	}
	
	public List<T> getPopulation()
	{
		return _population;
	}
	
	public void evolve()
	{
		_population = Evaluate();		
	}
	
	private List<T> Evaluate()
	{
		List<T> newPopulation = new ArrayList<T>();
		// TODO evaluatedCandidates is null because never initialised, where do we initialise it?

		for(EvaluatedCandidate<T> t : _evaluatedCandidates)
		{
			if (t.getFitness() > 0d) {
				_parents.add(t.getCandidate());
			}
		}

		newPopulation.addAll(_parents);

		for (int i = 0; i < _populationSize - _parents.size(); i++) {
			// TODO don't hardcode parents - check number of parents and act accordingly? mutation for 1?
			newPopulation.addAll((List<T>) _crossover.mate((Node)_parents.get(0), (Node)_parents.get(1), crossoverPoints, _rng) );
		}

		return newPopulation;
	}
	
	private List<T> GenerateInitialPopulation()
	{
		List<T> population = new ArrayList<T>();
		for(int i = 0; i < _populationSize; i++)
		{
			population.add((T) _factory.generateRandomCandidate(_rng));
		}
		
		return population;
	}
	
	public void survive(List<Integer> winners)
	{			
		for(int i = 0; i < _populationSize; i++)
		{
			// TODO breakpoint here
			if(winners.contains(i))
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), WinScore));
			else
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), LossScore));
		}
	}	
}
