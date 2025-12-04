//Chassiss TeleOp Test - No Odommetry
//Intake & Outtake TeleOp

package org.firstinspires.ftc.teamcode.opModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Outake

@TeleOp(name = "Chassis TeleOp (No Odometry)")
class TeleOpTest : NextFTCOpMode(Drivetrain, Intake, Outake) {

    override fun onStartButtonPressed() {

        while (opModeIsActive()) {

            // ---- GAMEPAD INPUT ----
            val axial = -gamepad1.left_stick_y.toDouble()
            val lateral = gamepad1.left_stick_x.toDouble()
            val yaw = gamepad1.right_stick_x.toDouble()

            // ---- DRIVE ----
            Drivetrain.drive(axial, lateral, yaw)

            // ---- INTAKE ----
            when {
                gamepad1.a -> Intake.intake()      // Run intake forward
                gamepad1.b -> Intake.outtake()     // Reverse intake
                else -> Intake.stop()              // Stop when no button is pressed
            }

            // ---- OUTTAKE ----x
            if (gamepad1.x) {
                Outake.outtake()
            } else {
                Outake.stop()
            }

            telemetry.addData("Axial", axial)
            telemetry.addData("Lateral", lateral)
            telemetry.addData("Yaw", yaw)
            telemetry.update()
        }
    }
}



