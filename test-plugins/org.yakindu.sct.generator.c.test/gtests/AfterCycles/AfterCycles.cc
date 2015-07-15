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
#include <string>
#include "gtest/gtest.h"
#include "AfterCycles.h"
#include "AfterCyclesRequired.h"


AfterCycles handle;

TEST(StatemachineTest, AftrerCyclesTest) {
	afterCycles_init(&handle);
	afterCycles_enter(&handle);
	EXPECT_TRUE(afterCycles_isStateActive(&handle, AfterCycles_main_region_A));
	afterCycles_runCycle(&handle);
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCycles_isStateActive(&handle, AfterCycles_main_region_A));
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCycles_isStateActive(&handle, AfterCycles_main_region_B));
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCyclesIface_get_myInt(&handle) == 0l);
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCyclesIface_get_myInt(&handle) == 1l);
	EXPECT_TRUE(afterCyclesIface_get_anotherInt(&handle) == 5l);
	afterCycles_runCycle(&handle);
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCyclesIface_get_myInt(&handle) == 2l);
	afterCycles_runCycle(&handle);
	afterCycles_runCycle(&handle);
	afterCycles_runCycle(&handle);
	EXPECT_TRUE(afterCyclesIface_get_anotherInt(&handle) == 10l);
	EXPECT_TRUE(afterCyclesIface_get_mybool(&handle) == false);
}

		
void afterCycles_setTimer(const sc_eventid evid, const sc_integer time_ms, const sc_boolean periodic){
	// Mockup
	afterCycles_raiseTimeEvent(&handle, evid);
}

void afterCycles_unsetTimer(const sc_eventid evid){
	// Mockup
}		
