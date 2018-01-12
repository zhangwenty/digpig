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
package like.digpig.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @author zl
 *
 */
public class FontFactory {
	private static Font FONT_ITALICS;
	private static Font FONT_BOLD;
	private static Font FONT_BIG_BOLD;
	private static Font FONT_BOLD_ITALICS;
	
	static {
		FONT_ITALICS = new Font(Display.getDefault(), "Arial", 9, SWT.ITALIC);
		FONT_BIG_BOLD = new Font(Display.getDefault(), "Arial", 10, SWT.BOLD);
		FONT_BOLD = new Font(Display.getDefault(), "Arial", 9, SWT.BOLD);
		FONT_BOLD_ITALICS = new Font(Display.getDefault(), "Arial", 9, SWT.BOLD|SWT.ITALIC);
	}
	
	public static Font getItalicsFont() {
		return FONT_ITALICS;
	}
	
	public static Font getBigBoldFont() {
		return FONT_BIG_BOLD;
	}
	
	public static Font getBoldFont() {
		return FONT_BOLD;
	}
	
	public static Font getBoldItalicsFont() {
		return FONT_BOLD_ITALICS;
	}
}
