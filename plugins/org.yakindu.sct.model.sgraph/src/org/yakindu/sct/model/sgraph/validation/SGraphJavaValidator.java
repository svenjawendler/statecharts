/**
 * Copyright (c) 2012 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.model.sgraph.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.EValidatorRegistrar;
import org.yakindu.base.base.BasePackage;
import org.yakindu.sct.model.sgraph.Choice;
import org.yakindu.sct.model.sgraph.CompositeElement;
import org.yakindu.sct.model.sgraph.Entry;
import org.yakindu.sct.model.sgraph.EntryKind;
import org.yakindu.sct.model.sgraph.Exit;
import org.yakindu.sct.model.sgraph.FinalState;
import org.yakindu.sct.model.sgraph.Region;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Synchronization;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.sgraph.Vertex;

import com.google.inject.Inject;

/**
 * This validator is intended to be used by a compositeValidator (See
 * {@link org.eclipse.xtext.validation.ComposedChecks}) of another language
 * specific validator. It does not register itself as an EValidator.
 * 
 * This validator checks for common graphical constraints for all kinds of state
 * charts.
 * 
 * @author terfloth
 * @author muelder
 * @author bohl - migrated to xtext infrastruture
 * @author schwertfeger
 * @author antony
 */
public class SGraphJavaValidator extends AbstractDeclarativeValidator implements SGraphValidationMessages {

	@Check(CheckType.FAST)
	public void vertexNotReachable(final Vertex vertex) {
		if (!(vertex instanceof Entry)) {
			final Set<Object> stateScopeSet = new HashSet<Object>();
			for (EObject obj : EcoreUtil2.eAllContents(vertex)) {
				stateScopeSet.add(obj);
			}
			stateScopeSet.add(vertex);
			final List<Object> externalPredecessors = new ArrayList<Object>();
			DFS dfs = new DFS() {
				@Override
				public Iterator<Object> getElementLinks(Object element) {
					List<Object> elements = new ArrayList<Object>();

					if (element instanceof org.yakindu.sct.model.sgraph.State) {
						if (!stateScopeSet.contains(element)) {
							externalPredecessors.add(element);
						} else {
							elements.addAll(((org.yakindu.sct.model.sgraph.State) element).getRegions());
							elements.addAll(((org.yakindu.sct.model.sgraph.State) element).getIncomingTransitions());
						}

					} else if (element instanceof Region) {
						elements.addAll(((Region) element).getVertices());
					} else if (element instanceof Entry) {
						if (!stateScopeSet.contains(element)) {
							externalPredecessors.add(element);
						} else {
							elements.addAll(((Entry) element).getIncomingTransitions());
						}

					} else if (element instanceof Vertex) {
						elements.addAll(((Vertex) element).getIncomingTransitions());

					} else if (element instanceof Transition) {
						elements.add(((Transition) element).getSource());

					}

					return elements.iterator();
				}
			};

			dfs.perform(vertex);
			if (externalPredecessors.size() == 0) {
				error(ISSUE_NODE_NOT_REACHABLE_MSG, vertex, null, ISSUE_NODE_NOT_REACHABLE_CODE);
			}
		}
	}

