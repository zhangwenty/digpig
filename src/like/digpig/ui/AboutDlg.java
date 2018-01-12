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
import like.digpig.util.FontFactory;
import like.digpig.util.TextConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author danny
 *
 */
public class AboutDlg {
	protected Shell shell;
	
	public AboutDlg(Shell parent) {
		shell = new Shell(parent, SWT.SYSTEM_MODAL | SWT.DIALOG_TRIM | SWT.MIN | SWT.CLOSE);
		shell.setSize(400, 150);
		int x = parent.getLocation().x + (parent.getSize().x - 400)/2;
		int y = parent.getLocation().y + (parent.getSize().y - 150)/2;
		shell.setLocation(x, y);
		shell.setText(TextConstants.ABOUT_TITLE);
	}
	
	public int open() {
		createControl();
		shell.open();
		while(!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch ()) shell.getDisplay().sleep ();
		}
		return 0;
	}
	
	protected void createControl() {
		shell.setLayout(new GridLayout());
		Composite upComp = new Composite(shell, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		upComp.setLayout(layout);
		GridData data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL
				| GridData.FILL_BOTH);
		upComp.setLayoutData(data);
		Label img = new Label(upComp, SWT.None);
		img.setImage(ImageProvider.getImage(ImageProvider.PIG_IMAGE));
		
		Label caption = new Label(upComp, SWT.None);
		caption.setText(TextConstants.ABOUT_TEXT);
		caption.setFont(FontFactory.getBoldFont());
		data = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER);
		caption.setLayoutData(data);
		
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
				shell.dispose();
			}
		});
	}
}
