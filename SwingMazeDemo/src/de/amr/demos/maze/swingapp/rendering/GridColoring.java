package de.amr.demos.maze.swingapp.rendering;

import java.awt.Color;

import de.amr.demos.maze.swingapp.model.MazeDemoModel;
import de.amr.easy.grid.ui.swing.DefaultGridRenderingModel;

/**
 * Rendering model for grid taking settings from demo model.
 * 
 * @author Armin Reichert
 */
public class GridColoring extends DefaultGridRenderingModel {

	private static final Color VISITED_COLOR = Color.BLUE;

	private final MazeDemoModel model;

	public GridColoring(MazeDemoModel model) {
		this.model = model;
	}

	@Override
	public int getCellSize() {
		return model.getGridCellSize();
	}

	@Override
	public int getPassageWidth() {
		int thickness = model.getGridCellSize() * model.getPassageThicknessPct() / 100;
		if (thickness < 1) {
			thickness = 1;
		} else if (thickness > model.getGridCellSize() - 1) {
			thickness = model.getGridCellSize() - 1;
		}
		return thickness;
	}

	@Override
	public Color getCellBgColor(int cell) {
		switch (model.getGrid().get(cell)) {
		case COMPLETED:
			return super.getCellBgColor(cell);
		case UNVISITED:
			return getGridBgColor();
		case VISITED:
			return VISITED_COLOR;
		default:
			return getGridBgColor();
		}
	}
}
