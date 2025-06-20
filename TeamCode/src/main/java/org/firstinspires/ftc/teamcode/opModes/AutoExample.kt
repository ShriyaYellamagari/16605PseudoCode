package org.firstinspires.ftc.teamcode.opModes

import com.pedropathing.follower.Follower
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
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

// https://nextftc.dev/user-guide/opmodes/autonomous
// https://nextftc.dev/user-guide/opmodes/pedropathing
@Autonomous(name = "AutoExample")
class AutoExample: PedroOpMode(Intake, Arm, Slide) {
    val firstPath = PathBuilder().addPath(
        BezierLine(
            Point(start.x, start.y, Point.CARTESIAN),
            Point(score.x, score.y, Point.CARTESIAN),
        )
    ).build()

    override fun onInit() {
        follower = Follower(hardwareMap, FConstants::class.java, LConstants::class.java)
        follower.setStartingPose(start)
    }

    override fun onStartButtonPressed() {
        FollowPath(firstPath).then(Commands.deposit).start()
    }
}