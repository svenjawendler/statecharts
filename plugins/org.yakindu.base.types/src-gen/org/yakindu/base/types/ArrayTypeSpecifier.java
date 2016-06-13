/**
 */
package org.yakindu.base.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Type Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.types.ArrayTypeSpecifier#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.types.TypesPackage#getArrayTypeSpecifier()
 * @model
 * @generated
 */
public interface ArrayTypeSpecifier extends TypeSpecifier {

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(ArrayType)
	 * @see org.yakindu.base.types.TypesPackage#getArrayTypeSpecifier_Type()
	 * @model containment="true"
	 * @generated
	 */
	ArrayType getType();

	/**
	 * Sets the value of the '{@link org.yakindu.base.types.ArrayTypeSpecifier#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ArrayType value);

} // ArrayTypeSpecifier
