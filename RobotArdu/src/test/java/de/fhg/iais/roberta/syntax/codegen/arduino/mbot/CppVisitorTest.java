package de.fhg.iais.roberta.syntax.codegen.arduino.mbot;

import org.junit.Test;

import de.fhg.iais.roberta.util.test.ardu.HelperMakeBlock;

public class CppVisitorTest {
    HelperMakeBlock h = new HelperMakeBlock();

    private static final String MAIN_METHOD1 = ""
        + "#include <math.h> \n"
        + "#include <MeMCore.h> \n"
        + "#include <Wire.h>\n"
        + "#include <SoftwareSerial.h>\n"
        + "#include <CountUpDownTimer.h>\n"
        + "#include <RobertaFunctions.h>\n"
        + "#include \"MeDrive.h\"\n\n";

    private static final String MAIN_METHOD2 = "" + "void setup(){" + "Serial.begin(9600);";

    @Test
    public void motor1m1Test() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeDCMotor motor1(M1);"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "        motor1.run(30);\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/motor1m1.xml", true);
    }

    @Test
    public void ultrasonicSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeUltrasonicSensor ultraSensor4(PORT_4);"
            + "MeDCMotor motor1(M1);"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "        motor1.run(ultraSensor4.distanceCm());\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_ultrasonic_sensor.xml", true);
    }

    @Test
    public void LightSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeLineFollower lineFinder1(PORT_1);"
            + "bool item;"
            + MAIN_METHOD2
            + "item=lineFinder1.readSensors()&1;"
            + "}"
            + "void loop(){"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_light_sensor2.xml", true);
    }

    @Test
    public void TouchSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeTouchSensor myTouch1(PORT_1);"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (myTouch1.touched()) {"
            + "        delay(500);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_touch_sensor.xml", true);
    }

    @Test
    public void PIRMotionSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MePIRMotionSensor pir3(PORT_3);"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (pir3.isHumanDetected()) {"
            + "        delay(500);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_pir_motion_sensor.xml", true);
    }

    @Test
    public void JoyStickTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeJoystick myJoystick2(PORT_2);"
            + "MeDCMotormotor1(M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (myJoystick2.readX() == 0 ) {"
            + "        motor1.run(30);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_joystick_sample.xml", true);
    }

    @Test
    public void GyroscopeTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeGyro myGyro1(PORT_1);"
            + "MeDCMotor motor1(M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (myGyro1.getGyroX() > 10 ) {"
            + "        motor1.run(30);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_gyroscope_sensor.xml", true);
    }

    @Test
    public void AccelerometerTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeGyro myGyro1(PORT_1);"
            + "MeDCMotor motor1(M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (myGyro1.getAngleX() > 10 ) {"
            + "        motor1.run(30);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_accelerometer_sensor.xml", true);
    }

    @Test
    public void FlameSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeFlameSensor flameSensor1(PORT_1);"
            + "MeDCMotor motor1(M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (flameSensor1.readAnalog() > 20 ) {"
            + "        motor1.run(30);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_flame_sensor.xml", true);
    }

    @Test
    public void AmbientLightSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeLightSensormyLight0(PORT_0);"
            + "MeDCMotor motor1(M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (myLight0.read() > 10 ) {"
            + "        motor1.run(30);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_ambientlight_sensor.xml", true);
    }

    @Test
    public void SoundSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeSoundSensor mySound3(PORT_3);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "if (mySound3.strength() > 0) {"
            + "        delay(500);}\n"

            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_sound_sensor.xml", true);
    }

    @Test
    public void TemperatureSensorTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "RobertaFunctions rob;"
            + "MeHumiture myTemp1(PORT_1);"
            + "MeDrive myDrive(M2,M1);"
            + "double item;"
            + MAIN_METHOD2
            + "}"
            + "void loop(){"
            + "myTemp1.update();"
            + "if (myTemp1.getTemperature() < 20) {"
            + "        myDrive.drive(30, 1);}\n"

            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_temperature_sensor.xml", true);
    }

    @Test
    public void TimerTest() throws Exception {

        final String a = "" //
            + MAIN_METHOD1
            + "#include<CountUpDown.h>"
            + "CountUpDownTimer T(UP,HIGH);"
            + "RobertaFunctions rob;"
            + "double item;"
            + MAIN_METHOD2
            + "T.StartTimer();"
            + "}"
            + "void loop(){"
            + "T.Timer();"
            + "if (T.ShowSeconds() > 60 ) {"
            + "       delay(500);}\n"
            + "}\n";

        this.h.assertCodeIsOk(a, "/syntax/code_generator/java/makeblock/get_timer.xml", true);
    }

}
