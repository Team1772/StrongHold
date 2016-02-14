
package org.team1772.robot;

import org.team1772.subsystems.Drive;
import org.team1772.util.XboxControl;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {

	Drive driveTrain;
	XboxControl xbox;
	double l, r;
	boolean botaoapertado;

    public void robotInit() {
    	driveTrain = Drive.getInstance();
    	xbox  = new XboxControl(0);
    	driveTrain.liveWindow();
    }

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    	botaoapertado = false;
    	
    	driveTrain.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//driveTrain.setInputSpeed(xbox.getAxisLeftY(), xbox.getAxisRightY());
    	
    	driveTrain.print();
    	
    	// funcao PID
    	if (xbox.getButtonX()) {
			botaoapertado = true;
		} else if (xbox.getButtonY()) {
			botaoapertado = false;
			driveTrain.start();
			driveTrain.setSetPoint(0, 0);
		}
    	if (botaoapertado) {
    		driveTrain.setSetPoint(100, 100);
		}
    	
    }
    
    public void testInit() {
    	driveTrain.start();
    }
    
    public void testPeriodic() {
    	driveTrain.print();
    }
    
}
