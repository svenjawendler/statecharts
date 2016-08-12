/** 
 * Copyright (c) 2015 committers of YAKINDU and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 * Contributors:
 * committers of YAKINDU - initial API and implementation
 *
*/
package org.yakindu.sct.generator.java;

import com.google.inject.Singleton;

//can be bound via org.eclipse.xtext.generator.AbstractFileSystemAccess, see ISCTFileSystemAccess
@Singleton
public class Beautifier {

//	@Inject
//	JavaBeautifier javaBeautifier;

	/**
	 * Format code with PostProcessor (XPand style).
	 */
	public CharSequence format(String fileName, CharSequence code) {
//
//		// create fileHandle with dummy outlet.
//		File file = new File(fileName);
//		FileHandle fileHandle = new FileHandleImpl(new Outlet(), file);
//		fileHandle.setBuffer(code);
//
//		// call postProcessor for formatting the code.
//		javaBeautifier.beforeWriteAndClose(fileHandle);
//
//		// return formatted results.
//		return fileHandle.getBuffer();
		return code;
	}
}