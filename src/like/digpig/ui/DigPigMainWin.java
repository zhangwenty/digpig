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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import like.digpig.images.ImageProvider;
import like.digpig.model.PigBed;
import like.digpig.model.PigBedState;
import like.digpig.model.PigbedsModel;
import like.digpig.util.TextConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * @author danny
 *
 */
public class DigPigMainWin {
	public static final int RESTART = 8888;
	
	private Shell shell;
	private int returnCode = SWT.CANCEL;
	private int column, row;
	private PigbedsModel model;
	private Button initBtn;
	private HashMap<PigBed, Button> bed2btn;
	private TriFigureCompsite numOfPigs;
	private TriFigureCompsite timePassed;
	private Thread timeTrd;
	protected static int CUBE_WIDTH = 28;
	
	public DigPigMainWin(Display display, int column, int row, int size) {
		shell = new Shell(display, SWT.DIALOG_TRIM | SWT.MIN);
		if(column > 20)column = 20;
		if(column < 5)column = 5;
		if(row > 20)row = 20;
		if(row <5)row = 5;
		this.column = column;
		this.row = row;
		int width = CUBE_WIDTH*column+40;
		int height = CUBE_WIDTH*row+100;
		shell.setSize(width, height);
		int x = (display.getBounds().width - width)/2;
		int y = (display.getBounds().height - height)/2;
		shell.setLocation(x, y);
		shell.setText(TextConstants.APP_TITLE);
		model = new PigbedsModel(column, row, size);
		bed2btn = new HashMap<PigBed, Button>();
	}
	
	public int open() {
		model.initModel();
		createControl(shell);
		createMenu(shell);
		shell.open();
		while (!shell.isDisposed())
			while (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		if(!timeTrd.isInterrupted())timeTrd.interrupt();
		return returnCode;
	}
	
	public void createControl(Shell shell) {
		shell.setLayout(new GridLayout());
		Composite topComp = new Composite(shell, SWT.None);
		topComp.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL));
		initTopComp(topComp);
		
