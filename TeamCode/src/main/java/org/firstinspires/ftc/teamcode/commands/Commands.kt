package org.firstinspires.ftc.teamcode.commands

import org.firstinspires.ftc.teamcode.subsystems.Arm
import org.firstinspires.ftc.teamcode.subsystems.Slide

// You can use these in auto and teleop
// https://nextftc.dev/builtin-commands/commandgroups
object Commands{
    // Example of a command group for getting to the deposit position
    val deposit = Arm.toLow.and(Slide.extend)
}