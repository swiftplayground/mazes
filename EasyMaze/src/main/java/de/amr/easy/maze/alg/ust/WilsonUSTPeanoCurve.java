package de.amr.easy.maze.alg.ust;

import static de.amr.easy.graph.api.traversal.TraversalState.UNVISITED;
import static de.amr.easy.grid.api.GridPosition.BOTTOM_LEFT;
import static de.amr.easy.grid.impl.OrthogonalGrid.emptyGrid;
import static de.amr.easy.util.GraphUtils.log;
import static de.amr.easy.util.GraphUtils.nextPow;
import static java.lang.Math.max;
import static java.util.Arrays.stream;

import java.util.stream.IntStream;

import de.amr.easy.grid.impl.OrthogonalGrid;
import de.amr.easy.grid.impl.curves.PeanoCurve;

/**
 * Wilson's algorithm where the random walks start in the order defined by a Peano curve.
 * 
 * @author Armin Reichert
 */
public class WilsonUSTPeanoCurve extends WilsonUST {

	public WilsonUSTPeanoCurve(int numCols, int numRows) {
		super(numCols, numRows);
	}

	private int i;

	@Override
	protected IntStream randomWalkStartCells() {
		int[] walkStartCells = new int[grid.numVertices()];
		int n = nextPow(3, max(grid.numCols(), grid.numRows()));
		OrthogonalGrid square = emptyGrid(n, n, UNVISITED);
		PeanoCurve peano = new PeanoCurve(log(3, n));
		int current = square.cell(BOTTOM_LEFT);
		addCell(walkStartCells, square.col(current), square.row(current));
		for (int dir : peano) {
			current = square.neighbor(current, dir).getAsInt();
			addCell(walkStartCells, square.col(current), square.row(current));
		}
		return stream(walkStartCells);
	}

	private void addCell(int[] walkStartCells, int col, int row) {
		if (grid.isValidCol(col) && grid.isValidRow(row)) {
			walkStartCells[i++] = grid.cell(col, row);
		}
	}
}