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

/**
 * @author danny
 *
 */
public class TextConstants {
	public static final String APP_TITLE = "救珂猪";
	public static final String SETTING_TITLE = "游戏设置";
	public static final String PRE_DEFINE = "预设：";
	public static final String LEVEL_LOW = "初级";
	public static final String LEVEL_MID = "中级";
	public static final String LEVEL_HIGH = "高级";
	public static final String USER_DEFINE = "自定义：";
	public static final String COLUMNS = "宽度：";
	public static final String ROWS = "高度：";
	public static final String PIGSIZE = "珂猪数：";
	public static final String ERROR_TITLE = "错误";
	public static final String BLANK_MSG = "宽度、高度或数量不能为空";
	public static final String FORMAT_ERROR_MSG = "数字格式错误";
	public static final String OVERFLOW_MSG = "宽度或高度只能在5~20之间";
	public static final String PIGSIZE_OVERFLOW_MSG = "珂猪的数目不能小于等于0，不能大于等于宽度*高度";
	
	public static final String BUTTON_OK = "    确定    ";
	public static final String BUTTON_CANCEL = "   取消   ";
	public static final String INITBTN_TIP = "重新开始";
	public static final String PIGBED_TIP = "单击左键挖一下，单击右键做标记";
	public static final String PIGNUMBEE_TIP = "未标记的珂猪数量";
	public static final String GAMEOVER_TITLE = "游戏结束";
	public static final String RETRY_MSG = "哇，伤到珂猪了，你挂了。重新开始吗？";
	public static final String TIMEOUT_MSG = "时间到，你没救出所有珂猪，你挂了。重新开始吗？";
	public static final String RETRY_SUBMSG = "单击\"确定\"重新开始，单击\"取消\"返回查看结果。";
	public static final String WIN_TITLE = "游戏获胜";
	public static final String WIN_MSG = "恭喜你，你救出了所有的珂猪。重新开始吗？";
	public static final String WIN_MSG(int time) {
		return "恭喜你，你救出了所有的珂猪，你所用的时间是 "+time+" 秒。\n重新开始吗？";
	}
	public static final String WIN_SUBMSG = "单击\"确定\"重新开始，单击\"取消\"返回查看结果。";
	
	public static final String GAME_MENU = "游戏(&G)";
	public static final String HELP_MENU = "帮助(&H)";
	public static final String NEW_MENUITEM = "开局(&N)";
	public static final String GAMEDEF_MENUITEM = "自定义(&C)";
	public static final String EXIT_MENUITEM = "退出(&X)";
	public static final String HELP_MENUITEM = "使用帮助(&H)";
	public static final String ABOUT_MENUITEM = "关于(&A)";
	public static final String ABOUT_TITLE = "关于救珂猪";
	public static final String ABOUT_TEXT = "救珂猪v1.0\n\nNicole and Danny 版权所有(C)2008~FOREVER\nby Danny (kikilikecoco@163.com)";
}
