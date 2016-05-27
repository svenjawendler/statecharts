package org.yakindu.sct.standalone.app;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("SCT Standalone START");
		return null;
	}

	@Override
	public void stop() {
		System.out.println("SCT Standalone END");
	}

}
