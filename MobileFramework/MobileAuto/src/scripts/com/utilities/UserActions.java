package scripts.com.utilities;

public class UserActions {

	public enum SELECTOR {

		BY_XPATH(0), BY_ID(1), BY_CLASSNAME(2), BY_CSS(4), BY_NAME(5), BY_TAGNAME(6), BY_LINKTEXT(7), BY_ACCESSIBILITYID(8);

		private int selectorNum;

		SELECTOR(int selectorNum) {
			this.selectorNum = selectorNum;
		}

		public int GetSelectorNum() {
			return selectorNum;
		}
	}

	public enum EVENT {
		CLICK(0), CLEAR(1), SUBMIT(2), SEND_KEYS(3);

		private int eventNum;
		private int selectorNum;

		EVENT(int eventNum) {
			this.eventNum = eventNum;
		}

		public int GetEventNum() {
			return eventNum;
		}
	}
}
