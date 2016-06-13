/**
 */
package org.yakindu.base.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.types.ArrayType#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.yakindu.base.types.ArrayType#getBaseType <em>Base Type</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.types.TypesPackage#getArrayType()
 * @model
 * @generated
 */
public interface ArrayType extends Type {
	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.yakindu.base.types.ArrayDimension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimensions</em>' containment reference list.
	 * @see org.yakindu.base.types.TypesPackage#getArrayType_Dimensions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ArrayDimension> getDimensions();

	/**
	 * Returns the value of the '<em><b>Base Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Type</em>' reference.
	 * @see #setBaseType(Type)
	 * @see org.yakindu.base.types.TypesPackage#getArrayType_BaseType()
	 * @model
	 * @generated
	 */
	Type getBaseType();

	/**
	 * Sets the value of the '{@link org.yakindu.base.types.ArrayType#getBaseType <em>Base Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Type</em>' reference.
	 * @see #getBaseType()
	 * @generated
	 */
	void setBaseType(Type value);

} // ArrayType
