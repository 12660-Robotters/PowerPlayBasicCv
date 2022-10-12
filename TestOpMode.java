package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test", group = "Test")
public class TestOpMode extends LinearOpMode {
    private final PowerplayBasicCv cvClass = new PowerplayBasicCv();

    @Override
    public void runOpMode(){
        cvClass.init(hardwareMap);

        waitForStart();

        int cv = cvClass.pullcv();

        telemetry.addData("Parking Spot:", "" + cv);
        telemetry.update();
    }
}
