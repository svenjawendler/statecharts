/**
 */
package org.yakindu.base.expressions.expressions;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Initialization Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.expressions.expressions.ArrayInitializationExpression#getValues <em>Values</em>}</li>
 *   <li>{@link org.yakindu.base.expressions.expressions.ArrayInitializationExpression#getDim <em>Dim</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getArrayInitializationExpression()
 * @model
 * @generated
 */
public interface ArrayInitializationExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.yakindu.base.expressions.expressions.Literal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getArrayInitializationExpression_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<Literal> getValues();

	/**
	 * Returns the value of the '<em><b>Dim</b></em>' containment reference list.
	 * The list contents are of type {@link org.yakindu.base.expressions.expressions.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dim</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dim</em>' containment reference list.
	 * @see org.yakindu.base.expressions.expressions.ExpressionsPackage#getArrayInitializationExpression_Dim()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getDim();

} // ArrayInitializationExpression
