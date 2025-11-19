package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.0019785;
        ThreeWheelConstants.strafeTicksToInches = 0.0019785;
        ThreeWheelConstants.turnTicksToInches = 0.0019785;
        //if it doesn't work, number 0.0019785(from claude) should change to 0.001978956
        ThreeWheelConstants.leftY = -0.644;
        ThreeWheelConstants.rightY = -0.664;
        ThreeWheelConstants.strafeX = 8.19;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




