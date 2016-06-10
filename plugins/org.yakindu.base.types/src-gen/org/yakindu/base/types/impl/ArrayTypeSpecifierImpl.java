/**
 */
package org.yakindu.base.types.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.yakindu.base.base.BasePackage;
import org.yakindu.base.base.NamedElement;
import org.yakindu.base.types.ArrayDimension;
import org.yakindu.base.types.ArrayTypeSpecifier;
import org.yakindu.base.types.PackageMember;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.TypeConstraint;
import org.yakindu.base.types.TypesPackage;
import org.yakindu.base.types.TypesUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Type Specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#isVisible <em>Visible</em>}</li>
 *   <li>{@link org.yakindu.base.types.impl.ArrayTypeSpecifierImpl#getDimensions <em>Dimensions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayTypeSpecifierImpl extends TypeSpecifierImpl implements ArrayTypeSpecifier {
	private static final String ARRAY_BRACKETS = "[]";
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraint()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeConstraint> constraint;
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;
	/**
	 * The default value of the '{@link #isVisible() <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VISIBLE_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isVisible() <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean visible = VISIBLE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ArrayDimension> dimensions;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayTypeSpecifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.ARRAY_TYPE_SPECIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ARRAY_TYPE_SPECIFIER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getId() {
		if (eContainer instanceof NamedElement) {
			String containerName = TypesUtil.computeQID((NamedElement)eContainer());
			return containerName + "_" + type.getId() + ARRAY_BRACKETS;
		}
		return type.getId() + ARRAY_BRACKETS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeConstraint> getConstraint() {
		if (constraint == null) {
			constraint = new EObjectContainmentEList<TypeConstraint>(TypeConstraint.class, this, TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT);
		}
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisible(boolean newVisible) {
		boolean oldVisible = visible;
		visible = newVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE, oldVisible, visible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ArrayDimension> getDimensions() {
		if (dimensions == null) {
			dimensions = new EObjectContainmentEList<ArrayDimension>(ArrayDimension.class, this, TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS);
		}
		return dimensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Type getOriginType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT:
				return ((InternalEList<?>)getConstraint()).basicRemove(otherEnd, msgs);
			case TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS:
				return ((InternalEList<?>)getDimensions()).basicRemove(otherEnd, msgs);
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
			case TypesPackage.ARRAY_TYPE_SPECIFIER__NAME:
				return getName();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ID:
				return getId();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT:
				return getConstraint();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT:
				return isAbstract();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE:
				return isVisible();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS:
				return getDimensions();
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
			case TypesPackage.ARRAY_TYPE_SPECIFIER__NAME:
				setName((String)newValue);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT:
				getConstraint().clear();
				getConstraint().addAll((Collection<? extends TypeConstraint>)newValue);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE:
				setVisible((Boolean)newValue);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS:
				getDimensions().clear();
				getDimensions().addAll((Collection<? extends ArrayDimension>)newValue);
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
			case TypesPackage.ARRAY_TYPE_SPECIFIER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT:
				getConstraint().clear();
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE:
				setVisible(VISIBLE_EDEFAULT);
				return;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS:
				getDimensions().clear();
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
			case TypesPackage.ARRAY_TYPE_SPECIFIER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT:
				return constraint != null && !constraint.isEmpty();
			case TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE:
				return visible != VISIBLE_EDEFAULT;
			case TypesPackage.ARRAY_TYPE_SPECIFIER__DIMENSIONS:
				return dimensions != null && !dimensions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case TypesPackage.ARRAY_TYPE_SPECIFIER__NAME: return BasePackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == PackageMember.class) {
			switch (derivedFeatureID) {
				case TypesPackage.ARRAY_TYPE_SPECIFIER__ID: return TypesPackage.PACKAGE_MEMBER__ID;
				default: return -1;
			}
		}
		if (baseClass == Type.class) {
			switch (derivedFeatureID) {
				case TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT: return TypesPackage.TYPE__CONSTRAINT;
				case TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT: return TypesPackage.TYPE__ABSTRACT;
				case TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE: return TypesPackage.TYPE__VISIBLE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case BasePackage.NAMED_ELEMENT__NAME: return TypesPackage.ARRAY_TYPE_SPECIFIER__NAME;
				default: return -1;
			}
		}
		if (baseClass == PackageMember.class) {
			switch (baseFeatureID) {
				case TypesPackage.PACKAGE_MEMBER__ID: return TypesPackage.ARRAY_TYPE_SPECIFIER__ID;
				default: return -1;
			}
		}
		if (baseClass == Type.class) {
			switch (baseFeatureID) {
				case TypesPackage.TYPE__CONSTRAINT: return TypesPackage.ARRAY_TYPE_SPECIFIER__CONSTRAINT;
				case TypesPackage.TYPE__ABSTRACT: return TypesPackage.ARRAY_TYPE_SPECIFIER__ABSTRACT;
				case TypesPackage.TYPE__VISIBLE: return TypesPackage.ARRAY_TYPE_SPECIFIER__VISIBLE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return type.toString() + ARRAY_BRACKETS;
	}

} //ArrayTypeSpecifierImpl
