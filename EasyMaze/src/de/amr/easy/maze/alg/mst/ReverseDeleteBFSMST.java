package de.amr.easy.maze.alg.mst;

import de.amr.easy.graph.api.SimpleEdge;
import de.amr.easy.graph.api.traversal.TraversalState;
import de.amr.easy.graph.impl.traversal.BreadthFirstTraversal;
import de.amr.easy.grid.api.Grid2D;

/**
 * A (naive?) implementation of the Reverse-Delete-MST algorithm.
 * 
 * @author Armin Reichert
 *
 * @see <a href="https://en.wikipedia.org/wiki/Reverse-delete_algorithm">Wikipedia</a>
 */
public class ReverseDeleteBFSMST extends ReverseDeleteMST {

	public ReverseDeleteBFSMST(Grid2D<TraversalState, SimpleEdge> grid) {
		super(grid);
	}

	@Override
	protected boolean connected(int u, int v) {
		BreadthFirstTraversal bfs = new BreadthFirstTraversal(grid);
		bfs.traverseGraph(u, v);
		return bfs.getParent(v) != -1;
	}
}