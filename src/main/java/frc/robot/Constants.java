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

    //Joystick port #s
    public static final int ROBOTCONTAINER_JOYSTICK1_PORTNUMBER = 0;
    public static final int ROBOTCONTAINER_JOYSTICK2_PORTNUMBER = 1;
    public static final int ROBOTCONTAINER_HIDCONTROLLER_PORTNUMBER = 2;



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

    //joystick
    public static final int ROBOTCONTAINER_JOYSTICK_BASE_MODESWITCH_ID = 7;
  



 

    //pneumatics
    public static final PneumaticsModuleType INTAKE_PNEUMATICS_TYPE = PneumaticsModuleType.REVPH;
    public static final int PNEUMATICS_PCM_ID = 10;
    public static final int INTAKE_PNEUMATICS_FORWARD = 1;
    public static final int INTAKE_PNEUMATICS_REVERSE = 0;
}


