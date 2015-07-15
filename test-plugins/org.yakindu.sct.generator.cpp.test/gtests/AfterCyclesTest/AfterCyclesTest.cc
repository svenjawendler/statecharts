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

TEST(StatemachineTest, AftrerCyclesTest) {
	AfterCycles* statechart = new AfterCycles();
	statechart->init();
	statechart->enter();
	EXPECT_TRUE(statechart->isStateActive(AfterCycles::main_region_A));
	statechart->runCycle();
	statechart->runCycle();
	EXPECT_TRUE(statechart->isStateActive(AfterCycles::main_region_A));
	statechart->runCycle();
	EXPECT_TRUE(statechart->isStateActive(AfterCycles::main_region_B));
	statechart->runCycle();
	EXPECT_TRUE(statechart->getDefaultSCI()->get_myInt()== 0l);
	statechart->runCycle();
	EXPECT_TRUE(statechart->getDefaultSCI()->get_myInt()== 1l);
	EXPECT_TRUE(statechart->getDefaultSCI()->get_anotherInt()== 5l);
	statechart->runCycle();
	statechart->runCycle();
	EXPECT_TRUE(statechart->getDefaultSCI()->get_myInt()== 2l);
	statechart->runCycle();
	statechart->runCycle();
	statechart->runCycle();
	EXPECT_TRUE(statechart->getDefaultSCI()->get_anotherInt()== 10l);
	EXPECT_TRUE(statechart->getDefaultSCI()->get_mybool()== false);
	delete statechart;
}
