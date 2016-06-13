/**
 */
package org.yakindu.base.types;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.types.TypeSpecifier#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.types.TypesPackage#getTypeSpecifier()
 * @model
 * @generated
 */
public interface TypeSpecifier extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Arguments</b></em>' reference list.
	 * The list contents are of type {@link org.yakindu.base.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Arguments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Arguments</em>' reference list.
	 * @see org.yakindu.base.types.TypesPackage#getTypeSpecifier_TypeArguments()
	 * @model
	 * @generated
	 */
	EList<Type> getTypeArguments();

} // TypeSpecifier
