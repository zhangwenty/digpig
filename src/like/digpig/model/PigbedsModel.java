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
public class PigbedsModel {
	private PigBed[][] beds;
	private int column;
	private int row;
	private int pigPosition[];
	
	public PigbedsModel(int column, int row, int pigSize) {
		this.column = column;
		this.row = row;
		beds = new PigBed[column][row];
		for(int i=0; i<column; i++) {
			beds[i] = new PigBed[row];
			for(int j=0; j<row; j++) {
				beds[i][j] = new PigBed();
				beds[i][j].setX(i);
				beds[i][j].setY(j);
			}
		}
		if(pigSize <= 0) {
			pigPosition = new int[column*row/8];
		}
		else {
			pigPosition = new int[pigSize];
		}
	}
	
	public int getPigSize() {
		return pigPosition.length;
	}
	private int rand(int lower, int upper) {
		if(lower == upper)return lower;
		double tmp = Math.random();
		tmp = lower + (upper-lower)*tmp;
		// get a random integer between lower and upper
		int result = (int)Math.round(tmp);
		// move result close to lower
//		if(result-lower > (upper-lower)/2)result = lower+(upper-result);
		return result;
	}
	private void initPigPos() {
		int upper = column*row-1;
		int tmp = 0;
		for(int i=0; i<pigPosition.length; i++) {
			boolean repeat = true;
			while(repeat) {
				tmp = rand(0, upper);
				repeat = false;
				for(int j=0; j<i; j++) {
					if(tmp == pigPosition[j]) {
						repeat = true;
						break;
					}
				}
			}
			pigPosition[i] = tmp;
		}
	}
	public void initModel() {
		initPigPos();
		int x, y;
		for(int i=0; i<column; i++) {
			for(int j=0; j<row; j++) {
				beds[i][j].reset();
			}
		}
		for(int i=0; i<pigPosition.length; i++) {
			y = pigPosition[i]/column;
			x = pigPosition[i] - y*column;
			beds[x][y].setHasPig(true);
			beds[x][y].setState(PigBedState.PIG_HIDE);
		}
	}
	public PigBed getPigBed(int x, int y) {
		if(x<0 || x>=column || y<0 || y>=row)return null;
		return beds[x][y];
	}
	public int calculate(PigBed center) {
		int result = 0;
		for(int xInc=-1; xInc<=1; xInc++) {
			for(int yInc=-1; yInc<=1; yInc++) {
				PigBed tmp = this.getPigBed(center.getX()+xInc, 
						center.getY()+yInc);
				if(tmp != null && tmp != center) {
					if(tmp.isHasPig()) {
						result ++;
					}
				}
			}
		}
		center.setNeighbours(result);
		return result;
	}
}
