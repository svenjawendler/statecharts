package org.yakindu.scr.stringconcatenation;
import org.yakindu.scr.IStatemachine;

public interface IStringConcatenationStatemachine extends IStatemachine {
	public interface SCInterface {
		public void raiseMyEvent(String value);
		public String getWord();
		public void setWord(String value);
		public String getAnotherword();
		public void setAnotherword(String value);
		public String getLastword();
		public void setLastword(String value);

	}

	public SCInterface getSCInterface();

}
