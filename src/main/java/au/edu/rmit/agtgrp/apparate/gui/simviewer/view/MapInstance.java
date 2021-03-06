package au.edu.rmit.agtgrp.apparate.gui.simviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import au.edu.rmit.agtgrp.apparate.gui.simviewer.controller.Launcher;
import au.edu.rmit.agtgrp.apparate.gui.simviewer.model.timer.ThreadedTimer;
import au.edu.rmit.agtgrp.apparate.gui.simviewer.view.MapGrid.LayerType;

/**
 * The interface for a single model
 * Holds all users preference for this map
 * Also defines the appearance of the map
 * 
 * @author Andy Xie
 *
 */
public class MapInstance extends Panel {
	
	private static final long serialVersionUID = 7710958056048092418L;

	public static final int MAXZOOM = 16;
	
	private static final Color COLOR_START = new Color(255, 0, 0); // Start position colour
	private static final Color COLOR_GOAL = new Color(0, 255, 255); // Goal position colour
	
	private static final Color COLOR_PLANPATH = new Color(255, 255, 0, 127); // Planned path colour
	private static final Color COLOR_GOALPATH = new Color(127, 127, 0, 127); // Goal travelled path colour
	private static final Color COLOR_STARTPATH = new Color(255, 102, 0, 127); // Start travelled path colour
	
	private static final Color COLOR_OPENED = new Color(0, 102, 0, 63); // Open nodes colour
	private static final Color COLOR_CLOSED = new Color(102, 51, 0, 63); // Closed nodes colour
	
	private static final Color COLOR_BLOCKED = Color.GRAY; // Intraversable terrain colour
	private static final Color COLOR_TRAVERSABLE = Color.LIGHT_GRAY; // Traversable terrain colour

	private static final Color COLOR_SELECTION = new Color(255, 255, 255, 63); // mouse selection colour
	
	private final MapGrid map; // the shown map
	public final ScrollPane gridscroll; // the scroll pane which the map lives
	private final JSlider zoomslider; // the slider the alter the zoom level
	
	public final JLabel runstate, mousestate, zoomstate; // the running state, the mouse state and the zoom level
	
	
	
	/* *******************
	 * Constructor
	 *********************/
	
	/**
	 * 
	 * @param width the width of the map to create
	 * @param height the height of the map to create
	 * @param pixelsize the pixel size
	 */
	public MapInstance(int width, int height, int pixelsize) {
		JPanel pane;
		map = new MapGrid(width, height);
		gridscroll = new ScrollPane();
		gridscroll.add(map);
		this.setLayout(new BorderLayout());
		this.add(gridscroll, BorderLayout.CENTER);

		// create the zoom slider with the power to zoom
		this.zoomstate = new JLabel();
		zoomslider = new JSlider(1, MAXZOOM);
		zoomslider.addChangeListener(new ChangeListener() {
			private final MapGrid curmap = map;
			private final ScrollPane curscroll = gridscroll;
			private final JLabel zoomdisp = zoomstate;
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider curslider = (JSlider) arg0.getSource();
				curmap.setPixelSize(curslider.getValue());
				zoomdisp.setText("    " + curslider.getValue() + "x");
				curscroll.repaint();
			}
			
		});
		zoomslider.setValue(pixelsize);
		zoomslider.setSnapToTicks(true);
		
