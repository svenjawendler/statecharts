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

package org.yakindu.sct.generator.java.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.yakindu.scr.aftercycles.AfterCyclesStatemachine;
import org.yakindu.scr.aftercycles.AfterCyclesStatemachine.State;
import org.yakindu.scr.TimerService;
/**
 *  Unit TestCase for AfterCycles
 */
@SuppressWarnings("all")
public class AfterCyclesTest {

	private AfterCyclesStatemachine statemachine;

	@Before
	public void setUp() {
		statemachine = new AfterCyclesStatemachine();
		statemachine.setTimer(new TimerService());
		statemachine.init();
	}

	@After
	public void tearDown() {
		statemachine = null;
	}

	@Test
	public void testAftrerCyclesTest() {
		statemachine.enter();
		assertTrue(statemachine.isStateActive(State.main_region_A));
		statemachine.runCycle();
		statemachine.runCycle();
		assertTrue(statemachine.isStateActive(State.main_region_A));
		statemachine.runCycle();
		assertTrue(statemachine.isStateActive(State.main_region_B));
		statemachine.runCycle();
		assertTrue(statemachine.getMyInt() == 0l);
		statemachine.runCycle();
		assertTrue(statemachine.getMyInt() == 1l);
		assertTrue(statemachine.getAnotherInt() == 5l);
		statemachine.runCycle();
		statemachine.runCycle();
		assertTrue(statemachine.getMyInt() == 2l);
		statemachine.runCycle();
		statemachine.runCycle();
		statemachine.runCycle();
		assertTrue(statemachine.getAnotherInt() == 10l);
		assertTrue(statemachine.getMybool() == false);
	}
}
