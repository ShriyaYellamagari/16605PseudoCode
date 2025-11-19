package org.firstinspires.ftc.teamcode.opModes

import com.acmerobotics.dashboard.config.Config
import com.pedropathing.localization.Pose

@Config
object PathConstants {
    @JvmField var start = Pose(0.0, 0.0, Math.toRadians(90.0))
    @JvmField var score = Pose(17.25, 131.25, Math.toRadians(-45.0))
    @JvmField var testStart = Pose(0.0, 0.0, 0.0)
    @JvmField var testForward = Pose(0.0, 24.0, 0.0)
    @JvmField var testRight = Pose(24.0, 24.0, 0.0)
}