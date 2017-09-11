package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;

public class Hello {

	@Inject @Optional
	private LogService log;
	@Inject
	private MWindow window;
	private Label label;

	@PostConstruct
	public void create(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setText(window.getLabel());
		log.log(LogService.LOG_ERROR, "Hello");
	}

	@Focus
	public void focus() {
		label.setFocus();
	}

	@Inject
	@Optional
	public void receiveEvent(@UIEventTopic("rainbow/color") String data) {
		label.setText(data);
	}
}
