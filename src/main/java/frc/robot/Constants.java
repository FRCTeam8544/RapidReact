// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj.PneumaticsModuleType;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // RIO Ports
    public static final int DIO_0 = 0;
    public static final int DIO_1 = 1;
    public static final int DIO_2 = 2;
    public static final int DIO_3 = 3;
    public static final int DIO_4 = 4;
    public static final int DIO_5 = 5;
    public static final int DIO_6 = 6;
    public static final int DIO_7 = 7;
    public static final int DIO_8 = 8;
    public static final int DIO_9 = 9;

    // OI
    //  [B]utton [B]ox
    //    [B]uttons
    public static final int BB_B_A = 1;
    public static final int BB_B_B = 2;
    public static final int BB_B_X = 3;
    public static final int BB_B_Y = 4;
    public static final int BB_B_LB = 5;
    public static final int BB_B_RB = 6;
    public static final int BB_B_SHARE = 7;
    public static final int BB_B_OPTIONS = 8;
    public static final int BB_B_SL = 9;
    public static final int BB_B_SR = 10;
    //     [A]xes
    public static final int BB_A_LT = 2;
    public static final int BB_A_RT = 3;
    //     [POV] aka dpad
    public static final int BB_POV = 0;


//Drive Train 
    //speed controller motortype and id 
    public static final int DRIVETRAIN_DMOTOR1_ID = 1; 
    public static final MotorType DRIVETRAIN_DMOTOR1_MOTORTYPE = MotorType.kBrushless;

    
    public static final int DRIVETRAIN_DMOTOR2_ID = 2;
    public static final MotorType DRIVETRAIN_DMOTOR2_MOTORTYPE = MotorType.kBrushless;

    public static final int DRIVETRAIN_DMOTOR3_ID = 3; 
    public static final MotorType DRIVETRAIN_DMOTOR3_MOTORTYPE = MotorType.kBrushless;

    public static final int DRIVETRAIN_DMOTOR4_ID = 4;
    public static final MotorType DRIVETRAIN_DMOTOR4_MOTORTYPE = MotorType.kBrushless;

    public static final int SHOOTER_MOTOR1_ID = 5; 
    public static final MotorType SHOOTER_MOTOR1_MOTORTYPE = MotorType.kBrushless;

    public static final int SHOOTER_MOTOR2_ID = 6; 
    public static final MotorType SHOOTER_MOTOR2_MOTORTYPE = MotorType.kBrushless;

    public static final int INTAKE_MOTOR_ID = 7;
    public static final int SHOOTER_FEED_MOTOR_ID = 8;
    public static final int CLIMB_MOTOR_ID = 9;

    //lead speed controller inversion settings ^values have not been tested
    public static final boolean DRIVETRAIN_DMOTOR1_INVERSION = true;
    public static final boolean DRIVEMOTOR_DMOTOR2_INVERSION = false;

    public static final boolean SHOOTER_MOTOR2_INVERSION = false;
    
    //encoder type and CPR &define CPR
    public static final Type DRIVETRAIN_ENCODERDM1_ENCODERTYPE = Type.kHallSensor;
    public static final int DRIVETRAIN_ENCODERDM1_CPR = 0;

    public static final Type DRIVETRAIN_ENCODERDM2_ENCODERTYPE = Type.kHallSensor;
    public static final int DRIVETRAIN_ENCODERDM2_CPR = 0;

    public static final Type DRIVETRAIN_ENCODERDM3_ENCODERTYPE = Type.kHallSensor;
    public static final int DRIVETRAIN_ENCODERDM3_CPR = 0;

    public static final Type DRIVETRAIN_ENCODERDM4_ENCODERTYPE = Type.kHallSensor;
    public static final int DRIVETRAIN_ENCODERDM4_CPR = 0;

    // Limit switches
    public static final int SHOOTER_FEEDER_LIMITSWITCH = DIO_0;
    public static final int INTAKE_HOPPER_LIMITSWITCH = DIO_1;

    //Joystick port #s
    public static final int ROBOTCONTAINER_JOYSTICK1_PORTNUMBER = 0;
    public static final int ROBOTCONTAINER_JOYSTICK2_PORTNUMBER = 1;
    public static final int ROBOTCONTAINER_HIDCONTROLLER_PORTNUMBER = 2;
    public static final int ROBOTCONTAINER_BUTTONBOX_PORTNUMBER = 3;



    //non-driving controls
    //contr0ller
    public static final int ROBOTCONTAINER_BUTTON_NUMBER_B = 3;
	public static final int ROBOTCONTAINER_BUTTON_NUMBER_X = 1;
	public static final int ROBOTCONTAINER_BUTTON_NUMBER_Y = 4;
    public static final int ROBOTCONTAINER_BUTTON_NUMBER_A = 2;
    public static final int ROBOTCONTAINER_BUTTON_SHOOTER_SPEED1 = ROBOTCONTAINER_BUTTON_NUMBER_X;
    public static final int ROBOTCONTAINER_BUTTON_SHOOTER_SPEED2 = ROBOTCONTAINER_BUTTON_NUMBER_A;
    public static final int ROBOTCONTAINER_BUTTON_SHOOTER_SPEED3 = ROBOTCONTAINER_BUTTON_NUMBER_Y;
    public static final int ROBOTCONTAINER_BUTTON_PNEUMATIC_TOGGLE = ROBOTCONTAINER_BUTTON_NUMBER_B;
    public static final int ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_IN = 7;
    public static final int ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_OUT = 5;
    public static final int ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_IN = 8;
    public static final int ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_OUT = 6;
    public static final int ROBOTCONTAINER_CONTROLLER_CLIMB_ARM_EXTEND = 10;
    public static final int ROBOTCONTAINER_CONTROLLER_CLIMB_ARM_RETRACT = 9;
    public static final int ROBOTCONTAINER_CONTROLLER_OVERRIDE_FEEDER = 12;

    // Button box
    public static final int BUTTON_INTAKE_LOAD_BALL1 = BB_B_X; // Ball 1 (position 1) is the primary one, i.e. in the shooter about to be shot
    public static final int BUTTON_INTAKE_LOAD_BALL2 = BB_B_A; // Ball 2 (position 2) is the secondary one, i.e. in the hopper waiting to be loaded
    public static final int BUTTON_INTAKE_MANUAL_IN = BB_B_LB; // Manually extend the intake
    public static final int BUTTON_INTAKE_MANUAL_OUT = BB_B_RB; // Manually retract the intake
    public static final int BUTTON_INTAKE_MANUAL_ROLL_IN = BB_B_Y; // Manually activate the intake rollers inward
    public static final int BUTTON_INTAKE_MANUAL_ROLL_OUT = BB_B_B; // Manually activate the intake rollers outward

    //joystick
    public static final int ROBOTCONTAINER_JOYSTICK_BASE_MODESWITCH_ID = 7;
  



 

    //pneumatics
    public static final PneumaticsModuleType INTAKE_PNEUMATICS_TYPE = PneumaticsModuleType.REVPH;
    public static final int PNEUMATICS_PCM_ID = 10;
    public static final int INTAKE_PNEUMATICS_FORWARD = 1;
    public static final int INTAKE_PNEUMATICS_REVERSE = 0;
}


