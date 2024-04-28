package app;

import threads.*;

public class LegoApp{

	public static void main (String[] args) {
		RunLego kone1 = new RunLego();
		ReadData kone2 = new ReadData();
		
		System.out.println("Run in threads");
		
		Thread sale1 = new Thread(kone1);
		Thread sale2 = new Thread(kone2);
		sale1.start();
		sale2.start();
	
	}
}
