/**
 * Copyright (c) 2014 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	itemis AG - initial API and implementation
 * 
 */
package org.yakindu.sct.model.stext.validation;

/**
 * 
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public interface STextValidationMessages {

	// Validation Messages
	String CHOICE_ONE_OUTGOING_DEFAULT_TRANSITION_MSG = "A choice should have one outgoing default transition";
	String FEATURE_CALL_HAS_NO_EFFECT_MSG = "FeatureCall has no effect";
	String ENTRY_EXIT_TRIGGER_NOT_ALLOWED_MSG = "Entry/Exit trigger not allowed";
	String LOCAL_REACTIONS_NOT_ALLOWED_MSG = "Local reactions not allowed";
	String ONLY_ONE_INTERFACE_MSG = "Only one default/unnamed interface is allowed.";
	String IN_OUT_DECLARATIONS_MSG = "In/Out declarations are not allowed in internal scope.";
	String LOCAL_DECLARATIONS_MSG = "Local declarations are not allowed in interface scope.";
	String TIME_EXPRESSION_MSG = "The evaluation result of a time expression must be of type integer";
	String GUARD_EXPRESSION_MSG = "The evaluation result of a guard expression must be of type boolean";
	String ASSIGNMENT_EXPRESSION_MSG = "No nested assignment of the same variable allowed (different behavior in various programming languages)";
	String TRANSITION_ENTRY_SPEC_NOT_COMPOSITE_MSG = "Target state isn't composite";
	String TRANSITION_EXIT_SPEC_NOT_COMPOSITE_MSG = "Source state isn't composite";
	String TRANSITION_UNBOUND_DEFAULT_ENTRY_POINT_MSG = "Target state has regions without 'default' entries.";
	String TRANSITION_UNBOUND_NAMED_ENTRY_POINT_MSG = "Target state has regions without named entries: ";
	String TRANSITION_NOT_EXISTING_NAMED_EXIT_POINT_MSG = "Source State needs at least one region with the named exit point";
	String REGION_UNBOUND_DEFAULT_ENTRY_POINT_MSG = "Region must have a 'default' entry.";
	String REGION_UNBOUND_NAMED_ENTRY_POINT_MSG = "Region should have a named entry to support transitions entry specification: ";
	String ENTRY_UNUSED_MSG = "The named entry is not used by incoming transitions.";
	String EXIT_UNUSED_MSG = "The named exit is not used by outgoing transitions.";
	String EXIT_DEFAULT_UNUSED_MSG = "The parent composite state has no 'default' exit transition.";
	String TRANSITION_EXIT_SPEC_ON_MULTIPLE_SIBLINGS_MSG = "ExitPointSpec can't be used on transition siblings.";
	String LEFT_HAND_ASSIGNMENT_MSG = "The left-hand side of an assignment must be a variable";
	String ISSUE_TRANSITION_WITHOUT_TRIGGER_MSG = "Missing trigger. Transisition is never taken. Use 'oncycle' or 'always' instead";
	String IMPORT_NOT_RESOLVED_MSG = "Import cannot be resolved";
	String EXITPOINTSPEC_WITH_TRIGGER_MSG = "Transitions with an exit point spec must not have a trigger or guard.";
	String ASSIGNMENT_TO_VALUE_MSG = "Assignment to constant not allowed";
	String REFERENCE_TO_VARIABLE_MSG = "Cannot reference a variable in a constant initialization";
	String REFERENCE_CONSTANT_BEFORE_DEFINED_MSG = "Cannot reference a constant from different scope or before it is defined";
	String INTERNAL_DECLARATION_UNUSED_MSG = "Internal declaration is not used in statechart";
	String WRONG_NUMBER_OF_ARGS_MSG = "Wrong number of arguments, expected %s";
	String VAR_EVENT_OPERATION_REQUIRED_MSG = "A variable, event or operation is required";
	String ENTRY_LOCAL_REACTION_ONLY_MSG = "entry and exit events are allowed as local reactions only.";
	String ACTION_HAS_NO_EFFECT_MSG = "Action has no effect.";
	String ACCESS_TO_FEATURE_NO_EFFECT_MSG = "Access to property '%s' has no effect";
	String IMPORT_CAN_NOT_BE_RESOLVED_MSG = "The import '%s' cannot be resolved";
	
	// Validation Codes
	String CHOICE_ONE_OUTGOING_DEFAULT_TRANSITION_CODE = "ST-001";
	String FEATURE_CALL_HAS_NO_EFFECT_CODE = "ST-002";
	String ENTRY_EXIT_TRIGGER_NOT_ALLOWED_CODE = "ST-003";
	String LOCAL_REACTIONS_NOT_ALLOWED_CODE = "ST-004";
	String FEATURE_CALL_TO_SCOPE_CODE = "ST-005";
	String ONLY_ONE_INTERFACE_CODE = "ST-006";
	String IN_OUT_DECLARATIONS_CODE = "ST-007";
	String LOCAL_DECLARATIONS_CODE = "ST-008";
	String TIME_EXPRESSION_CODE = "ST-009";
	String GUARD_EXPRESSION_CODE = "ST-010";
	String ASSIGNMENT_EXPRESSION_CODE = "ST-011";
	String TRANSITION_ENTRY_SPEC_NOT_COMPOSITE_CODE = "ST-012";
	String TRANSITION_EXIT_SPEC_NOT_COMPOSITE_CODE = "ST-013";
	String TRANSITION_UNBOUND_DEFAULT_ENTRY_POINT_CODE = "ST-014";
	String TRANSITION_UNBOUND_NAMED_ENTRY_POINT_CODE = "ST-015";
	String TRANSITION_NOT_EXISTING_NAMED_EXIT_POINT_CODE = "ST-016";
	String REGION_UNBOUND_DEFAULT_ENTRY_POINT_CODE = "ST-017";
	String REGION_UNBOUND_NAMED_ENTRY_POINT_CODE = "ST-018";
	String ENTRY_UNUSED_CODE = "ST-019";
	String EXIT_UNUSED_CODE = "ST-020";
	String EXIT_DEFAULT_UNUSED_CODE = "ST-021";
	String TRANSITION_EXIT_SPEC_ON_MULTIPLE_SIBLINGS_CODE = "ST-022";
	String LEFT_HAND_ASSIGNMENT_CODE = "ST-023";
	String ISSUE_TRANSITION_WITHOUT_TRIGGER_CODE = "ST-024";
	String IMPORT_NOT_RESOLVED_CODE = "ST-025";
	String EXITPOINTSPEC_WITH_TRIGGER_CODE = "ST-026";
	String ASSIGNMENT_TO_VALUE_CODE = "ST-027";
	String REFERENCE_TO_VARIABLE_CODE = "ST-028";
	String REFERENCE_CONSTANT_BEFORE_DEFINED_CODE = "ST-029";
	String INTERNAL_DECLARATION_UNUSED_CODE = "ST-030";
	String WRONG_NUMBER_OF_ARGS_CODE = "ST-031";
	String VAR_EVENT_OPERATION_REQUIRED_CODE = "ST-032";
	String ENTRY_LOCAL_REACTION_ONLY_CODE = "ST-033";
	String ACTION_HAS_NO_EFFECT_CODE = "ST-034";
	String ACCESS_TO_FEATURE_NO_EFFECT_CODE = "ST-035";
	String IMPORT_CAN_NOT_BE_RESOLVED_CODE = "ST-036";
}
