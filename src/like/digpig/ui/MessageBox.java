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
 * @author zl
 * 
 */
public class MessageBox {
	protected Shell shell;
	protected String message;
	protected String subMsg;
	protected int type;
	protected int returnCode;

	/**
	 * 
	 * @param parent
	 * @param title
	 * @param message
	 * @param subMsg
	 * @param type 2-warning, other-confirm
	 */
	public MessageBox(Shell parent, String title, String message, String subMsg, int type) {
		shell = new Shell(parent, SWT.SYSTEM_MODAL |SWT.RESIZE | SWT.MIN | SWT.CLOSE);
		shell.setSize(400, 150);
		int x = parent.getLocation().x + (parent.getSize().x - 400)/2;
		int y = parent.getLocation().y + (parent.getSize().y - 150)/2;
		shell.setLocation(x, y);
		shell.setText(title);
		returnCode = SWT.CANCEL;
		this.message = message;
		this.subMsg = subMsg;
		this.type = type;
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
		Composite dataComp = new Composite(shell, SWT.NONE);
		GridData data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL
				| GridData.FILL_BOTH);
		dataComp.setLayoutData(data);
		
		dataComp.setLayout(new GridLayout());
		Label label = new Label(dataComp, SWT.NONE);
		label.setFont(FontFactory.getBoldFont());
		label.setText(message);
		
		Label worning = new Label(dataComp, SWT.NONE);
		worning.setText(subMsg);
		
//		Button checkbox = new Button(dataComp, SWT.CHECK);
//		checkbox.setText("update?");
//		checkbox.setSelection(true);
//		checkbox.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				
//			}
//		});
//		data = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
//		data.horizontalSpan = 2;
//		checkbox.setLayoutData(data);
		
		Composite btnComp = new Composite(shell, SWT.NONE);
		data = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		btnComp.setLayoutData(data);
		GridLayout layout = new GridLayout();
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
		
		if(type != 2) {
			Button cancel = new Button(btnComp, SWT.PUSH);
			cancel.setText(TextConstants.BUTTON_CANCEL);
			cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			cancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					onCancel();
				}
			});
		}
	}
	protected void onOK() {
		returnCode = SWT.OK;
		shell.dispose();
	}
	protected void onCancel() {
		returnCode = SWT.CANCEL;
		shell.dispose();
	}
}
