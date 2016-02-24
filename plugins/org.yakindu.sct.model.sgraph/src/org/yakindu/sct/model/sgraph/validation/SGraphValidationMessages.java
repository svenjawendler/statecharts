/**
 * Copyright (c) 2016 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.model.sgraph.validation;

public interface SGraphValidationMessages {

	// Validation Messages
	String ISSUE_STATE_WITHOUT_NAME_MSG = "A state must have a name.";
	String ISSUE_NODE_NOT_REACHABLE_MSG = "Node is not reachable.";
	String ISSUE_FINAL_STATE_OUTGOING_TRANSITION_MSG = "A final state should have no outgoing transition.";
	String ISSUE_STATE_WITHOUT_OUTGOING_TRANSITION_MSG = "A state should have at least one outgoing transition.";
	String ISSUE_INITIAL_ENTRY_WITH_IN_TRANS_MSG = "Initial entry should have no incoming transition.";
	String ISSUE_INITIAL_ENTRY_WITHOUT_OUT_TRANS_MSG = "Initial entry should have a single outgoing transition";
	String ISSUE_ENTRY_WITH_MULTIPLE_OUT_TRANS_MSG = "Entries must not have more than one outgoing transition";
	String ISSUE_ENTRY_WITH_TRIGGER_MSG = "Outgoing Transitions from Entries can not have a Trigger or Guard.";
	String ISSUE_EXIT_WITH_OUT_TRANS_MSG = "Exit node should have no outgoing transition.";
	String ISSUE_EXIT_WITHOUT_IN_TRANS_MSG = "Exit node should have at least one incoming transition";
	String ISSUE_EXIT_ON_STATECHART_MSG = "Exit node in top level region not supported - use final states instead.";
	String ISSUE_CHOICE_WITHOUT_OUTGOING_TRANSITION_MSG = "A choice must have at least one outgoing transition.";
	String ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NO_DEFAULT_ENTRY_MSG = "The region can't be entered using the shallow history. Add a default entry node.";
	String ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NON_CONNECTED_DEFAULT_ENTRY_MSG = "The region can't be entered using the shallow history. Add a transition from default entry to a state.";
	String ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_ORTHOGONAL_MSG = "The target states of a synchronization must be orthogonal!";
	String ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_WITHIN_SAME_PARENTSTATE_MSG = "The target states of a synchronization have to be contained in the same parent state within different regions!";
	String ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_ORTHOGONAL_MSG = "The source states of a synchronization must be orthogonal!";
	String ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_WITHIN_SAME_PARENTSTATE_MSG = "The source states of a synchronization have to be contained in the same parent state within different regions!";
	String ISSUE_SYNCHRONIZATION_TRANSITION_COUNT_MSG = "A synchronization should have at least two incoming or two outgoing transitions";
	String ISSUE_INITIAL_ENTRY_WITH_TRANSITION_TO_CONTAINER_MSG = "Outgoing Transitions from Entries can only target to sibling or inner states.";

	// Validation Codes
	String ISSUE_STATE_WITHOUT_NAME_CODE = "SC-001";
	String ISSUE_NODE_NOT_REACHABLE_CODE = "SC-002";
	String ISSUE_FINAL_STATE_OUTGOING_TRANSITION_CODE = "SC-003";
	String ISSUE_STATE_WITHOUT_OUTGOING_TRANSITION_CODE = "SC-004";
	String ISSUE_INITIAL_ENTRY_WITH_IN_TRANS_CODE = "SC-005";
	String ISSUE_INITIAL_ENTRY_WITHOUT_OUT_TRANS_CODE = "SC-006";
	String ISSUE_ENTRY_WITH_MULTIPLE_OUT_TRANS_CODE = "SC-007";
	String ISSUE_ENTRY_WITH_TRIGGER_CODE = "SC-008";
	String ISSUE_EXIT_WITH_OUT_TRANS_CODE = "SC-009";
	String ISSUE_EXIT_WITHOUT_IN_TRANS_CODE = "SC-010";
	String ISSUE_EXIT_ON_STATECHART_CODE = "SC-011";
	String ISSUE_CHOICE_WITHOUT_OUTGOING_TRANSITION_CODE = "SC-012";
	String ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NO_DEFAULT_ENTRY_CODE = "SC-013";
	String ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NON_CONNECTED_DEFAULT_ENTRY_CODE = "SC-014";
	String ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_ORTHOGONAL_CODE = "SC-015";
	String ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_WITHIN_SAME_PARENTSTATE_CODE = "SC-016";
	String ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_ORTHOGONAL_CODE = "SC-017";
	String ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_WITHIN_SAME_PARENTSTATE_CODE = "SC-018";
	String ISSUE_SYNCHRONIZATION_TRANSITION_COUNT_CODE = "SC-019";
	String ISSUE_INITIAL_ENTRY_WITH_TRANSITION_TO_CONTAINER_CODE = "SC-020";
}
