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
 *   <li>{@link org.yakindu.base.types.ArrayTypeSpecifier#getDimensions <em>Dimensions</em>}</li>
 * </ul>
 *
 * @see org.yakindu.base.types.TypesPackage#getArrayTypeSpecifier()
 * @model
 * @generated
 */
public interface ArrayTypeSpecifier extends TypeSpecifier, Type {
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
	 * @see org.yakindu.base.types.TypesPackage#getArrayTypeSpecifier_Dimensions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ArrayDimension> getDimensions();

} // ArrayTypeSpecifier
