package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Carousel implements Mechanism {
    int colorMultiplier;
    public Carousel(Color color) {
        if (color == Color.RED) {
            colorMultiplier = -1;
        } else if (color == Color.BLUE) {
            colorMultiplier = 1;
        }
    }
    public DcMotor carouselMotor;
    boolean aWasDown = false;
    boolean bWasDown = false;

    public void init(HardwareMap hardwareMap) {
        carouselMotor = hardwareMap.get(DcMotor.class, "carousel");
        //carousel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void run(Gamepad gamepad) {
        //This makes sure things only happen once.
        if (gamepad.a) {
            if (!aWasDown) {
                // turnCarousel();
                carouselMotor.setPower(colorMultiplier * 0.35);
                aWasDown = true;
                bWasDown = false;
            }
        } else if (gamepad.b) {
            if (!bWasDown) {
                // turnCarousel();
                carouselMotor.setPower(-colorMultiplier * 0.35);
                aWasDown = false;
                bWasDown = true;
            }
        } else {
            aWasDown = false;
            bWasDown = false;
            carouselMotor.setPower(0);
        }
    }

    public void turnCarousel() {
        carouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        carouselMotor.setTargetPosition(colorMultiplier * 2500);
        carouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carouselMotor.setPower(0.55);
    }
}
