package frc.robot.subsystems;

public class IntakeSTM {
    // State
    private IntakeSTM_State state;
    private Intake intake;
    // Inputs
    private boolean feeder_full;
    private boolean hopper_full;
    private boolean ball1_button;
    private boolean ball2_button;
    private boolean manual_intake_out;
    private boolean manual_intake_in;
    private boolean manual_intake_roll_in;
    private boolean manual_intake_roll_out;

    public IntakeSTM(Intake intake, boolean feeder_full, boolean hopper_full) {
        this.state = IntakeSTM_State.UNINITIALIZED;
        this.intake = intake;
        this.feeder_full = feeder_full;
        this.hopper_full = hopper_full;
        this.ball1_button = false;
        this.ball2_button = false;
        this.manual_intake_out = false;
        this.manual_intake_in = false;
        this.manual_intake_roll_in = false;
        this.manual_intake_roll_out = false;
    }

    public void update_ball1_button(boolean ball1_button) {
        this.ball1_button = ball1_button;
    }

    public void update_ball2_button(boolean ball2_button) {
        this.ball2_button = ball2_button;
    }

    public void update_manual_intake_out(boolean pressed) {
        this.manual_intake_out = pressed;
    }

    public void update_manual_intake_in(boolean pressed) {
        this.manual_intake_in = pressed;
    }

    public void update_manual_intake_roll_in(boolean pressed) {
        this.manual_intake_roll_in = pressed;
    }

    public void update_manual_intake_roll_out(boolean pressed) {
        this.manual_intake_roll_out = pressed;
    }

    protected void tick(boolean feeder_full, boolean hopper_full) {
        // Update states (excluding buttons which get updated as they occur)
        this.feeder_full = feeder_full;
        this.hopper_full = hopper_full;

        switch(this.state) {
            case UNINITIALIZED:
                if (!this.hopper_full) {
                    this.state = IntakeSTM_State.EMPTY_HOPPER;
                    this.intake.stopMotor();
                    this.intake.setIntakeIn();
                } else if (this.hopper_full) {
                    this.state = IntakeSTM_State.HOPPER_LOADED;
                    this.intake.stopMotor();
                    this.intake.setIntakeIn();
                } else if (this.manual_intake_in
                    || this.manual_intake_out
                    || this.manual_intake_roll_in
                    || this.manual_intake_roll_out) {
                    this.state = IntakeSTM_State.MANUAL_OVERRIDE;
                }
                break;
            case EMPTY_HOPPER:
                if (this.hopper_full) {
                    this.state = IntakeSTM_State.HOPPER_LOADED;
                    this.intake.stopMotor();
                    this.intake.setIntakeIn();
                } else if (((!this.feeder_full && this.ball1_button)
                    || this.ball2_button)
                    && !hopper_full) {
                        this.state = IntakeSTM_State.LOOKING_FOR_BALL;
                        this.intake.setIntakeOut();
                        this.intake.runMotorForward(); // In
                } else if (this.manual_intake_in
                    || this.manual_intake_out
                    || this.manual_intake_roll_in
                    || this.manual_intake_roll_out) {
                    this.state = IntakeSTM_State.MANUAL_OVERRIDE;
                }
                break;
            case HOPPER_LOADED:
                if (this.ball1_button && !this.feeder_full) {
                    this.state = IntakeSTM_State.INTAKE_TO_FEEDER;
                    this.intake.setIntakeIn();
                    this.intake.runMotorForward();
                } else if (this.manual_intake_in
                    || this.manual_intake_out
                    || this.manual_intake_roll_in
                    || this.manual_intake_roll_out) {
                    this.state = IntakeSTM_State.MANUAL_OVERRIDE;
                }
                break;
            case INTAKE_TO_FEEDER:
                if (this.feeder_full) {
                    this.intake.stopMotor();
                    this.intake.setIntakeIn();
                } else if (this.manual_intake_in
                    || this.manual_intake_out
                    || this.manual_intake_roll_in
                    || this.manual_intake_roll_out) {
                    this.state = IntakeSTM_State.MANUAL_OVERRIDE;
                }
                break;
            case LOOKING_FOR_BALL:
                if (!ball2_button && !(ball1_button && !feeder_full)) {
                    this.state = IntakeSTM_State.EMPTY_HOPPER;
                    this.intake.setIntakeIn();
                    this.intake.stopMotor();
                } else if (this.hopper_full) {
                    this.state = IntakeSTM_State.HOPPER_LOADED;
                    this.intake.setIntakeIn();
                    this.intake.stopMotor();
                } else if (this.manual_intake_in
                    || this.manual_intake_out
                    || this.manual_intake_roll_in
                    || this.manual_intake_roll_out) {
                    this.state = IntakeSTM_State.MANUAL_OVERRIDE;
                }
                break;
            case MANUAL_OVERRIDE:
                if (!this.manual_intake_in
                    && !this.manual_intake_out
                    && !this.manual_intake_roll_in
                    && !this.manual_intake_roll_out) {
                    // Exciting manual override state
                    if (this.hopper_full) {
                        this.state = IntakeSTM_State.HOPPER_LOADED;
                        this.intake.setIntakeIn();
                        this.intake.stopMotor();
                    } else if (!this.hopper_full) {
                        this.state = IntakeSTM_State.EMPTY_HOPPER;
                        this.intake.setIntakeIn();
                        this.intake.stopMotor();
                    }
                }
                if (this.manual_intake_in) {
                    this.intake.setIntakeIn();
                } else if (this.manual_intake_out) {
                    this.intake.setIntakeOut();
                }
                if (this.manual_intake_roll_in) {
                    this.intake.runMotorForward();
                } else if (this.manual_intake_roll_out) {
                    this.intake.runMotorBackward();
                }
                break;
            default:
                System.err.println("ERROR: Unknown state encountered. This should not happen and is likely the result of a new state being added but not fully implemented");
                break;
        }
    }

    private enum IntakeSTM_State {
        UNINITIALIZED,
        EMPTY_HOPPER,
        LOOKING_FOR_BALL,
        HOPPER_LOADED,
        INTAKE_TO_FEEDER,
        MANUAL_OVERRIDE
    }
}