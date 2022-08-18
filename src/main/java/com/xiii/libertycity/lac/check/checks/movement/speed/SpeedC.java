package com.xiii.libertycity.lac.check.checks.movement.speed;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Speed C", category = Category.MOVEMENT)
public class SpeedC extends Check {

    double speed;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.INSIDE_VEHICLE, ExemptType.WEB, ExemptType.CLIMBABLE);
        speed = Math.sqrt(Math.pow(Math.abs(motionX), 2) + Math.pow(Math.abs(motionZ), 2));
        speed = Math.round(speed * 10000000);
        if(!exempt && String.valueOf(speed).contains("000")) fail("Movement trop arrondis", "cs=" + speed);
        if(!String.valueOf(speed).contains("000")) removeBuffer();
    }
}