	@Check(CheckType.FAST)
	public void finalStateWithOutgoingTransition(FinalState finalState) {
		if ((finalState.getOutgoingTransitions().size() > 0)) {
			warning(ISSUE_FINAL_STATE_OUTGOING_TRANSITION_MSG, finalState, null,
					ISSUE_FINAL_STATE_OUTGOING_TRANSITION_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void nameIsNotEmpty(org.yakindu.sct.model.sgraph.State state) {
		if ((state.getName() == null || state.getName().trim().length() == 0) && !(state instanceof FinalState)) {
			error(ISSUE_STATE_WITHOUT_NAME_MSG, state, null, ISSUE_STATE_WITHOUT_NAME_CODE,
					BasePackage.Literals.NAMED_ELEMENT__NAME.getName());
		}
	}

	@Check(CheckType.FAST)
	public void choiceWithoutOutgoingTransition(Choice choice) {
		// Choice without outgoing transition
		if (choice.getOutgoingTransitions().size() == 0) {
			error(ISSUE_CHOICE_WITHOUT_OUTGOING_TRANSITION_MSG, choice, null,
					ISSUE_CHOICE_WITHOUT_OUTGOING_TRANSITION_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void disallowTrigger(Entry entry) {
		for (Transition transition : entry.getOutgoingTransitions()) {
			if (transition.getTrigger() != null) {
				error(ISSUE_ENTRY_WITH_TRIGGER_MSG, entry, null, ISSUE_ENTRY_WITH_TRIGGER_CODE);
			}
		}
	}

	@Check(CheckType.FAST)
	public void initialEntryWithoutIncomingTransitions(Entry entry) {
		if (entry.getIncomingTransitions().size() > 0 && entry.getKind().equals(EntryKind.INITIAL)) {
			warning(ISSUE_INITIAL_ENTRY_WITH_IN_TRANS_MSG, entry, null, ISSUE_INITIAL_ENTRY_WITH_IN_TRANS_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void initialEntryWithoutOutgoingTransition(Entry entry) {
		if (entry.getOutgoingTransitions().size() == 0 && ((Entry) entry).getKind().equals(EntryKind.INITIAL)) {
			warning(ISSUE_INITIAL_ENTRY_WITHOUT_OUT_TRANS_MSG, entry, null, ISSUE_INITIAL_ENTRY_WITHOUT_OUT_TRANS_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void initialEntryWithMultipleOutgoingTransition(Entry entry) {
		if (entry.getOutgoingTransitions().size() > 1) {
			error(ISSUE_ENTRY_WITH_MULTIPLE_OUT_TRANS_MSG, entry, null, ISSUE_ENTRY_WITH_MULTIPLE_OUT_TRANS_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void exitWithoutIncomingTransition(Exit exit) {
		if (exit.getIncomingTransitions().size() == 0) {
			warning(ISSUE_EXIT_WITHOUT_IN_TRANS_MSG, exit, null, ISSUE_EXIT_WITHOUT_IN_TRANS_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void exitWithOutgoingTransition(Exit exit) {
		if (exit.getOutgoingTransitions().size() > 0) {
			error(ISSUE_EXIT_WITH_OUT_TRANS_MSG, exit, null, ISSUE_EXIT_WITH_OUT_TRANS_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void exitOnStatechart(Exit exit) {
		if (exit.getParentRegion().getComposite() instanceof Statechart) {
			error(ISSUE_EXIT_ON_STATECHART_MSG, exit, null, ISSUE_EXIT_ON_STATECHART_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void synchronizationTransitionCount(Synchronization sync) {
		if (sync.getIncomingTransitions().size() < 2 && sync.getOutgoingTransitions().size() < 2) {
			warning(ISSUE_SYNCHRONIZATION_TRANSITION_COUNT_MSG, sync, null,
					ISSUE_SYNCHRONIZATION_TRANSITION_COUNT_CODE);
		}
	}

	@Check(CheckType.FAST)
	public void initialEntryWithTransitionToContainer(Transition t) {
		if (t.getSource() instanceof Entry && !isChildOrSibling(t.getSource(), t.getTarget())) {
			error(ISSUE_INITIAL_ENTRY_WITH_TRANSITION_TO_CONTAINER_MSG, t, null,
					ISSUE_INITIAL_ENTRY_WITH_TRANSITION_TO_CONTAINER_CODE);
		}
	}

	protected boolean isChildOrSibling(Vertex source, Vertex target) {
		TreeIterator<EObject> iter = source.getParentRegion().eAllContents();
		while (iter.hasNext()) {
			if (target == iter.next()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if all composite states that are siblings of a shallow history can
	 * enter their regions.
	 * 
	 * @param e
	 */
	@Check(CheckType.FAST)
	public void regionCantBeEnteredUsingShallowHistory(Entry e) {
		if (e.getKind() == EntryKind.SHALLOW_HISTORY) {
			// get all regions off all sibling states
			List<Region> regions = new ArrayList<Region>();
			for (Vertex v : e.getParentRegion().getVertices()) {
				if (v instanceof org.yakindu.sct.model.sgraph.State) {
					org.yakindu.sct.model.sgraph.State state = (org.yakindu.sct.model.sgraph.State) v;
					regions.addAll(state.getRegions());
				}
			}
			// check each region
			for (Region r : regions) {
				// first determine if the region contains a default entry
				Entry defaultEntry = null;
				for (Vertex v : r.getVertices()) {
					if (v instanceof Entry) {
						String name = v.getName().trim().toLowerCase();
						if (name != null || "".equals(name) || "default".equals(name)) {
							defaultEntry = (Entry) v;
							break;
						}
					}
				}
				// now check error conditions
				if (defaultEntry == null) {
					error(ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NO_DEFAULT_ENTRY_MSG, r, null,
							ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NO_DEFAULT_ENTRY_CODE);
				} else if (defaultEntry.getOutgoingTransitions().size() != 1) {
					error(ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NON_CONNECTED_DEFAULT_ENTRY_MSG, r, null,
							ISSUE_REGION_CANT_BE_ENTERED_USING_SHALLOW_HISTORY_NON_CONNECTED_DEFAULT_ENTRY_CODE);
				}
			}

		}

	}

	@Check(CheckType.FAST)
	public void orthogonalStates(Synchronization fork) {
		// check target states
		orthogonalStates(fork, true);
		// check source states
		orthogonalStates(fork, false);
	}

	private void orthogonalStates(Synchronization fork, boolean searchTarget) {
		List<Transition> transitions = searchTarget ? fork.getOutgoingTransitions() : fork.getIncomingTransitions();
		if (transitions.size() > 1) {
			final Transition firstTransition = transitions.get(0);
			final Vertex vertex = searchTarget ? firstTransition.getTarget() : firstTransition.getSource();

			CompositeElement root = findCommonRootCompositeElement(vertex.getParentRegion().getComposite(), fork,
					searchTarget);

			if (root != null) {
				for (Transition t : transitions) {
					Region parentRegion = searchTarget ? t.getTarget().getParentRegion()
							: t.getSource().getParentRegion();
					for (Transition transition : transitions) {
						if (transition != t && EcoreUtil.isAncestor(parentRegion,
								searchTarget ? transition.getTarget() : transition.getSource())) {
							error(searchTarget ? ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_ORTHOGONAL_MSG
									: ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_ORTHOGONAL_MSG, fork, null,
									searchTarget ? ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_ORTHOGONAL_CODE
											: ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_ORTHOGONAL_CODE);
							break;
						}
					}
				}
			} else {
				error(searchTarget ? ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_WITHIN_SAME_PARENTSTATE_MSG
						: ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_WITHIN_SAME_PARENTSTATE_MSG, fork, null,
						searchTarget ? ISSUE_SYNCHRONIZATION_TARGET_STATES_NOT_WITHIN_SAME_PARENTSTATE_CODE
								: ISSUE_SYNCHRONIZATION_SOURCE_STATES_NOT_WITHIN_SAME_PARENTSTATE_CODE);
			}

		}
	}

	protected CompositeElement findCommonRootCompositeElement(CompositeElement root, Synchronization fork,
			boolean searchTarget) {
		CompositeElement ret = root;
		if (ret != fork.getParentRegion().getComposite()) {
			for (Transition transition : searchTarget ? fork.getOutgoingTransitions() : fork.getIncomingTransitions()) {
				if (ret != null
						&& !EcoreUtil.isAncestor(ret, searchTarget ? transition.getTarget() : transition.getSource())) {
					if (ret.eContainer() instanceof Region) {
						final CompositeElement newRoot = ((Region) root.eContainer()).getComposite();
						ret = findCommonRootCompositeElement(newRoot, fork, searchTarget);
					}
				}
			}
			return ret;
		}
		return null;
	}

	@Override
	public boolean isLanguageSpecific() {
		return false;
	}

	@Inject
	public void register(EValidatorRegistrar registrar) {
		// Do not register because this validator is only a composite #398987
	}
}
