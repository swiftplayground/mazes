package de.amr.easy.maze.alg.wilson;

import static de.amr.easy.grid.api.GridPosition.BOTTOM_RIGHT;

import java.util.stream.Stream;

import de.amr.easy.graph.api.TraversalState;
import de.amr.easy.grid.api.Dir4;
import de.amr.easy.grid.api.Grid2D;
import de.amr.easy.grid.iterators.traversals.RightToLeftSweep;

/**
 * Wilson's algorithm where the vertices are selected column-wise left-to-right.
 * 
 * @author Armin Reichert
 */
public class WilsonUSTRightToLeftSweep extends WilsonUST {

	public WilsonUSTRightToLeftSweep(Grid2D<Dir4,TraversalState, Integer> grid) {
		super(grid);
	}

	@Override
	protected Stream<Integer> cellStream() {
		return new RightToLeftSweep(grid).stream();
	}

	@Override
	protected Integer customStartCell(Integer start) {
		return grid.cell(BOTTOM_RIGHT);
	}
}