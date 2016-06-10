/**
 */
package org.yakindu.base.expressions.expressions.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.yakindu.base.expressions.expressions.ArrayInitializationExpression;
import org.yakindu.base.expressions.expressions.Expression;
import org.yakindu.base.expressions.expressions.ExpressionsPackage;
import org.yakindu.base.expressions.expressions.Literal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Initialization Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.expressions.expressions.impl.ArrayInitializationExpressionImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.yakindu.base.expressions.expressions.impl.ArrayInitializationExpressionImpl#getDim <em>Dim</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayInitializationExpressionImpl extends ExpressionImpl implements ArrayInitializationExpression {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Literal> values;

	/**
	 * The cached value of the '{@link #getDim() <em>Dim</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDim()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> dim;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayInitializationExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.ARRAY_INITIALIZATION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Literal> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<Literal>(Literal.class, this, ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getDim() {
		if (dim == null) {
			dim = new EObjectContainmentEList<Expression>(Expression.class, this, ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM);
		}
		return dim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM:
				return ((InternalEList<?>)getDim()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES:
				return getValues();
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM:
				return getDim();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends Literal>)newValue);
				return;
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM:
				getDim().clear();
				getDim().addAll((Collection<? extends Expression>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES:
				getValues().clear();
				return;
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM:
				getDim().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__VALUES:
				return values != null && !values.isEmpty();
			case ExpressionsPackage.ARRAY_INITIALIZATION_EXPRESSION__DIM:
				return dim != null && !dim.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ArrayInitializationExpressionImpl
