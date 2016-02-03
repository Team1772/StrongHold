package org.team1772.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;

public class Drive {
	// Components
	private Victor leftMotor;
	private Victor rightMotor;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private PIDController leftPID;
    private PIDController rightPID;
    
    public Drive (int pwmLM, int pwmRM) {
    	leftMotor    = new Victor(pwmLM);
    	rightMotor   = new Victor(pwmRM);
    }
    
    public Drive (int pwmLM, int pwmRM, int pwmLEA, int pwmLEB, int pwmREA, int pwmREB) {

    	// Set PWMs
    	leftMotor    = new Victor(pwmLM);
    	rightMotor   = new Victor(pwmRM);
    	leftEncoder  = new Encoder(pwmLEA, pwmLEB, false, Encoder.EncodingType.k4X);
    	rightEncoder = new Encoder(pwmREA, pwmREB, false, Encoder.EncodingType.k4X);
    	
    	// To-do Wheel Size Inches * Math.PI / Encoder Counts Per Revolution
    	leftEncoder.setDistancePerPulse(1);
    	rightEncoder.setDistancePerPulse(1);
    	
    	// Start Encoders
    	leftEncoder.reset();
    	rightEncoder.reset();

    	// Set PIDs type
    	leftEncoder.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
        rightEncoder.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
        
        // Set PIDs Controller
        leftPID  = new PIDController(0, 0, 0, leftEncoder, leftMotor);
        rightPID = new PIDController(0, 0, 0, rightEncoder, rightMotor);
        
        // Start PIDs
        leftPID.enable();
        rightPID.enable();
        
        //These will change, and you should change them based on how far
        //your robot will be driving.
        //For this example, we set them at 100 inches.
        // leftPID.setInputRange(0, 100);
        // rightPID.setInputRange(0, 100);

    }
    
    // Set a distance in inches
    public void setSetPoint(double leftInches, double rightInches) {
    	leftPID.setSetpoint(leftInches);
    	rightPID.setSetpoint(rightInches);
    }
    
    // Returns the current difference of the input from the setpoint
    public double getLeftError() {
    	return leftPID.getError();
    }
    public double getRightError() {
    	return rightPID.getError();
    }
    
    // Teleop inputs
    public void setInputSpeed(double leftSpeed, double rightSpeed) {
    	rightMotor.set(rightSpeed);
    	leftMotor.set(leftSpeed);
    }
    
    // Free components
    public void freeComponents() {
    	leftEncoder.free();
    	rightEncoder.free();
    	leftPID.free();
    	rightPID.free();
    }
	
	// Singleton
	private static Drive instance;
	public static Drive getInstance() {
		if(instance == null) {
			// To-do set pwm values
			instance = new Drive(1, 2, 3, 4, 5, 6);
		}
		return instance;
	}
}
