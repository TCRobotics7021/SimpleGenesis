/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private TalonSRX motor1 = new TalonSRX(4);
  private TalonSRX motor2 = new TalonSRX(1);
  private TalonSRX motor3 = new TalonSRX(2);
  private TalonSRX motor4 = new TalonSRX(3);

  Joystick Gamepad = new Joystick(0);

  int speedlimiter = 1;
  double speedlimiteralt = 1;
  double leftspeed;
  double rightspeed;


  private SpeedController liftmotor = new Talon(1);

private Joystick gamepad = new Joystick(0);

double speed = 0;

private DigitalInput in2 = new DigitalInput(2);
private DigitalInput in3 = new DigitalInput(3);
private DigitalInput in4 = new DigitalInput(4);

boolean lowerlimit;
boolean upperlimit_1;
boolean upperlimit_2;






  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.addDefault("Default Auto", kDefaultAuto);
    m_chooser.addObject("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
      leftspeed = Gamepad.getRawAxis(1)*speedlimiteralt;
      rightspeed = Gamepad.getRawAxis(3)*-speedlimiteralt;


      motor1.set(ControlMode.PercentOutput,leftspeed);
      motor2.set(ControlMode.PercentOutput,leftspeed);


      motor3.set(ControlMode.PercentOutput,rightspeed);
      motor4.set(ControlMode.PercentOutput,rightspeed);

      lowerlimit = in3.get();
      upperlimit_1 = in2.get();
      upperlimit_2 = in4.get();
  
      if(gamepad.getRawButton(1)) {
         speed = .5;
      } else if(gamepad.getRawButton(2)) {
        speed = -1;
      } else {
        speed = 0;
      }
      
      if (lowerlimit == false && speed > 0) {
        speed = 0;
      } else if (upperlimit_1 == false && upperlimit_2 == false && speed < 0) {
        speed = 0;
      }
      liftspeed(speed);
    }

    public void liftspeed(double speed) {
      liftmotor.set(speed);
    }
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
