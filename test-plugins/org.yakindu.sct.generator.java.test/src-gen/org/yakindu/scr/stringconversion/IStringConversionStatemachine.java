package org.yakindu.scr.stringconversion;
import org.yakindu.scr.IStatemachine;

public interface IStringConversionStatemachine extends IStatemachine {
	public interface SCInterface {
		public void raiseMyEvent(String value);
		public String getWord();
		public void setWord(String value);
		public String getAnotherword();
		public void setAnotherword(String value);
		public long getNumber();
		public void setNumber(long value);
		public boolean getBoolVar();
		public void setBoolVar(boolean value);
		public double getRealVar();
		public void setRealVar(double value);

	}

	public SCInterface getSCInterface();

}
