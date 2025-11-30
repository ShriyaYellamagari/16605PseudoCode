//Chassis TeleOp Test - No Odomery
package org.firstinspires.ftc.teamcode.opModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain

@TeleOp(name = "Chassis TeleOp (No Odometry)")
class ChassisTeleOp : NextFTCOpMode(Drivetrain) {
    override fun onStartButtonPressed() {
        while (opModeIsActive()) {

            // GAMEPAD INPUT
            // Read joystick inputs (Pushing the left stick forward gives a negative Y value.)
            val axial = -gamepad1.left_stick_y.toDouble()
            val lateral = gamepad1.left_stick_x.toDouble()
            val yaw = gamepad1.right_stick_x.toDouble()
            //double speed_Limit = 1.0; // You can lower this (e.g., 0.5) for slower driving


            //CALL FUNCTION from Subsystem with all motor math
            Drivetrain.drive(axial, lateral, yaw)

            telemetry.addData("Axial", axial)
            telemetry.addData("Lateral", lateral)
            telemetry.addData("Yaw", yaw)
            telemetry.update()
        }
    }
}


