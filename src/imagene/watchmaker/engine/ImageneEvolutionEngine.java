package imagene.watchmaker.engine;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import imagene.watchmaker.UnexpectedParentsException;
import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.tree.TreeCrossover;
import imagene.watchmaker.gp.tree.TreeMutation;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;

import imagene.watchmaker.gp.tree.TreeFactory;

/*****************************************
 * Written by Callum McLennan (s3367407),
 * Dorothea Baker (s3367422) and
 * Andrew Sanger (s3440468)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

public class ImageneEvolutionEngine<T> extends AbstractEvolutionEngine<T> {
	private final double WinScore = 1d, LossScore = 0d;
	
	// Added for readability
	private final double _Channels = 3;

	private List<T> _population;
	private int _populationSize;

	private List<EvaluatedCandidate<T>> _evaluatedCandidates;

	private TreeFactory _factory;
	private TreeCrossover _crossover;
	private TreeMutation _mutation;
	private int crossoverPoints = 2;

	private Random _rng;

	
	public ImageneEvolutionEngine(final int populationSize, TreeFactory factory, Random rng)
	{
		super(null, null, null);
		_evaluatedCandidates = new ArrayList<EvaluatedCandidate<T>>();

		_factory = factory;
		_crossover = new TreeCrossover();
		_mutation = new TreeMutation(factory, new Probability(0.5));
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
		List<T> _parents = new ArrayList<T>();

		for(EvaluatedCandidate<T> t : _evaluatedCandidates)
		{
			if (t.getFitness() > 0d) {
				_parents.add(t.getCandidate());
			}
		}

		System.out.println("num parents is " + _parents.size());

		System.out.println(">>>>> PARENTS <<<<<<");
		for (int a = 0; a < _parents.size(); a++) {
			System.out.println(_parents.get(a).toString());
		}

		// Elitism
		newPopulation.addAll(_parents);

		// Generate the remaining population by mating parents
		// >= 2 is a workaround for the weird first-generation bug of having too many parents
		if (_parents.size() >= 2) {

			int numNewIndividuals = 2;

			for (int i = 0; i < numNewIndividuals; i++) {
				List<Node> twoNewChildrenR = (_crossover.mate((Node) _parents.get(0), (Node) _parents.get(3), crossoverPoints, _rng));
				List<Node> twoNewChildrenG = (_crossover.mate((Node) _parents.get(1), (Node) _parents.get(4), crossoverPoints, _rng));
				List<Node> twoNewChildrenB = (_crossover.mate((Node) _parents.get(2), (Node) _parents.get(5), crossoverPoints, _rng));

				T favoriteChildR = (T) twoNewChildrenR.get(0);
				T favoriteChildG = (T) twoNewChildrenG.get(0);
				T favoriteChildB = (T) twoNewChildrenB.get(0);

				System.out.println(i);

				newPopulation.add(favoriteChildR);
				newPopulation.add(favoriteChildG);
				newPopulation.add(favoriteChildB);
			}

		} else  if (_parents.size() == 1) {
			// If only one parent is supplied, mutate it to create the rest of the next generation.
			// Possible future functionality, not currently operable due to frontend limitations.
			List<T> remainingPopulation = _population;
			remainingPopulation.remove(_parents.get(0));
			newPopulation.addAll((List<T>) _mutation.apply((List<Node>)remainingPopulation, _rng));
		} else {
			// We don't want, or know how, to handle other numbers of parents
			throw new UnexpectedParentsException("Number of parents: " + _parents.size());
		}

		_evaluatedCandidates.clear();

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
		for(int i = 0; i < _populationSize / _Channels; i++)
		{
			// Added these for the sake of readability
			int redNum = i * 3;
			int greenNum = redNum + 1;
			int blueNum = redNum + 2;
			
			if(winners.contains(i)) {				
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(redNum), WinScore));
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(greenNum), WinScore));
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(blueNum), WinScore));			
			} else {
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(redNum), LossScore));
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(greenNum), LossScore));
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(blueNum), LossScore));	
			}
		}
	}
}
