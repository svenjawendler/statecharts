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


StringConversion handle;

TEST(StatemachineTest, StringConversionTest) {
	stringConversion_init(&handle);
	stringConversion_enter(&handle);
	stringConversion_runCycle(&handle);
	EXPECT_TRUE(stringConversion_isStateActive(&handle, StringConversion_main_region_B));
	EXPECT_TRUE(strcmp(stringConversionIface_get_anotherword(&handle) , stringConversionIface_get_word(&handle) +stringConversionIface_get_number(&handle) ) == 0);
	stringConversionIface_raise_myEvent(&handle, "EventValue"+stringConversionIface_get_boolVar(&handle) );
	stringConversion_runCycle(&handle);
	EXPECT_TRUE(strcmp(stringConversionIface_get_word(&handle) , stringConversionIface_get_anotherword(&handle) +stringConversionIface_get_boolVar(&handle) +stringConversionIface_get_realVar(&handle) ) == 0);
	EXPECT_TRUE(strcmp(stringConversionIface_get_word(&handle) , stringConversionIface_get_anotherword(&handle) +"true"+"1.1") == 0);
}

		
