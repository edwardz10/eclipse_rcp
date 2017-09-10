package com.packtpub.e4.clock.ui.views;


import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.ClockWidget;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ClockView extends ViewPart {

	private Combo timezones;
	
	@Override
	public void createPartControl(Composite parent) {
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		parent.setLayout(layout);

		final ClockWidget clock1 = new ClockWidget(parent, SWT.NONE, new RGB(255,0,0));
		final ClockWidget clock2 = new ClockWidget(parent, SWT.NONE, new RGB(0,255,0));
		final ClockWidget clock3 = new ClockWidget(parent, SWT.NONE, new RGB(0,0,255));

		String[] ids = TimeZone.getAvailableIDs();
		timezones = new Combo(parent, SWT.SIMPLE);
		timezones.setVisibleItemCount(5);

		for (int i = 0; i < ids.length; i++) {
			timezones.add(ids[i]);
		}

		timezones.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String z = timezones.getText();	
				TimeZone tz = z == null ? null : TimeZone.getTimeZone(z);
				TimeZone dt = TimeZone.getDefault();
				int offset = tz == null ? 0 : (
						 tz.getOffset(System.currentTimeMillis()) -
						 dt.getOffset(System.currentTimeMillis())) / 3600000;
				clock3.setOffset(offset);
				clock3.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				clock3.setOffset(0);
				clock3.redraw();				
			}
		});
	}

	@Override
	public void setFocus() {
		timezones.setFocus();
	}

	
//	private TableViewer viewer;
//	private Action action1;
//	private Action action2;
//	private Action doubleClickAction;
//	 
//
//	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
//		public String getColumnText(Object obj, int index) {
//			return getText(obj);
//		}
//		public Image getColumnImage(Object obj, int index) {
//			return getImage(obj);
//		}
//		public Image getImage(Object obj) {
//			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
//		}
//	}
//
///**
//	 * The constructor.
//	 */
//	public ClockView() {
//	}
//
//	/**
//	 * This is a callback that will allow us
//	 * to create the viewer and initialize it.
//	 */
//	public void createPartControl(Composite parent) {
//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		
//		viewer.setContentProvider(ArrayContentProvider.getInstance());
//		viewer.setInput(new String[] { "One", "Two", "Three" });
//	viewer.setLabelProvider(new ViewLabelProvider());
//		getSite().setSelectionProvider(viewer);
//		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
//		contributeToActionBars();
//	}
//
//	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				ClockView.this.fillContextMenu(manager);
//			}
//		});
//		Menu menu = menuMgr.createContextMenu(viewer.getControl());
//		viewer.getControl().setMenu(menu);
//		getSite().registerContextMenu(menuMgr, viewer);
//	}
//
//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
//	}
//
//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(action1);
//		manager.add(new Separator());
//		manager.add(action2);
//	}
//
//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(action1);
//		manager.add(action2);
//		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
//	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(action1);
//		manager.add(action2);
//	}
//
//	private void makeActions() {
//		action1 = new Action() {
//			public void run() {
//				showMessage("Action 1 executed");
//			}
//		};
//		action1.setText("Action 1");
//		action1.setToolTipText("Action 1 tooltip");
//		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		
//		action2 = new Action() {
//			public void run() {
//				showMessage("Action 2 executed");
//			}
//		};
//		action2.setText("Action 2");
//		action2.setToolTipText("Action 2 tooltip");
//		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		doubleClickAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
//			}
//		};
//	}
//
//	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
//			}
//		});
//	}
//	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(),
//			"Clock View",
//			message);
//	}
//
//	/**
//	 * Passing the focus request to the viewer's control.
//	 */
//	public void setFocus() {
//		viewer.getControl().setFocus();
//	}
}
