package org.firstinspires.ftc.teamcode.example.kotlin

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode

@Autonomous(name = "NextFTC Autonomous Program Kotlin")
class AutonomousProgram: NextFTCOpMode(Claw, Arm) {
    val firstRoutine: Command
        get() = SequentialGroup(
            Arm.toHigh,
            ParallelGroup(
                Arm.toMiddle,
                Claw.close
            ),
            Delay(0.5),
            ParallelGroup(
                Claw.open,
                Arm.toLow
            )
        )

    override fun onStartButtonPressed() {
        firstRoutine()
    }
}