		Composite pigComp = new Composite(shell, SWT.None);
		pigComp.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL
				| GridData.GRAB_VERTICAL
				| GridData.FILL_VERTICAL));
		initPigComp(pigComp);
	}
	
	public void createMenu(final Shell shell) {
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem gameMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		gameMenuHeader.setText(TextConstants.GAME_MENU);
		Menu gameMenu = new Menu(shell, SWT.DROP_DOWN);
		gameMenuHeader.setMenu(gameMenu);
		
		MenuItem gameNewItem = new MenuItem(gameMenu, SWT.PUSH);
		gameNewItem.setText(TextConstants.NEW_MENUITEM);
		gameNewItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		MenuItem gameDefItem = new MenuItem(gameMenu, SWT.PUSH);
		gameDefItem.setText(TextConstants.GAMEDEF_MENUITEM);
		gameDefItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				returnCode = RESTART;
				shell.dispose();
			}
		});
		MenuItem exitItem = new MenuItem(gameMenu, SWT.PUSH);
		exitItem.setText(TextConstants.EXIT_MENUITEM);
		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				returnCode = SWT.CANCEL;
				shell.dispose();
			}
		});
		
		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuHeader.setText(TextConstants.HELP_MENU);
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		helpMenuHeader.setMenu(helpMenu);
		
		MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpItem.setText(TextConstants.HELP_MENUITEM);
		helpItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Runtime.getRuntime().exec("notepad help.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText(TextConstants.ABOUT_MENUITEM);
		aboutItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AboutDlg about = new AboutDlg(shell);
				about.open();
			}
		});
		
		shell.setMenuBar(menuBar);
	}

	private void initTopComp(Composite comp) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		comp.setLayout(layout);
		numOfPigs = new TriFigureCompsite(comp, SWT.None);
		numOfPigs.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL|
				GridData.FILL_HORIZONTAL));
		numOfPigs.setNumber(model.getPigSize());
		numOfPigs.setToolTipText(TextConstants.PIGNUMBEE_TIP);
		
		initBtn = new Button(comp, SWT.None);
		initBtn.setToolTipText(TextConstants.INITBTN_TIP);
		initBtn.setImage(ImageProvider.getImage(ImageProvider.SMILE_IMAGE));
		initBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		
		timePassed = new TriFigureCompsite(comp, SWT.None);
		timePassed.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL|
				GridData.FILL_HORIZONTAL));
		startTimer();
	}
	
	private void initPigComp(Composite comp) {
		GridLayout layout = new GridLayout();
		layout.numColumns = column;
		layout.verticalSpacing = 2;
		layout.horizontalSpacing = 2;
		comp.setLayout(layout);
		
		for(int j=0; j<row; j++) {
			for(int i=0; i<column; i++) {
				Composite base = new Composite(comp, SWT.None);
				base.setLayout(new FillLayout());
				base.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL|
						GridData.FILL_HORIZONTAL |
						GridData.GRAB_VERTICAL |
						GridData.FILL_VERTICAL));
				final Button btn = new Button(base, SWT.PUSH);
				btn.setToolTipText(TextConstants.PIGBED_TIP);
				PigbedMouseListener listener = new PigbedMouseListener(btn, initBtn, this);
				PigBed bed = model.getPigBed(i, j);
				listener.setBed(bed);
				btn.addMouseListener(listener);
				bed2btn.put(bed, btn);
			}
		}
	}
	protected void startTimer() {
		timePassed.setNumber(0);
		timeTrd = new Thread(new Runnable() {
			public void run() {
				while (!Thread.interrupted()) {
					try {
						Thread.sleep(1000);
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								timePassed.increase();
								if(timePassed.getNumber()==999) {
									timeOut();
								}
							}
						});
					} catch (InterruptedException e) {
//						e.printStackTrace();
						System.out.println("time thread exited");
						return;
					}
				}
			}
		});
		timeTrd.start();
	}
	public void check(PigBed center) {
		if(PigBedState.NUMBERED == center.getState())return;
		center.setState(PigBedState.NUMBERED);
		Button btn = bed2btn.get(center);
		if(model.calculate(center)>0) {
			if(btn != null) {
				btn.setImage(ImageProvider.getImage(center.getNeighbours()+".gif"));
			}
		}
		else {
			btn.setVisible(false);
			for(int xInc=-1; xInc<=1; xInc++) {
				for(int yInc=-1; yInc<=1; yInc++) {
					PigBed tmp = model.getPigBed(center.getX()+xInc, 
							center.getY()+yInc);
					if(tmp != null && tmp != center) {
						if(tmp.getNeighbours() == -1) {
							check(tmp);
						}
					}
				}
			}
		}
		checkResult();
	}
	public void mark(PigBed bed) {
		Button btn = bed2btn.get(bed);
		if(PigBedState.BLANK == bed.getState() ||
				PigBedState.PIG_HIDE == bed.getState()) {
			btn.setImage(ImageProvider.getImage(ImageProvider.FLAG12_IMAGE));
			bed.setState(PigBedState.PIG_MARKED);
			numOfPigs.decrease();
			checkResult();
		}
		else if(PigBedState.PIG_MARKED == bed.getState()) {
			btn.setImage(ImageProvider.getImage(ImageProvider.QUESTION_IMAGE));
			bed.setState(PigBedState.PIG_NOTSURE);
		}
		else if(PigBedState.PIG_NOTSURE == bed.getState()) {
			btn.setImage(ImageProvider.getImage(ImageProvider.BLANK_IMAGE));
			if(bed.isHasPig()) {
				bed.setState(PigBedState.PIG_HIDE);
			}
			else {
				bed.setState(PigBedState.BLANK);
			}
		}
	}
	public void timeOut() {
		freeze();
		MessageBox box = new MessageBox(shell, TextConstants.GAMEOVER_TITLE, 
				TextConstants.TIMEOUT_MSG, TextConstants.RETRY_SUBMSG, 1);
		if(box.open() == SWT.OK) {
			refresh();
		}
	}
	public void gameOver(Button host) {
		initBtn.setImage(ImageProvider.getImage(ImageProvider.CRY_IMAGE));
		Iterator<PigBed> it = bed2btn.keySet().iterator();
		for( ; it.hasNext(); ) {
			PigBed bed = it.next();
			if(bed.isHasPig()) {
				Button btn = bed2btn.get(bed);
				if(btn != null) {
					btn.setImage(ImageProvider.getImage(ImageProvider.PIGHEAD_IMAGE));
				}
			}
		}
		host.setImage(ImageProvider.getImage(ImageProvider.DIGGED_IMAGE));
		freeze();
		MessageBox box = new MessageBox(shell, TextConstants.GAMEOVER_TITLE, 
				TextConstants.RETRY_MSG, TextConstants.RETRY_SUBMSG, 1);
		if(box.open() == SWT.OK) {
			refresh();
		}
	}
	public void checkResult() {
		boolean win = true;
		Iterator<PigBed> it = bed2btn.keySet().iterator();
		for( ; it.hasNext(); ) {
			PigBed bed = it.next();
			if(bed.isHasPig()) {
				if(PigBedState.PIG_MARKED != bed.getState()) {
					win = false;
					break;
				}
			}
			else {
				if(PigBedState.NUMBERED != bed.getState()) {
					win = false;
					break;
				}
			}
		}
		if(win) {
			initBtn.setImage(ImageProvider.getImage(ImageProvider.WINNER_IMAGE));
			freeze();
			MessageBox box = new MessageBox(shell, TextConstants.WIN_TITLE, 
					TextConstants.WIN_MSG(timePassed.getNumber()), TextConstants.WIN_SUBMSG, 1);
			if(box.open() == SWT.OK) {
				refresh();
			}
		}
	}
	public void refresh() {
		model.initModel();
		numOfPigs.setNumber(model.getPigSize());
		initBtn.setImage(ImageProvider.getImage(ImageProvider.SMILE_IMAGE));
		Iterator<PigBed> it = bed2btn.keySet().iterator();
		for( ; it.hasNext(); ) {
			PigBed bed = it.next();
			Button btn = bed2btn.get(bed);
			if(btn != null) {
				btn.setVisible(true);
				btn.setImage(ImageProvider.getImage(ImageProvider.BLANK_IMAGE));
			}
		}
		timeTrd.interrupt();
		startTimer();
	}
	public void freeze() {
		timeTrd.interrupt();
		Iterator<PigBed> it = bed2btn.keySet().iterator();
		for( ; it.hasNext(); ) {
			PigBed bed = it.next();
			bed.setState(PigBedState.FROZEN);
		}
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
}
