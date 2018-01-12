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

import like.digpig.util.TextConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author danny
 *
 */
public class SettingDlg {
	protected Shell shell;
	protected int returnCode;
	protected int column, row, size;
	protected Combo levels;
	protected Text columnText, rowText, sizeText;
	
	public SettingDlg(Display display) {
		shell = new Shell(display, SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
		shell.setSize(400, 220);
		int x = (display.getBounds().width - 400)/2;
		int y = (display.getBounds().height - 220)/2;
		shell.setLocation(x, y);
		shell.setText(TextConstants.SETTING_TITLE);
		returnCode = SWT.CANCEL;
		column = row = size = 8;
	}
	
	public int open() {
		createControl();
		shell.open();
		while(!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch ()) shell.getDisplay().sleep ();
		}
		return returnCode;
	}
	
	protected void createControl() {
		shell.setLayout(new GridLayout());
		Composite upComp = new Composite(shell, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 5;
		upComp.setLayout(layout);
		GridData data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL
				| GridData.FILL_BOTH);
		upComp.setLayoutData(data);
		
		Button preDef = new Button(upComp, SWT.RADIO);
		preDef.setText(TextConstants.PRE_DEFINE);
		preDef.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				onPreDef();
			}
		});
		
		new Composite(upComp, SWT.None);
		
		levels = new Combo(upComp, SWT.READ_ONLY);
		data = new GridData(/*GridData.HORIZONTAL_ALIGN_BEGINNING*/);
		data.horizontalSpan = 3;
		levels.setLayoutData(data);
		levels.add(TextConstants.LEVEL_LOW);
		levels.add(TextConstants.LEVEL_MID);
		levels.add(TextConstants.LEVEL_HIGH);
		levels.select(0);
		levels.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeLevel();
			}
		});
		
		Button userDef = new Button(upComp, SWT.RADIO);
		userDef.setText(TextConstants.USER_DEFINE);
		userDef.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				onUserDef();
			}
		});
		
		Label columnLabel = new Label(upComp, SWT.None);
		columnLabel.setText(TextConstants.COLUMNS);
		
		columnText = new Text(upComp, SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		columnText.setLayoutData(data);
		
		Label rowLabel = new Label(upComp, SWT.None);
		rowLabel.setText(TextConstants.ROWS);
		
		rowText = new Text(upComp, SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		rowText.setLayoutData(data);
		
		new Composite(upComp, SWT.None);
		
		Label sizeLabel = new Label(upComp, SWT.None);
		sizeLabel.setText(TextConstants.PIGSIZE);
		
		sizeText = new Text(upComp, SWT.BORDER);
		data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		sizeText.setLayoutData(data);
		
		updateData();
		
		Composite btnComp = new Composite(shell, SWT.NONE);
		data = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		btnComp.setLayoutData(data);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 10;
		btnComp.setLayout(layout);
		
		Button ok = new Button(btnComp, SWT.PUSH);
		ok.setText(TextConstants.BUTTON_OK);
		ok.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END));
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				onOK();
			}
		});
		
		Button cancel = new Button(btnComp, SWT.PUSH);
		cancel.setText(TextConstants.BUTTON_CANCEL);
		cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				onCancel();
			}
		});
	}
	protected void onPreDef() {
		columnText.setEnabled(false);
		rowText.setEnabled(false);
		sizeText.setEnabled(false);
		levels.setEnabled(true);
		updateData();
	}
	protected void onUserDef() {
		columnText.setEnabled(true);
		rowText.setEnabled(true);
		sizeText.setEnabled(true);
		levels.setEnabled(false);
	}
	protected void updateData() {
		columnText.setText(new Integer(column).toString());
		rowText.setText(new Integer(row).toString());
		sizeText.setText(new Integer(size).toString());
	}
	protected void changeLevel() {
		int idx = levels.getSelectionIndex();
		switch(idx) {
		case 0:
			column = row = size = 8;
			break;
		case 1:
			column = row = 12;
			size = 14;
			break;
		case 2:
			column = 18;
			row = 15;
			size = 27;
		}
		updateData();
	}
	protected void onOK() {
		if("".equals(columnText.getText())
				|| "".equals(rowText.getText())
				|| "".equals(sizeText.getText())) {
			MessageBox box = new MessageBox(shell, TextConstants.ERROR_TITLE, TextConstants.BLANK_MSG, "", 2);
			box.open();
			return;
		}
		try {
			column = Integer.valueOf(columnText.getText());
			row = Integer.valueOf(rowText.getText());
			size = Integer.valueOf(sizeText.getText());
		} catch(Exception e) {
			MessageBox box = new MessageBox(shell, TextConstants.ERROR_TITLE, TextConstants.FORMAT_ERROR_MSG, "", 2);
			box.open();
			return;
		}
		if(column < 5 || column > 20 || row < 5 || row > 20) {
			MessageBox box = new MessageBox(shell, TextConstants.ERROR_TITLE, TextConstants.OVERFLOW_MSG, "", 2);
			box.open();
			return;
		}
		if(size <= 0 || size >= column * row) {
			MessageBox box = new MessageBox(shell, TextConstants.ERROR_TITLE, TextConstants.PIGSIZE_OVERFLOW_MSG, "", 2);
			box.open();
			return;
		}
		returnCode = SWT.OK;
		shell.dispose();
	}
	protected void onCancel() {
		returnCode = SWT.CANCEL;
		shell.dispose();
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
}
