package org.firstinspires.ftc.teamcode.opModes

import com.pedropathing.follower.Follower
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathBuilder
import com.pedropathing.pathgen.Point
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.pedro.FollowPath
import com.rowanmcalpin.nextftc.pedro.PedroOpMode
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.opModes.PathConstants.testStart
import org.firstinspires.ftc.teamcode.opModes.PathConstants.testForward
import org.firstinspires.ftc.teamcode.opModes.PathConstants.testRight
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants

@Autonomous(name = "AutoTest")
class AutoTest: PedroOpMode(Drivetrain) {

    val testPath = PathBuilder()
        .addPath(
            BezierLine(
                Point(testStart.x, testStart.y, Point.CARTESIAN),
                Point(testForward.x, testForward.y, Point.CARTESIAN)
            )
        )
        .addPath(
            BezierLine(
                Point(testForward.x, testForward.y, Point.CARTESIAN),
                Point(testRight.x, testRight.y, Point.CARTESIAN)
            )
        )
        .build()

    override fun onInit() {
        follower = Follower(hardwareMap, FConstants::class.java, LConstants::class.java)
        follower.setStartingPose(testStart)
    }

    override fun onStartButtonPressed() {
        FollowPath(testPath).start()
    }
}