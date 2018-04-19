package de.amr.easy.maze.alg.wilson;

import java.util.List;
import java.util.stream.Collectors;

import de.amr.easy.graph.api.TraversalState;
import de.amr.easy.grid.api.Grid2D;

/**
 * Wilson's algorithm with random vertex selection.
 * 
 * @author Armin Reichert
 */
public class WilsonUSTRandomCell extends WilsonUST {

	private final List<Integer> cellsOutsideTree;

	public WilsonUSTRandomCell(Grid2D<TraversalState, Integer> grid) {
		super(grid);
		cellsOutsideTree = grid.vertexStream().boxed().collect(Collectors.toList());
	}

	@Override
	public void run(int start) {
		addToTree(start);
		while (!cellsOutsideTree.isEmpty()) {
			loopErasedRandomWalk(cellsOutsideTree.get(rnd.nextInt(cellsOutsideTree.size())));
		}
	}

	@Override
	protected void addToTree(int v) {
		super.addToTree(v);
		cellsOutsideTree.remove((Object) v); // remove(int) is the wrong method!
	}
}