		// fields for setting time
		final JTextField timeField = new JTextField("",5);
		JButton setTimeBtn = new JButton("Reset Time");
		setTimeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
				int newTime = Integer.parseInt(timeField.getText());
				setMaxTime(newTime);
				} catch(Exception e){
					System.err.println("Please enter an integer");
				}
			}
		});
		
		pane = new JPanel();
		JPanel zoompane = new JPanel();
		pane.setLayout(new BorderLayout());
		this.runstate = new JLabel();
		this.mousestate = new JLabel();
		mousestate.setHorizontalTextPosition(JLabel.RIGHT);
		mousestate.setHorizontalAlignment(JLabel.RIGHT);
		this.zoomstate.setMinimumSize(new Dimension(20, 1));

		pane.add(runstate, BorderLayout.WEST);
		pane.add(mousestate, BorderLayout.CENTER);
		zoompane.add(zoomstate);
		zoompane.add(zoomslider);
		
		JPanel sidePane = new JPanel();
		sidePane.setLayout(new BorderLayout());
		sidePane.add(timeField,BorderLayout.WEST);
		sidePane.add(setTimeBtn,BorderLayout.CENTER);
		sidePane.add(zoompane, BorderLayout.EAST);
		pane.add(sidePane,BorderLayout.EAST);
		this.add(pane, BorderLayout.SOUTH);
		
		zoomslider.setFocusable(false);
		mousestate.setFocusable(false);
		runstate.setFocusable(false);
		zoomstate.setFocusable(false);
		map.requestFocus();
		
	}
	/**
	 * Set the max time
	 *
	 * @param maxtime the maximum time allowed
	 */
	public void setMaxTime(int maxtime){
		try {
			(new ThreadedTimer(Launcher.getRunEnviron().getTimer())).Pause();
			
			Launcher.getRunEnviron().setMaxTime(maxtime);
		} catch (Exception e) { }
		 
	}

	/* *******************
	 * Accessor
	 *********************/
	
	public Dimension2D getGridViewPortSize() {
		return gridscroll.getViewportSize();
	}
	
	public Point getGridViewScrollPosition() {
		return gridscroll.getScrollPosition();
	}
	
	public int getGridWidth() {
		return map.getGridWidth();
	}
	
	public int getGridHeight() {
		return map.getGridHeight();
	}
	
	public int getPixelSize() {
		return map.getPixelSize();
	}
	
	public boolean getLayerVisibility(LayerType layer) {
		return map.getLayer(layer).getEnabled();
	}
	
	/* *******************
	 * Mutator
	 *********************/
	
	public void setGridViewScrollPosition(int x, int y) {
		gridscroll.setScrollPosition(x, y);
	}
	
	public void setPixelSize(int pixelsize) {
		int thispixelsize = pixelsize;
		if (thispixelsize < 1) thispixelsize = 1;
		if (thispixelsize > MAXZOOM) thispixelsize = MAXZOOM;
		if (thispixelsize != map.getPixelSize()) {
			map.setPixelSize(pixelsize);
			zoomslider.setValue(map.getPixelSize());
		}
		
	}
	
	public void setMouseState(String state) {
		this.mousestate.setText(state);
	}

	public void setRunState(String currentState, int currentStep, long nanoTimeSoFar, double costSoFar, int currentPulse, int maxSteps, int maxTimeMs) {
		String message = 
				"Step: " + currentStep + 
				"/" + maxSteps +  
				" | Time: " + nanoTimeSoFar + 
				"ms/" + maxTimeMs + "ms" + 
				" | Time Left: " + (maxTimeMs-nanoTimeSoFar) + "ms" + 
				" | Cost: " + ( Math.round(costSoFar*100.0)/100.0 ) + 
				" | " + currentState.toString();
		
		
		this.runstate.setText(message);
	}
	
	public void setRunState(String currentState) {
		this.runstate.setText(currentState);
	}

	
	/**
	 * Add a mouse listener to the canvas map within
     *
	 * @param listener the linserner to register
	 */
	public void AddGridMouseListener(MouseListener listener) {
		map.addMouseListener(listener);
	}
	
	public void AddGridMotionListener(MouseMotionListener listener) {
		map.addMouseMotionListener(listener);
	}
	
	public void AddGridKeyListener(KeyListener listener) {
		map.addKeyListener(listener);
	}
	
	/**
	 * Force the map to redraw
	 */
	public void updateShownMap() {
		map.updateShownMap();
	}
	
	/**
	 * change a single coordinate's colour
	 * @param x         x value of coordinate to change
	 * @param y         y value of coordinate to change
	 * @param colour    the colour to set in that coordinate
	 */
	public void updateMapColor(int x, int y, Color colour) {
		MapLayer layer = map.getLayer(LayerType.LAYER_MAP);
		synchronized (layer) {
			layer.setGrid(x, y, colour);
		}
	}
	
	/**
	 * Change the entire map's colour
     *
	 * @param colours colors to change the map into
	 */
	public void updateMapColor(Color[][] colours) {
		MapLayer layer = map.getLayer(LayerType.LAYER_MAP);
		synchronized (layer) {
			layer.clearGrid();
			layer.setGrid(colours);
		}
	}
	
	/**
	 * Change a single coordinate if it is traversable
     *
	 * @param x         x value of coordinate
	 * @param y         y value of coordinate
	 * @param blocked   whether (x,y) has to be set as "blocked" (otherwise set as traversable)
	 */
	public void updateMapTraversability(int x, int y, boolean blocked) {
		MapLayer layer = map.getLayer(LayerType.LAYER_TRAVERSABLEMAP);
		synchronized (layer) {
			layer.setGrid(x, y, (blocked ? COLOR_BLOCKED : COLOR_TRAVERSABLE));
		}
	}
	
	/**
	 * Change the entire map of where is it traversable
     *
	 * @param blocked an array stating which cells are blocked and which are not
	 */
	public void updateMapTraversability(boolean[][] blocked) {
		int width = getGridWidth(), height = getGridHeight();
		Color[][] blockedmap = new Color[width][height];
		MapLayer layer = map.getLayer(LayerType.LAYER_TRAVERSABLEMAP);
		for (int i = 0; i < width && i < blocked.length; i++) {
			for (int j = 0; j < height && j < blocked[i].length; j++) blockedmap[i][j] = (blocked[i][j] ? COLOR_BLOCKED : COLOR_TRAVERSABLE);
			for (int j = blocked[i].length; j < height; j++) blockedmap[i][j] = COLOR_BLOCKED;
		}
		for (int i = blocked.length; i < width; i++) for (int j = 0; j < height; j++) blockedmap[i][j] = COLOR_BLOCKED;
		synchronized (layer) {
			layer.clearGrid();
			layer.setGrid(blockedmap);
		}
	}
	
	/**
	 * Set the starting position
	 * Also update the path of the starting position
     *
     * @param x the x value of the coordinate
     * @param y the y value of the coordinate
	 */
	public void setStartNode(int x, int y) {
		MapLayer layer = map.getLayer(LayerType.LAYER_START);
		synchronized (layer)  {
			Color highlightcolor = new Color(COLOR_START.getRed(), COLOR_START.getGreen(), COLOR_START.getBlue(), COLOR_START.getAlpha() / 4);
			layer.clearGrid();
			layer.setGrid(new MapEntity(x - 4, y - 4, 9, 9, highlightcolor));
			layer.setGrid(new MapEntity(x - 2, y - 2, 5, 5, highlightcolor));
			layer.setGrid(x, y, COLOR_START);
			map.getLayer(LayerType.LAYER_TRAVELED).setGrid(x, y, COLOR_STARTPATH);
		}
	}
	
	/**
	 * Set the goal position
     * Also update the path of the goal position
     *
	 * @param x the x value of the goal location
	 * @param y the y value of the goal location
	 */
	public void setGoalNode(int x, int y) {
		MapLayer layer = map.getLayer(LayerType.LAYER_GOAL);
		synchronized (layer) {
			Color highlightcolor = new Color(COLOR_GOAL.getRed(), COLOR_GOAL.getGreen(), COLOR_GOAL.getBlue(), COLOR_GOAL.getAlpha() / 4);
			layer.clearGrid();
			layer.setGrid(new MapEntity(x - 4, y - 4, 9, 9, highlightcolor));
			layer.setGrid(new MapEntity(x - 2, y - 2, 5, 5, highlightcolor));
			layer.setGrid(x, y, COLOR_GOAL);
			map.getLayer(LayerType.LAYER_PLANPATH).setGrid(x, y, COLOR_GOALPATH);
		}
	}
	
	/**
	 * Erase old map of planned path and redraw with the new one
     *
	 * @param points new planned path (future path) to be redraw
	 */
	public void setPlanPathNode(List<MapCoord> points) {
		MapLayer layer = map.getLayer(LayerType.LAYER_PLANPATH);
		synchronized (layer) {
			layer.clearGrid();
			for (MapCoord point: points) layer.setGrid(point.x, point.y, COLOR_PLANPATH);
		}
	}
	
	/**
	 * Erase old map of start position traversed nodes and replace it with the new one
     *
	 * @param points the new path that has been traversed (past path)
	 */
	public void setTraveledNode(List<MapCoord> points) {
		MapLayer layer = map.getLayer(LayerType.LAYER_TRAVELED);
		synchronized (layer) {
			layer.clearGrid();
			for (MapCoord point: points) layer.setGrid(point.x, point.y, COLOR_STARTPATH);
		}
	}
	
	/**
	 * Erase the old map of goal position traversed nodes and replace it with the new one
     *
	 * @param points the new goal position traversed
	 */
	public void setGoalTraveledNode(List<MapCoord> points) {
		MapLayer layer = map.getLayer(LayerType.LAYER_GOALPATH);
		synchronized (layer) {
			layer.clearGrid();
			for (MapCoord point: points) layer.setGrid(point.x, point.y, COLOR_GOALPATH);
		}
	}
	
	/**
	 * Erase the old map of all opened nodes and replace it with the new list
     *
	 * @param points the new set of open nodes
	 */
	public void setOpenedNode(List<MapCoord> points) {
		MapLayer layer = map.getLayer(LayerType.LAYER_OPENED);
		synchronized (layer) {
			layer.clearGrid();
			for (MapCoord point: points) layer.setGrid(point.x, point.y, COLOR_OPENED);
		}
	}
	
	/**
	 * Erase the old map of all closed nodes and replace it with the new list
     *
	 * @param points the new set of closed nodes
	 */
	public void setClosedNode(List<MapCoord> points) {
		MapLayer layer = map.getLayer(LayerType.LAYER_CLOSED);
		synchronized (layer) {
			layer.clearGrid();
			for (MapCoord point: points) layer.setGrid(point.x, point.y, COLOR_CLOSED);
		}
	}
	
	public void showSelection(MapCoord clickloc, MapCoord releaseloc) {
		MapLayer layer = map.getLayer(LayerType.LAYER_SELECTION);
		int width = Math.abs(releaseloc.getX() - clickloc.getX()) + 1;
		int height =  Math.abs(releaseloc.getY() - clickloc.getY()) + 1;
		int left = releaseloc.getX(), top = releaseloc.getY();
		if (left > clickloc.getX()) left = clickloc.getX();
		if (top > clickloc.getY()) top = clickloc.getY();
		synchronized (layer) {
			layer.clearGrid();
			layer.setGrid(new MapEntity(left, top, width, height, COLOR_SELECTION));
		}
	}
	
	public void clearSelection() {
		MapLayer layer = map.getLayer(LayerType.LAYER_SELECTION);
		synchronized (layer) {
			layer.clearGrid();
		}
	}
	
	/**
	 * Show the traverable map as the background
	 */
	public void showTraverseMap() {
		map.getLayer(LayerType.LAYER_MAP).setEnabled(false);
		map.getLayer(LayerType.LAYER_TRAVERSABLEMAP).setEnabled(true);
	}
	
	/**
	 * Show the terrain map as the background
	 */
	public void showTerrianMap() {
		map.getLayer(LayerType.LAYER_MAP).setEnabled(true);
		map.getLayer(LayerType.LAYER_TRAVERSABLEMAP).setEnabled(false);
	}
	
	public void setLayerVisibility(LayerType layer, boolean enabled) {
		map.getLayer(layer).setEnabled(enabled);
		map.updateShownMap();
	}
}
