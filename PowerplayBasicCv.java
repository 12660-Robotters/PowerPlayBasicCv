package org.firstinspires.ftc.teamcode.PowerPlayBasicCv;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.ClassFactory;

import java.util.List;

public class PowerplayBasicCv {
    // Make this the actual file
    private ClassFactory classFactory;
    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // These are the Labels, you can change this to use your custom model from the ML Toolchain if you want!
    private final String[] labels = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel",
    };

    private HardwareMap hardwareMap;

    // Declare the Objects
    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    public PowerplayBasicCv() {}

    public void init(HardwareMap hardwareMap) {
        initVuforia();
        initTfod();

        if(tfod != null) {
            tfod.activate();

            tfod.setZoom(1.0, 16.0/9.0);
        }



    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParamaters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        // Result Confidence for Tuning
        tfodParamaters.minResultConfidence = 0.75f;
        tfodParamaters.isModelTensorFlow2 = true;
        tfodParamaters.inputSize = 300;

        tfod = classFactory.createTFObjectDetector(tfodParamaters, vuforia);

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, labels);
    }
    private void initVuforia() {
        // Start Seting up vuforia
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        // Set this to your vuforia api key
        String VUFORIA_KEY = "1234";
        // Change this to Vuforia License Key
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        // Set Webcam Name to webcam in config or modify this variable
        parameters.cameraName = hardwareMap.get(WebcamName.class, "cam1");
        vuforia =classFactory.createVuforia(parameters);
    }


    // Returns Parking Spot 1,2,3 or -1 if not detected
    public int pullCv() {
        List<Recognition> recognitionList = tfod.getUpdatedRecognitions();
        if(recognitionList != null) {
            for (Recognition rec : recognitionList) {
                for(String item : labels) {
                    if (rec.getLabel().equals(item)) {
                        String[] items = item.split(" ");
                        int returned = Integer.parseInt(items[0]);
                        return returned;
                    }
                }
            }
        }
        return -1;
    }

}
