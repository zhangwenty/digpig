/**
 * Copyright (c) NICOLE & DANNY
 * All rights reserved. 
 *
 * NICOLE & DANNY (refers to as "ND" below) 
 * owns the copyright of this program and the accompanying materials, 
 * which is protected by Chinese Law. 
 * Any unauthorized (include but not limited) use, extract, 
 * distributing or modifying of the program and its accompanying 
 * materials without the prior written permission of ND are 
 * strictly prohibited. 
 * If you infringe upon ND's rights with respect to any 
 * ND Proprietary Property, you will be ordered to cease such 
 * illegal activity and you will be strictly liable to ND for 
 * any and all damages (including recovery of attorneys' fees) which 
 * may be suffered and/or incurred as a result of your infringement. 
 * 
 */
package like.digpig.model;

/**
 * @author danny
 *
 */
public class PigBed {
	private int x;
	private int y;
	private int neighbours;
	private boolean hasPig;
	private PigBedState state;
	
	public PigBed() {
		x = y = 0;
		hasPig = false;
		state = PigBedState.BLANK;
		neighbours = -1;
	}
	public void reset() {
		this.neighbours = -1;
		this.hasPig = false;
		this.state = PigBedState.BLANK;
	}
	/**
	 * @return the state
	 */
	public PigBedState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(PigBedState state) {
		this.state = state;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the hasPig
	 */
	public boolean isHasPig() {
		return hasPig;
	}
	/**
	 * @param hasPig the hasPig to set
	 */
	public void setHasPig(boolean hasPig) {
		this.hasPig = hasPig;
	}
	/**
	 * @return the neighbours
	 */
	public int getNeighbours() {
		return neighbours;
	}
	/**
	 * @param neighbours the neighbours to set
	 */
	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
}
