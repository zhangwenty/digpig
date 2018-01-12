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
public class PigBedState {
	public static PigBedState BLANK;
	public static PigBedState NUMBERED;
	public static PigBedState PIG_HIDE;
	public static PigBedState PIG_MARKED;
	public static PigBedState PIG_DIGGED;
	public static PigBedState PIG_NOTSURE;
	public static PigBedState FROZEN;
	
	static {
		BLANK = new PigBedState(0);
		NUMBERED = new PigBedState(1);
		PIG_HIDE = new PigBedState(2);
		PIG_MARKED = new PigBedState(3);
		PIG_DIGGED = new PigBedState(4);
		PIG_NOTSURE = new PigBedState(5);
		FROZEN = new PigBedState(6);
	}
	private int value;
	
	private PigBedState(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	public boolean equals(PigBedState ps) {
		if(ps == null)return false;
		return value == ps.getValue();
	}
}
