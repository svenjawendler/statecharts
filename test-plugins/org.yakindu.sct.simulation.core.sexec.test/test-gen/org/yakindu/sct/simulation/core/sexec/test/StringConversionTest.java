/**
 * Copyright (c) 2015 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.simulation.core.sexec.test;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sexec.interpreter.test.util.AbstractExecutionFlowTest;
import org.yakindu.sct.model.sexec.interpreter.test.util.SExecInjectionProvider;
import org.yakindu.sct.test.models.SCTUnitTestModels;
import com.google.inject.Inject;
import static org.junit.Assert.assertTrue;
/**
 *  Unit TestCase for StringConversion
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class StringConversionTest extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception {
		ExecutionFlow flow = models
				.loadExecutionFlowFromResource("StringConversion.sct");
		initInterpreter(flow);
	}
	@Test
	public void StringConversionTest() throws Exception {
		interpreter.enter();
		interpreter.runCycle();
		assertTrue(isStateActive("B"));
		assertTrue(getString("anotherword").equals(
				getString("word") + String.valueOf(getInteger("number"))));
		raiseEvent("myEvent", "EventValue");
		interpreter.runCycle();
		assertTrue(getString("anotherword").equals("EventValue"));
		assertTrue(getString("word").equals(
				getString("anotherword")
						+ String.valueOf(getBoolean("boolVar"))
						+ String.valueOf(getReal("realVar"))));
		assertTrue(getString("word").equals(
				getString("anotherword") + "true" + "1.1"));
	}
}
