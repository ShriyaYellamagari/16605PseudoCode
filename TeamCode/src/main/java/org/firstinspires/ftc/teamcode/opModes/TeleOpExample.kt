package org.firstinspires.ftc.teamcode.opModes

import com.pedropathing.follower.Follower
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.core.command.CommandManager
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import com.rowanmcalpin.nextftc.pedro.DriverControlled
import com.rowanmcalpin.nextftc.pedro.FollowPath
import com.rowanmcalpin.nextftc.pedro.PedroOpMode
import org.firstinspires.ftc.teamcode.commands.Commands
import org.firstinspires.ftc.teamcode.subsystems.Arm
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Slide
import org.firstinspires.ftc.teamcode.opModes.PathConstants.start
import org.firstinspires.ftc.teamcode.opModes.PathConstants.score
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants

// https://nextftc.dev/user-guide/opmodes/teleop
// https://nextftc.dev/builtin-commands/drivetrain-commands/pedropathing
@Autonomous(name = "AutoExample")
class TeleOpExample: PedroOpMode(Intake, Arm, Slide) {
    override fun onInit() {
        follower = Follower(hardwareMap, FConstants::class.java, LConstants::class.java)
    }

    override fun onStartButtonPressed() {
        gamepadManager.gamepad1.dpadUp.pressedCommand = { Commands.deposit }
        CommandManager.scheduleCommand(DriverControlled(gamepadManager.gamepad1, true))
    }
}