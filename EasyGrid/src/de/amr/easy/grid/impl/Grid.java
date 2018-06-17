package de.amr.easy.grid.impl;

import java.util.function.BiFunction;

import de.amr.easy.graph.api.Edge;
import de.amr.easy.graph.api.Vertex;
import de.amr.easy.graph.impl.DenseSymbolTable;
import de.amr.easy.graph.impl.SparseSymbolTable;
import de.amr.easy.grid.api.Grid2D;
import de.amr.easy.grid.api.Topology;

/**
 * A 2D-grid graph with vertex objects.
 * 
 * @author Armin Reichert
 * 
 * @param V
 *          vertex type
 * @param E
 *          edge type
 */
public class Grid<V, E extends Edge> extends GridGraph<E> implements Grid2D<V, E> {

	private final Vertex<V> vertexTable;

	/**
	 * Creates a grid with the given properties.
	 * 
	 * @param numCols
	 *          the number of columns ("width")
	 * @param numRows
	 *          the number of rows ("height")
	 * @param top
	 *          the topology of the grid
	 * @param defaultVertex
	 *          the default vertex
	 * @param sparse
	 *          if the grid has sparse content
	 */
	public Grid(int numCols, int numRows, Topology top, V defaultVertex, boolean sparse,
			BiFunction<Integer, Integer, E> fnEdgeFactory) {
		super(numCols, numRows, top, fnEdgeFactory);
		vertexTable = sparse ? new SparseSymbolTable<>() : new DenseSymbolTable<>(numCols * numRows);
		vertexTable.setDefaultVertex(defaultVertex);
	}

	// --- {@link Vertex} interface ---

	@Override
	public void clearVertexObjects() {
		vertexTable.clearVertexObjects();
	}

	@Override
	public V getDefaultVertex() {
		return vertexTable.getDefaultVertex();
	}

	@Override
	public void setDefaultVertex(V vertex) {
		vertexTable.setDefaultVertex(vertex);
	}

	@Override
	public V get(int v) {
		return vertexTable.get(v);
	}

	@Override
	public void set(int v, V vertex) {
		vertexTable.set(v, vertex);
	}

	@Override
	public boolean isSparse() {
		return vertexTable.isSparse();
	}
}