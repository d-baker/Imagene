package imagene.watchmaker.engine;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import imagene.watchmaker.UnexpectedParentsException;
import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.tree.TreeCrossover;
import imagene.watchmaker.gp.tree.TreeMutation;
import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;

import imagene.watchmaker.gp.tree.TreeFactory;

/*****************************************
 * Written by Callum McLennan (s3367407)
 * and Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class ImageneEvolutionEngine<T> extends AbstractEvolutionEngine<T> {
	private final double WinScore = 1d, LossScore = 0d;

	private List<T> _population;
	private int _populationSize;
	private List<T> _parents;

	private List<EvaluatedCandidate<T>> _evaluatedCandidates;

	private TreeFactory _factory;
	private TreeCrossover _crossover;
	private TreeMutation _mutation;
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

		_populationSize = populationSize * 3;
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
	
	public void evolve() throws UnexpectedParentsException
	{
		_population = Evaluate();		
	}
	
	private List<T> Evaluate() throws UnexpectedParentsException
	{
		List<T> newPopulation = new ArrayList<T>();
		for(EvaluatedCandidate<T> t : _evaluatedCandidates)
		{
			if (t.getFitness() > 0d) {
				_parents.add(t.getCandidate());
			}
		}

		if (_parents.size() == 2) {
			// Elitism - add parents first
			newPopulation.addAll(_parents);

			// Then crossover of parents to create remaining population
			for (int i = 0; i < _populationSize - _parents.size(); i++) {
				// TreeCrossover generates 2 children by crossover
				List<Node> twoNewChildren = (_crossover.mate((Node) _parents.get(0), (Node) _parents.get(1), crossoverPoints, _rng));

				// Discard one of the two children generated - this seems to be the standard thing to do in GP?
				T favoriteChild = (T)twoNewChildren.get(0);

				// Add the newly generated individual to the population
				newPopulation.add(favoriteChild);
			}

		} else if (_parents.size() == 1) {
			// Elitism - add parents first
			newPopulation.addAll(_parents);

			// Remaining population is population minus parent we added via elitism, as we don't want to mutate the parent
			List<T> remainingPopulation = newPopulation;
			remainingPopulation.remove(_parents.get(0));

			// Create rest of the population by randomly mutating the remaining population
			newPopulation.addAll((List<T>) _mutation.apply((List<Node>)remainingPopulation, _rng));

		} else {
			System.out.println("num parents is " + _parents.size());
			//throw new UnexpectedParentsException("Number of parents: " + _parents.size());
		}

		// TODO reset fitness of previous parents
		_evaluatedCandidates.clear(); // Empty the list for future use

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
		// TODO 4 here!!
		for(int i = 0; i < _populationSize; i++)
		{
			if(winners.contains(i))
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), WinScore));
			else
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), LossScore));
		}
	}	
}
