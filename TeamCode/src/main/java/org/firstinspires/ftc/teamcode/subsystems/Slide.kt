package org.firstinspires.ftc.teamcode.subsystems

import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.ArmFeedforward
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward
import com.rowanmcalpin.nextftc.core.units.deg
import com.rowanmcalpin.nextftc.ftc.OpModeData
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition


// Read the arm file before this one
// Most of the content from the arm file applies to the slide as well
// I would recommend controlling this slide with servos instead of a motor
object Slide : Subsystem() {
    lateinit var motor: MotorEx

    //lateinit var switch: DigitalChannel

    // TODO: Tune kp, and kd values for your slide

    @JvmField
    var kp = 0.0

    @JvmField
    var kd = 0.0

    /*Theoretically you should include feedforward for the force of gravity on the slide based on the arm angle
    You can use https://www.physics.uoguelph.ca/torque-and-rotational-motion-tutorial to derive it
    It should be similar to this: https://www.ctrlaltftc.com/feedforward-control#slide-gravity-feedforward but with a sin term
    You can use chatgpt to help
    Right now I commented out the feedfoward term*/
    val controller = PIDFController(
        kp,
        0.0,
        kd,
        /*StaticFeedforward()*/
    )
    val extend: Command
        get() = RunToPosition(
            motor, // MOTOR TO MOVE
            100.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this
        )



    override fun initialize() {
        motor = MotorEx("arm") // TODO: Set the name of the motor in the config file
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
