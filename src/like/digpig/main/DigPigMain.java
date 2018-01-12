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
package like.digpig.main;

import like.digpig.ui.DigPigMainWin;
import like.digpig.ui.SettingDlg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author danny
 *
 */
public class DigPigMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean exit = false;
		while (!exit) {
			SettingDlg dlg = new SettingDlg(Display.getDefault());
			if(dlg.open() == SWT.OK) {
				DigPigMainWin win = new DigPigMainWin(Display.getDefault(),dlg.getColumn(),dlg.getRow(),dlg.getSize());
				if(win.open() == SWT.CANCEL)exit = true;
			}
			else exit = true;
		}
		System.exit(0);
	}

}
