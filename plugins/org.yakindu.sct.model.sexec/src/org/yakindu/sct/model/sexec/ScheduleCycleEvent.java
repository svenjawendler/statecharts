/**
 */
package org.yakindu.sct.model.sexec;

import org.yakindu.base.expressions.expressions.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schedule Cycle Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.yakindu.sct.model.sexec.ScheduleCycleEvent#getTimeEvent <em>Time Event</em>}</li>
 *   <li>{@link org.yakindu.sct.model.sexec.ScheduleCycleEvent#getTimeValue <em>Time Value</em>}</li>
 * </ul>
 *
 * @see org.yakindu.sct.model.sexec.SexecPackage#getScheduleCycleEvent()
 * @model
 * @generated
 */
public interface ScheduleCycleEvent extends Step {
	/**
	 * Returns the value of the '<em><b>Time Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Event</em>' reference.
	 * @see #setTimeEvent(TimeEvent)
	 * @see org.yakindu.sct.model.sexec.SexecPackage#getScheduleCycleEvent_TimeEvent()
	 * @model
	 * @generated
	 */
	TimeEvent getTimeEvent();

	/**
	 * Sets the value of the '{@link org.yakindu.sct.model.sexec.ScheduleCycleEvent#getTimeEvent <em>Time Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Event</em>' reference.
	 * @see #getTimeEvent()
	 * @generated
	 */
	void setTimeEvent(TimeEvent value);

	/**
	 * Returns the value of the '<em><b>Time Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Value</em>' containment reference.
	 * @see #setTimeValue(Expression)
	 * @see org.yakindu.sct.model.sexec.SexecPackage#getScheduleCycleEvent_TimeValue()
	 * @model containment="true"
	 * @generated
	 */
	Expression getTimeValue();

	/**
	 * Sets the value of the '{@link org.yakindu.sct.model.sexec.ScheduleCycleEvent#getTimeValue <em>Time Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Value</em>' containment reference.
	 * @see #getTimeValue()
	 * @generated
	 */
	void setTimeValue(Expression value);

} // ScheduleCycleEvent
