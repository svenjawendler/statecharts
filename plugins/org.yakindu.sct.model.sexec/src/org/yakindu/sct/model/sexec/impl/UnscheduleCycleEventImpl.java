/**
 */
package org.yakindu.sct.model.sexec.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.yakindu.sct.model.sexec.SexecPackage;
import org.yakindu.sct.model.sexec.TimeEvent;
import org.yakindu.sct.model.sexec.UnscheduleCycleEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unschedule Cycle Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.sct.model.sexec.impl.UnscheduleCycleEventImpl#getTimeEvent <em>Time Event</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnscheduleCycleEventImpl extends StepImpl implements UnscheduleCycleEvent {
	/**
	 * The cached value of the '{@link #getTimeEvent() <em>Time Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeEvent()
	 * @generated
	 * @ordered
	 */
	protected TimeEvent timeEvent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnscheduleCycleEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SexecPackage.Literals.UNSCHEDULE_CYCLE_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEvent getTimeEvent() {
		if (timeEvent != null && timeEvent.eIsProxy()) {
			InternalEObject oldTimeEvent = (InternalEObject)timeEvent;
			timeEvent = (TimeEvent)eResolveProxy(oldTimeEvent);
			if (timeEvent != oldTimeEvent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT, oldTimeEvent, timeEvent));
			}
		}
		return timeEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEvent basicGetTimeEvent() {
		return timeEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeEvent(TimeEvent newTimeEvent) {
		TimeEvent oldTimeEvent = timeEvent;
		timeEvent = newTimeEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT, oldTimeEvent, timeEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT:
				if (resolve) return getTimeEvent();
				return basicGetTimeEvent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT:
				setTimeEvent((TimeEvent)newValue);
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
			case SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT:
				setTimeEvent((TimeEvent)null);
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
			case SexecPackage.UNSCHEDULE_CYCLE_EVENT__TIME_EVENT:
				return timeEvent != null;
		}
		return super.eIsSet(featureID);
	}

} //UnscheduleCycleEventImpl
