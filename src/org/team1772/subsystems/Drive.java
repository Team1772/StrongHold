package org.team1772.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	leftEncoder  = new Encoder(pwmLEA, pwmLEB, true, Encoder.EncodingType.k4X);
    	rightEncoder = new Encoder(pwmREA, pwmREB, false, Encoder.EncodingType.k4X);
    	
    	// Wheel Size Inches * Math.PI / Encoder Counts Per Revolution
    	rightEncoder.setDistancePerPulse(24.25 / 4844);
    	leftEncoder.setDistancePerPulse(24.25 / 4842);

    	// Set PIDs type
    	leftEncoder.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
        rightEncoder.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
        
        // Set PIDs Controller
        leftPID  = new PIDController(0.035, 0, 0, leftEncoder, leftMotor);
        rightPID = new PIDController(0.035, 0, 0, rightEncoder, rightMotor);
        
    }
    
    // Set a distance in inches
    public void setSetPoint(double leftInches, double rightInches) {
    	leftPID.setSetpoint(leftInches);
    	rightPID.setSetpoint(rightInches);
    	
    	leftPID.setOutputRange(0, 0.28);
    	rightPID.setOutputRange(0, 0.28);
    }
    
    // Teleop inputs
    public void setInputSpeed(double leftSpeed, double rightSpeed) {
    	rightMotor.set(rightSpeed);
    	leftMotor.set(leftSpeed);
    }
    
    public void start() {
    	// Start Encoders
    	leftEncoder.reset();
    	rightEncoder.reset();
        // Start PIDs
        leftPID.enable();
        rightPID.enable();
    }
    
    // Free components
    public void freeComponents() {
    	leftEncoder.free();
    	rightEncoder.free();
    	leftPID.free();
    	rightPID.free();
    }
    
    public void print() {
    	//System.out.println("encLeft: " + leftEncoder.get());
    	System.out.println("encLeft inches: " + leftEncoder.getDistance());
    	//System.out.println("encRight: " + rightEncoder.get());
    	System.out.println("encRight inches: " + rightEncoder.getDistance());
    }
    
    public void liveWindow() {
    	LiveWindow.addSensor("PID", "PID right", rightPID);
    	LiveWindow.addSensor("PID", "PID left", leftPID);
    }
	
	// Singleton
	private static Drive instance;
	public static Drive getInstance() {
		if(instance == null) {
			// To-do set pwm values LEA 0, LEB 1, REA 2, REB 3 
			instance = new Drive(1, 0, 0, 1, 2, 3);
		}
		return instance;
	}
}
