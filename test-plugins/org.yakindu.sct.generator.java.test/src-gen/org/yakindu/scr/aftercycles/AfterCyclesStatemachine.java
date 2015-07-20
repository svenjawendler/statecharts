package org.yakindu.scr.aftercycles;
import org.yakindu.scr.ITimer;

public class AfterCyclesStatemachine implements IAfterCyclesStatemachine {
	private final boolean[] timeEvents = new boolean[4];

	private final class SCInterfaceImpl implements SCInterface {

		private long myInt;
		public long getMyInt() {
			return myInt;
		}

		public void setMyInt(long value) {
			this.myInt = value;
		}

		private long anotherInt;
		public long getAnotherInt() {
			return anotherInt;
		}

		public void setAnotherInt(long value) {
			this.anotherInt = value;
		}

		private boolean mybool;
		public boolean getMybool() {
			return mybool;
		}

		public void setMybool(boolean value) {
			this.mybool = value;
		}

	}

	private SCInterfaceImpl sCInterface;

	public enum State {
		main_region_A, main_region_B, $NullState$
	};

	private final State[] stateVector = new State[1];

	private int nextStateIndex;

	private ITimer timer;

	public AfterCyclesStatemachine() {

		sCInterface = new SCInterfaceImpl();
	}

	public void init() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}

		clearEvents();
		clearOutEvents();

		sCInterface.myInt = 0;

		sCInterface.anotherInt = 0;

		sCInterface.mybool = false;
	}

	public void enter() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl' not defined

		entryAction();

		enterSequence_main_region_default();
	}

	public void exit() {
		exitSequence_main_region();

		exitAction();
	}

	/**
	 * @see IStatemachine#isActive()
	 */
	@Override
	public boolean isActive() {

		return stateVector[0] != State.$NullState$;
	}

	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	 * @see IStatemachine#isFinal() 
	 */
	@Override
	public boolean isFinal() {
		return false;
	}

	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {

		for (int i = 0; i < timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}

	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}

	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
		switch (state) {
			case main_region_A :
				return stateVector[0] == State.main_region_A;
			case main_region_B :
				return stateVector[0] == State.main_region_B;
			default :
				return false;
		}
	}

	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correct
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}

	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}

	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}

	public SCInterface getSCInterface() {
		return sCInterface;
	}

	public long getMyInt() {
		return sCInterface.getMyInt();
	}

	public void setMyInt(long value) {
		sCInterface.setMyInt(value);
	}
	public long getAnotherInt() {
		return sCInterface.getAnotherInt();
	}

	public void setAnotherInt(long value) {
		sCInterface.setAnotherInt(value);
	}
	public boolean getMybool() {
		return sCInterface.getMybool();
	}

	public void setMybool(boolean value) {
		sCInterface.setMybool(value);
	}

	private boolean check__lr0() {
		return timeEvents[3];
	}

	private boolean check_main_region_A_tr0_tr0() {
		return timeEvents[0];
	}

	private boolean check_main_region_A_lr0_lr0() {
		return timeEvents[1];
	}

	private boolean check_main_region_B_lr0_lr0() {
		return timeEvents[2];
	}

	private void effect__lr0() {
		sCInterface.anotherInt += 5;
	}

	private void effect_main_region_A_tr0() {
		exitSequence_main_region_A();

		enterSequence_main_region_B_default();
	}

	private void effect_main_region_A_lr0_lr0() {
		sCInterface.mybool = true;
	}

	private void effect_main_region_B_lr0_lr0() {
		sCInterface.myInt += 1;
	}

	/* Entry action for statechart 'AfterCycles'. */
	private void entryAction() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl' not defined
	}

	/* Entry action for state 'A'. */
	private void entryAction_main_region_A() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl' not defined

		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl' not defined
	}

	/* Entry action for state 'B'. */
	private void entryAction_main_region_B() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.ScheduleCycleEventImpl' not defined
	}

	/* Exit action for state 'AfterCycles'. */
	private void exitAction() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.UnscheduleCycleEventImpl' not defined
	}

	/* Exit action for state 'A'. */
	private void exitAction_main_region_A() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.UnscheduleCycleEventImpl' not defined

		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.UnscheduleCycleEventImpl' not defined
	}

	/* Exit action for state 'B'. */
	private void exitAction_main_region_B() {
		//ERROR: FlowCode for Step 'org.yakindu.sct.model.sexec.impl.UnscheduleCycleEventImpl' not defined
	}

	/* 'default' enter sequence for state A */
	private void enterSequence_main_region_A_default() {
		entryAction_main_region_A();

		nextStateIndex = 0;
		stateVector[0] = State.main_region_A;
	}

	/* 'default' enter sequence for state B */
	private void enterSequence_main_region_B_default() {
		entryAction_main_region_B();

		nextStateIndex = 0;
		stateVector[0] = State.main_region_B;
	}

	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}

	/* Default exit sequence for state A */
	private void exitSequence_main_region_A() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;

		exitAction_main_region_A();
	}

	/* Default exit sequence for state B */
	private void exitSequence_main_region_B() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;

		exitAction_main_region_B();
	}

	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
			case main_region_A :
				exitSequence_main_region_A();
				break;

			case main_region_B :
				exitSequence_main_region_B();
				break;

			default :
				break;
		}
	}

	/* The reactions of state A. */
	private void react_main_region_A() {
		if (check__lr0()) {
			effect__lr0();
		}

		if (check_main_region_A_tr0_tr0()) {
			effect_main_region_A_tr0();
		} else {
			if (check_main_region_A_lr0_lr0()) {
				effect_main_region_A_lr0_lr0();
			}
		}
	}

	/* The reactions of state B. */
	private void react_main_region_B() {
		if (check__lr0()) {
			effect__lr0();
		}

		if (check_main_region_B_lr0_lr0()) {
			effect_main_region_B_lr0_lr0();
		}
	}

	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_A_default();
	}

	public void runCycle() {

		clearOutEvents();

		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {

			switch (stateVector[nextStateIndex]) {
				case main_region_A :
					react_main_region_A();
					break;
				case main_region_B :
					react_main_region_B();
					break;
				default :
					// $NullState$
			}
		}

		clearEvents();
	}
}
