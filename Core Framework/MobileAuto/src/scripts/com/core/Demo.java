package scripts.com.core;

import scripts.com.utilities.UserActions;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(UserActions.EVENT.CLICK.name()+" ----- "+UserActions.EVENT.CLEAR.name());
		System.out.println(UserActions.EVENT.CLICK.GetEventNum()+" ----- "+UserActions.EVENT.CLEAR.GetEventNum());
		
	}

}
