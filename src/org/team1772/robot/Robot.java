
package org.team1772.robot;

import org.team1772.subsystems.Drive;
import org.team1772.util.XboxControl;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	Drive driveTrain;
	XboxControl xbox;

    public void robotInit() {
    	driveTrain = Drive.getInstance();
    	xbox  = new XboxControl(0);
    }

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	driveTrain.setInputSpeed(xbox.getAxisLeftY(), xbox.getAxisRightY());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
