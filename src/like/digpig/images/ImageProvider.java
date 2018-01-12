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
package like.digpig.images;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @author zl
 *
 */
public class ImageProvider {
	public static final String PIG_IMAGE = "pig.gif";
	public static final String SMILE_IMAGE = "smile.gif";
	public static final String CRY_IMAGE = "cry.gif";
	public static final String DIGGED_IMAGE = "digged.gif";
	public static final String PIGHEAD_IMAGE = "pighead.gif";
	public static final String BLANK_IMAGE = "blank.gif";
	public static final String FLAG12_IMAGE = "flag_12x12.gif";
	public static final String FLAG15_IMAGE = "flag_15x15.gif";
	public static final String QUESTION_IMAGE = "question.gif";
	public static final String SURPRISE_IMAGE = "surprise.gif";
	public static final String WINNER_IMAGE = "winner.gif";
	public static final String NUM0_IMAGE = "num0.gif";
	public static final String NUM1_IMAGE = "num1.gif";
	public static final String NUM2_IMAGE = "num2.gif";
	public static final String NUM3_IMAGE = "num3.gif";
	public static final String NUM4_IMAGE = "num4.gif";
	public static final String NUM5_IMAGE = "num5.gif";
	public static final String NUM6_IMAGE = "num6.gif";
	public static final String NUM7_IMAGE = "num7.gif";
	public static final String NUM8_IMAGE = "num8.gif";
	public static final String NUM9_IMAGE = "num9.gif";
	public static final String NUM_PREFIX = "num";
	
	private static final Map<String, Image> stringToImage = new HashMap<String, Image>();
	
	public static Image getImage(String key) {
		Image img = (Image)stringToImage.get(key);
		if (img == null) {
			img = getImageDescriptor(key).createImage();
			// cache the image
			stringToImage.put(key, img);
		}
		return img;
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		return ImageDescriptor.createFromFile(ImageProvider.class, key);
	}
}
