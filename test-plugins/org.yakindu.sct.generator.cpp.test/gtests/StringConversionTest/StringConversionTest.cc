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
#include "StringConversion.h"

TEST(StatemachineTest, StringConversionTest) {
	StringConversion* statechart = new StringConversion();
	statechart->init();
	statechart->enter();
	statechart->runCycle();
	EXPECT_TRUE(statechart->isStateActive(StringConversion::main_region_B));
	EXPECT_TRUE(strcmp(statechart->getDefaultSCI()->get_anotherword(), statechart->getDefaultSCI()->get_word()+statechart->getDefaultSCI()->get_number()) == 0);
	statechart->raise_myEvent( "EventValue"+statechart->getDefaultSCI()->get_boolVar());
	statechart->runCycle();
	EXPECT_TRUE(strcmp(statechart->getDefaultSCI()->get_word(), statechart->getDefaultSCI()->get_anotherword()+statechart->getDefaultSCI()->get_boolVar()+statechart->getDefaultSCI()->get_realVar()) == 0);
	EXPECT_TRUE(strcmp(statechart->getDefaultSCI()->get_word(), statechart->getDefaultSCI()->get_anotherword()+"true"+"1.1") == 0);
	delete statechart;
}
