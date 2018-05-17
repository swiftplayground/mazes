package de.amr.easy.maze.alg.mst;

import de.amr.easy.graph.alg.traversal.BreadthFirstTraversal;
import de.amr.easy.graph.api.TraversalState;
import de.amr.easy.grid.api.Grid2D;

/**
 * A (naive?) implementation of the Reverse-Delete-MST algorithm.
 * 
 * @author Armin Reichert
 *
 * @see <a href="https://en.wikipedia.org/wiki/Reverse-delete_algorithm">Wikipedia</a>
 */
public class ReverseDeleteBFSMST extends ReverseDeleteMST {

	public ReverseDeleteBFSMST(Grid2D<TraversalState, Integer> grid) {
		super(grid);
	}

	@Override
	protected boolean disconnected(int u, int v) {
		BreadthFirstTraversal bfs = new BreadthFirstTraversal(grid, u);
		bfs.setStopAt(v);
		bfs.traverseGraph();
		return bfs.getDistance(v) == -1;
	}
}