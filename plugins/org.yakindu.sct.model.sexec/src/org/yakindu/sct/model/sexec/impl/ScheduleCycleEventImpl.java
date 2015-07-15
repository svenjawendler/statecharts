/**
 */
package org.yakindu.sct.model.sexec.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.yakindu.base.expressions.expressions.Expression;
import org.yakindu.sct.model.sexec.ScheduleCycleEvent;
import org.yakindu.sct.model.sexec.SexecPackage;
import org.yakindu.sct.model.sexec.TimeEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schedule Cycle Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl#getTimeEvent <em>Time Event</em>}</li>
 *   <li>{@link org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl#getTimeValue <em>Time Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScheduleCycleEventImpl extends StepImpl implements ScheduleCycleEvent {
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
	 * The cached value of the '{@link #getTimeValue() <em>Time Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeValue()
	 * @generated
	 * @ordered
	 */
	protected Expression timeValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScheduleCycleEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SexecPackage.Literals.SCHEDULE_CYCLE_EVENT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT, oldTimeEvent, timeEvent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT, oldTimeEvent, timeEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getTimeValue() {
		return timeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeValue(Expression newTimeValue, NotificationChain msgs) {
		Expression oldTimeValue = timeValue;
		timeValue = newTimeValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE, oldTimeValue, newTimeValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeValue(Expression newTimeValue) {
		if (newTimeValue != timeValue) {
			NotificationChain msgs = null;
			if (timeValue != null)
				msgs = ((InternalEObject)timeValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE, null, msgs);
			if (newTimeValue != null)
				msgs = ((InternalEObject)newTimeValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE, null, msgs);
			msgs = basicSetTimeValue(newTimeValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE, newTimeValue, newTimeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE:
				return basicSetTimeValue(null, msgs);
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
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT:
				if (resolve) return getTimeEvent();
				return basicGetTimeEvent();
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE:
				return getTimeValue();
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
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT:
				setTimeEvent((TimeEvent)newValue);
				return;
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE:
				setTimeValue((Expression)newValue);
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
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT:
				setTimeEvent((TimeEvent)null);
				return;
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE:
				setTimeValue((Expression)null);
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
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_EVENT:
				return timeEvent != null;
			case SexecPackage.SCHEDULE_CYCLE_EVENT__TIME_VALUE:
				return timeValue != null;
		}
		return super.eIsSet(featureID);
	}

} //ScheduleCycleEventImpl
