package org.firstinspires.ftc.teamcode.subsystems

import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.ArmFeedforward
import com.rowanmcalpin.nextftc.core.units.deg
import com.rowanmcalpin.nextftc.ftc.OpModeData
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition


// Mechanically you can improve the arm by adding a spring like this https://youtu.be/OdmpYBSMWzM?si=8JJxWp9zTi0-wvZX
// You can also add an additional motor
// One tip is to select your cursor on any function or variable you don't know and press Ctrl + B to see where it is defined
// I recommend reading https://www.ctrlaltftc.com/introduction-to-controls before using this file
object Arm : Subsystem() {
    lateinit var motor: MotorEx
    //lateinit var motor2: MotorEx // This is if you have a second motor to help lift the arm

    /* This is if you have a limit switch to detect the arm at the zero position
    https://www.gobilda.com/limit-switches or https://www.revrobotics.com/rev-31-1462/?searchid=4551535&search_query=limit
    */
    //lateinit var switch: DigitalChannel

    // TODO: Tune kcos, kp, and kd values for your arm

    /*
    FTC Dashboard would make tuning easier https://acmerobotics.github.io/ftc-dashboard/features#configuration-variables
    Tune kcos first, then kp, and finally kd
     */
    /*The force of gravity applied to the arm is represented by: mass * gravity * length * cos(angle).
    Mass gravity and length are supposed to be constant so we can represent that by the kcos term
    It should be the lowest value that allows the arm to hold position while parallel from the ground. You can use the iphone level for this
    Basic explanation: https://www.ctrlaltftc.com/feedforward-control#arm-feedforward
    Advanced explanation: https://file.tavsys.net/control/controls-engineering-in-frc.pdf#page=84
    If you think about swinging a weight with your arm, you need to put the most force to hold it flat.
    The max of the cosine function is at 0 degrees
    For a better explanation you can ask chatgpt "explain mglcos(theta) for arm feedforward"*/
    @JvmField
    var kcos = 0.0

    @JvmField
    var kp = 0.0

    @JvmField
    var kd = 0.0


    /*Convert motor ticks to angle https://nextftc.dev/concepts/units#angle
    You can use the measure app and then level feature on iphone to find the angle of the arm at a known position
    Then get the angle in radians by amount ticks moved
    Either way, we use .inRad to get the angle in radians for the controller
     */
    val angle = { position: Double -> (position).deg.inRad }

    // https://www.ctrlaltftc.com/the-pid-controller
    val controller = PIDFController(
        kp,
        0.0,
        kd,
        ArmFeedforward(
            kcos, angle
        )
    )

    /*
    Theoretically you should use a GainScheduledArmFeedforward because the length of the arm changes.
    Basically the length of the arm isn't constant anymore so in the equation:
    mass * gravity * length * cos(angle)
    Only mass and gravity are constant, the mass of the sample is negligible so we can ignore it.
    Now the kcos term only represents the gravity * length part of the equation so we need to multiply it by the length of the extension.
    If you think about swinging a weight with your arm, the longer the arm is, the more force you need to apply to keep it in position.
    */
    /*val controller = PIDFController(
        kp,
        0.0,
        kd,
        GainScheduledArmFeedforward(
            {kcos*slideLength}, angle
        )
    )*/

    /*Example of a command to move the arm to a position. You can add commands for all the other positions you want
    A future version of NextFTC will let you use a motion profile https://www.ctrlaltftc.com/advanced/motion-profiling
    This is the example implementation which will be released soon:
    https://github.com/NextFTC/NextControl/blob/feature/mp/src/main/kotlin/dev/nextftc/control/interpolators/TrapezoidProfiles.kt

    This is the structure of a command: https://nextftc.dev/concepts/commands

    NextFTC has a built-in command to move the arm to a position using the RunToPosition command.

    If you want to be even fancier you can use voltage compensation so the voltage of the battery won't affect the motor power.
    If you Ctrl + F "voltage" you can see how it is used https://github.com/Pedro-Pathing/PedroPathing/blob/main/src/main/java/com/pedropathing/follower/Follower.java
    To implement it your self just access the drive follower variable and multiply the motor power by getVoltageNormalized()
    You don't want to get the voltage in this file because the drive already does it and getting the voltage just adds additional delay*/
    val toLow: Command
        get() = RunToPosition(
            motor, // MOTOR TO MOVE
            0.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this
        ) // IMPLEMENTED SUBSYSTEM



    override fun initialize() {
        motor = MotorEx("arm") // TODO: Set the name of the motor in the config file
        //motor2 = MotorEx("arm2") // If you have a second motor, uncomment this line and set the name in the config
        //motor = MotorGroup(motor1, motor2) // If you have a second motor, add it to the MotorGroup like this: MotorGroup(motor, motor2),
        //motor.direction = REVERSE // TODO: Set the direction of the motor if needed

        //switch = OpModeData.hardwareMap.get(DigitalChannel::class.java, "switch") // Write the name of the limit switch in the config file
    }

    /*override fun periodic() {
        if (switch.state)
            motor.resetEncoder() // If the limit switch is pressed, reset the encoder to 0
    }*/

    // You can make the default command hold the arm in position of the last command.
    // https://nextftc.dev/builtin-commands/hardware/motors#holdposition
    /*override val defaultCommand: Command
        get() = HoldPosition(motor, controller, this)*/


}
