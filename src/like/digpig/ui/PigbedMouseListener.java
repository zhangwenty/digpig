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
import like.digpig.model.PigBed;
import like.digpig.model.PigBedState;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;

/**
 * @author danny
 *
 */
public class PigbedMouseListener implements MouseListener {
	private Button initBtn;
	private Button host;
	private PigBed bed;
	private DigPigMainWin win;
	
	public PigbedMouseListener(Button host, Button initBtn, DigPigMainWin win) {
		this.initBtn = initBtn;
		this.host = host;
		this.win = win;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDown(MouseEvent arg0) {
		if(PigBedState.FROZEN == bed.getState())return;
		// left mouse button
		if(arg0.button == 1) {
			initBtn.setImage(ImageProvider.getImage(ImageProvider.SURPRISE_IMAGE));
		}
		// right mouse button
		else if(arg0.button == 3) {
			win.mark(bed);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseUp(MouseEvent arg0) {
		if(PigBedState.FROZEN == bed.getState())return;
		if(arg0.button == 1) {
			if(bed.isHasPig()) {
				win.gameOver(host);
			}
			else {
				initBtn.setImage(ImageProvider.getImage(ImageProvider.SMILE_IMAGE));
				win.check(bed);
			}
		}
	}
	
	/**
	 * @param bed the bed to set
	 */
	public void setBed(PigBed bed) {
		this.bed = bed;
//		if(bed.isHasPig()) {
//			host.setImage(ImageProvider.getImage(ImageProvider.PIGHEAD_IMAGE));
//		}
//		else {
			host.setImage(ImageProvider.getImage(ImageProvider.BLANK_IMAGE));
//		}
	}

}
