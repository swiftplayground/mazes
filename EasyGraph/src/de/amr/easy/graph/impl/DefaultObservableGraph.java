package de.amr.easy.graph.impl;

import java.util.HashSet;
import java.util.Set;

import de.amr.easy.graph.api.ObservableGraph;
import de.amr.easy.graph.api.SimpleEdge;
import de.amr.easy.graph.api.event.EdgeChangeEvent;
import de.amr.easy.graph.api.event.GraphObserver;
import de.amr.easy.graph.api.event.VertexChangeEvent;

/**
 * Adjacency set based implementation of an undirected, observable graph.
 * 
 * @author Armin Reichert
 * 
 * @param <E>
 *          edge type
 */
public class DefaultObservableGraph extends DefaultGraph implements ObservableGraph<SimpleEdge> {

	private Set<GraphObserver<SimpleEdge>> listeners = new HashSet<>();
	private boolean listeningSuspended = false;

	public DefaultObservableGraph() {
		this.listeningSuspended = false;
	}

	@Override
	public void addGraphObserver(GraphObserver<SimpleEdge> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeGraphObserver(GraphObserver<SimpleEdge> listener) {
		listeners.remove(listener);
	}

	@Override
	public void setEventsEnabled(boolean enabled) {
		listeningSuspended = enabled;
	}

	protected void fireVertexChange(int vertex, Object oldValue, Object newValue) {
		if (!listeningSuspended) {
			for (GraphObserver<SimpleEdge> listener : listeners) {
				listener.vertexChanged(new VertexChangeEvent(this, vertex, oldValue, newValue));
			}
		}
	}

	protected void fireEdgeChange(SimpleEdge edge, Object oldValue, Object newValue) {
		if (!listeningSuspended) {
			for (GraphObserver<SimpleEdge> listener : listeners) {
				listener.edgeChanged(new EdgeChangeEvent<>(this, edge, oldValue, newValue));
			}
		}
	}

	protected void fireGraphChange(ObservableGraph<SimpleEdge> graph) {
		if (!listeningSuspended) {
			for (GraphObserver<SimpleEdge> listener : listeners) {
				listener.graphChanged(graph);
			}
		}
	}

	@Override
	public void addVertex(int vertex) {
		super.addVertex(vertex);
		fireVertexChange(vertex, null, vertex);
	}

	@Override
	public void addEdge(int v, int w) {
		super.addEdge(v, w);
		edge(v, w).ifPresent(edge -> fireEdgeChange(edge, null, edge));
	}

	@Override
	public void removeEdge(int v, int w) {
		edge(v, w).ifPresent(edge -> {
			super.removeEdge(v, w);
			fireEdgeChange(edge, edge, null);
		});
	}

	@Override
	public void removeEdges() {
		super.removeEdges();
		fireGraphChange(this);
	}
}