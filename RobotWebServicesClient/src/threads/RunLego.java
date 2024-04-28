package threads;

import data.Robot;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class RunLego implements Runnable {
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
   // UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);

    @Override
    public void run() {
        while (Robot.getRun() == 1) {
        	
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Robot.getDirection() == 1) {
            	 leftMotor.setSpeed(Robot.getSpeed());
                 rightMotor.setSpeed(Robot.getSpeed()); // Adjusting right motor speed to correct for drift
                 leftMotor.backward();
                 rightMotor.backward();
                //motorA.backward();
            } else {
            	 leftMotor.setSpeed(Robot.getSpeed());
                 rightMotor.setSpeed(Robot.getSpeed()); // Adjusting right motor speed to correct for drift
                 leftMotor.forward();
                 rightMotor.forward();
                //motorA.forward();
            }
            //motorA.setPower(Robot.getSpeed());
        }
        leftMotor.stop(true);
        rightMotor.stop(true);
    }
}