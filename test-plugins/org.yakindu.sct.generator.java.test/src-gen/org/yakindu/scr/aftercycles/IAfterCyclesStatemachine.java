package org.yakindu.scr.aftercycles;
import org.yakindu.scr.IStatemachine;
import org.yakindu.scr.ITimerCallback;

public interface IAfterCyclesStatemachine extends ITimerCallback, IStatemachine {

	public interface SCInterface {
		public long getMyInt();
		public void setMyInt(long value);
		public long getAnotherInt();
		public void setAnotherInt(long value);
		public boolean getMybool();
		public void setMybool(boolean value);

	}

	public SCInterface getSCInterface();

}
