/**
 * Copyright (c) 2015 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */
package org.yakindu.sct.simulation.core.sexec.container

import com.google.inject.Inject
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.yakindu.base.types.Declaration
import org.yakindu.base.types.Package
import org.yakindu.base.types.inferrer.ITypeSystemInferrer
import org.yakindu.base.types.typesystem.ITypeSystem
import org.yakindu.base.types.typesystem.ITypeValueProvider
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sexec.TimeEvent
import org.yakindu.sct.model.sgraph.ImportDeclaration
import org.yakindu.sct.model.sgraph.Scope
import org.yakindu.sct.model.stext.stext.EventDefinition
import org.yakindu.sct.model.stext.stext.ImportScope
import org.yakindu.sct.model.stext.stext.InterfaceScope
import org.yakindu.sct.model.stext.stext.InternalScope
import org.yakindu.sct.model.stext.stext.OperationDefinition
import org.yakindu.sct.model.stext.stext.VariableDefinition
import org.yakindu.sct.simulation.core.sruntime.CompositeSlot
import org.yakindu.sct.simulation.core.sruntime.EventDirection
import org.yakindu.sct.simulation.core.sruntime.ExecutionContext
import org.yakindu.sct.simulation.core.sruntime.ExecutionSlot
import org.yakindu.sct.simulation.core.sruntime.impl.CompositeSlotImpl
import org.yakindu.sct.simulation.core.sruntime.impl.ExecutionEventImpl
import org.yakindu.sct.simulation.core.sruntime.impl.ExecutionVariableImpl
import org.yakindu.base.types.TypedElement
import org.yakindu.base.types.Type
import org.yakindu.base.types.ArrayTypeSpecifier

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
class DefaultExecutionContextInitializer implements IExecutionContextInitializer {

	@Inject protected extension IQualifiedNameProvider
	@Inject protected extension ITypeSystem
	@Inject protected extension ITypeSystemInferrer
	@Inject protected extension ITypeValueProvider

	override initialize(ExecutionContext context, ExecutionFlow flow) {
		flow.scopes.forEach[context.slots += transform]
	}
	
	def dispatch ExecutionSlot create composite : createImportSlot() transform(ImportScope scope) {
		// retrieve namespaces from variable names and create corresponding composite slots
		for (Declaration decl : scope.declarations.filter(ImportDeclaration).map[declaration]) {
			val pkg = EcoreUtil2.getContainerOfType(decl, Package)
			if (pkg != null) {
				val namespace = pkg.name
				val declName = decl.name
				val slot = composite.slots.getSlotFor(namespace)
				val declarationSlot = decl.transform
				declarationSlot.setFqName(namespace + "." + declName)
				declarationSlot.setName(declName)
				// only add imported variables/events when they have not yet been imported
				if (!slot.slots.exists[fqName == declarationSlot.fqName]) {
					slot.slots += declarationSlot
				}
			}
			else {
				composite.slots += decl.transform
			}
		}
	}
	
	/**
	 * Create only one root slot for imports independently on how many ImportScopes exist
	 */
	def CompositeSlot create slot : new CompositeSlotImpl() createImportSlot() {
		slot.name = "imports"
	}
	
	def getSlotFor(List<ExecutionSlot> slots, String name) {
		val existingSlot = slots.findFirst[it.name == name]
		if (existingSlot != null && existingSlot instanceof CompositeSlot) {
			existingSlot as CompositeSlot
		}
		else {
			val newSlot = new CompositeSlotImpl()
			newSlot.name = name
			slots += newSlot
			newSlot
		}
	}

	def dispatch ExecutionSlot create new CompositeSlotImpl() transform(InternalScope scope) {
		it.name = "internal"
		scope.declarations.forEach[decl|it.slots += decl.transform]
	}

	def dispatch ExecutionSlot create new CompositeSlotImpl() transform(Scope scope) {
		it.name = "time events"
		scope.declarations.forEach[decl|it.slots += decl.transform]
	}

	def dispatch ExecutionSlot create new CompositeSlotImpl() transform(InterfaceScope scope) {
		if(scope.name != null) it.name = scope.name else it.name = "default"
		scope.declarations.forEach[decl|it.slots += decl.transform]
	}

	def dispatch ExecutionSlot transform(VariableDefinition variable) {
		val slot = variable.createExecutionSlot
		slot.writable = !variable.const
		slot
	}

	def dispatch ExecutionSlot create new ExecutionEventImpl() transform(EventDefinition event) {
		it.name = event.fullyQualifiedName.lastSegment
		it.fqName = event.fullyQualifiedName.toString
		it.type = event.type
		it.value = it.type?.defaultValue
		it.direction = EventDirection.get(event.direction.value)
	}

	def dispatch ExecutionSlot create new ExecutionVariableImpl() transform(OperationDefinition op) {
		it.name = op.fullyQualifiedName.lastSegment
		it.fqName = op.fullyQualifiedName.toString
		it.type = if(op.type != null) op.type else getType(ITypeSystem.VOID)
		it.value = it.type.defaultValue
	}

	def dispatch ExecutionSlot create new ExecutionEventImpl() transform(TimeEvent event) {
		it.name = event.fullyQualifiedName.lastSegment
		it.fqName = event.fullyQualifiedName.toString
		it.type = getType(ITypeSystem.INTEGER)
		it.value = it.type.defaultValue
	}
	
	def protected ExecutionSlot createExecutionSlot(TypedElement element) {
		createExecutionSlot(element.inferType(null), element)
	}
	
	def dispatch ExecutionSlot create slot : new ExecutionVariableImpl() createExecutionSlot(Type type, TypedElement element) {
		slot.name = element.fullyQualifiedName.lastSegment
		slot.fqName = element.fullyQualifiedName.toString
		slot.type = element.inferType(null)
		slot.value = slot.type?.defaultValue
	}
	
	def dispatch ExecutionSlot create slot : new CompositeSlotImpl() createExecutionSlot(ArrayTypeSpecifier type, TypedElement element) {
		slot.name = element.fullyQualifiedName.lastSegment
		slot.fqName = element.fullyQualifiedName.toString
		slot.value = slot.type?.defaultValue
		slot.type = type.type
		val size = type.type.dimensions.get(0).size
		for (var int i=0; i<size; i++) {
			val elemSlot = new ExecutionVariableImpl()
			elemSlot.name = "["+i+"]"
			elemSlot.fqName = slot.name + "." + elemSlot.name
			elemSlot.type = type.type
			elemSlot.value = elemSlot.type?.defaultValue

			slot.slots += elemSlot
		}
	}

}
