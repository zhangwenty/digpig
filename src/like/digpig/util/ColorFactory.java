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

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.graphics.Color;
/**
 * 
 * @author zl
 *
 */
public class ColorFactory {
	static private Hashtable<Integer,Color> colorTable;
	
	static {
		colorTable = new Hashtable<Integer,Color>();
		addColor(255,0,0);//red
		addColor(0,255,0);//green
		addColor(0,0,255);//blue
		addColor(0,0,0);//black
		addColor(255,255,255);//write
		addColor(128,128,128);//grey
	}
	static public void status(){
		System.out.println("total "+colorTable.size()+" color(s) are included");
	}
	static private Color addColor(int r,int g,int b){
		int key = (r<<16)|(g<<8)|b;
		Color c = new Color(null,r,g,b);
		colorTable.put(key, c);
		return c;
	}
	static public Color getColor(int r,int g,int b){
		if(r==-1||g==-1||b==-1) return null;
		//make sure the color is valid
		r=r&0xff;
		g=g&0xff;
		b=b&0xff;
		
		int key = (r<<16)|(g<<8)|b;
		Color c = colorTable.get(key);
		if(c==null){
			c = addColor(r,g,b);
		}
		return c;
	}
	static public void clearColors(){
		Enumeration<Color> e = colorTable.elements();
		while(e.hasMoreElements()){
			Color c = e.nextElement();
			c.dispose();
		}
		colorTable.clear();
	}
}
