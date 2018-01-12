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
package like.digpig.ui;

import like.digpig.images.ImageProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author danny
 *
 */
public class TriFigureCompsite extends Composite {
	protected Label first;
	protected Label second;
	protected Label third;
	protected int number;
	
	public TriFigureCompsite(Composite parent, int style) {
		super(parent, style);
		init();
	}
	protected void init() {
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		this.setLayout(layout);
		number = 0;
		
		first = new Label(this, SWT.None);
		first.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL |
				GridData.GRAB_VERTICAL | GridData.FILL_BOTH));
		first.setImage(ImageProvider.getImage(ImageProvider.NUM0_IMAGE));
		
		second = new Label(this, SWT.None);
		second.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL |
				GridData.GRAB_VERTICAL | GridData.FILL_BOTH));
		second.setImage(ImageProvider.getImage(ImageProvider.NUM0_IMAGE));
		
		third = new Label(this, SWT.None);
		third.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL |
				GridData.GRAB_VERTICAL | GridData.FILL_BOTH));
		third.setImage(ImageProvider.getImage(ImageProvider.NUM0_IMAGE));
	}
	
	public void setNumber(int number) {
		this.number = number;
		if(number > 999 || number < 0)return;
		int tmp1 = number / 100;
		int tmp2 = (number-tmp1*100)/10;
		int tmp3 = (number-tmp1*100-tmp2*10);
		
		first.setImage(ImageProvider.getImage(ImageProvider.NUM_PREFIX 
				+ tmp1 + ".gif"));
		second.setImage(ImageProvider.getImage(ImageProvider.NUM_PREFIX 
				+ tmp2 + ".gif"));
		third.setImage(ImageProvider.getImage(ImageProvider.NUM_PREFIX 
				+ tmp3 + ".gif"));
	}
	public void decrease() {
		number --;
		setNumber(number);
	}
	public void increase() {
		number ++;
		setNumber(number);
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
}
