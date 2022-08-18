package com.xiii.libertycity.lac.check.checks.movement.speed;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Speed D", category = Category.MOVEMENT)
public class SpeedD extends Check {

    int invalidA;
    double maxSpeed;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.FLYING, ExemptType.INSIDE_VEHICLE);
        if(data.playerGround) invalidA++;
        if(!data.playerGround) invalidA = 0;
        if(invalidA >= 8) maxSpeed = 0.2895;
        if(invalidA < 8) maxSpeed = 0.62;
        if(data.onLowBlock || isExempt(ExemptType.STAIRS) || isExempt(ExemptType.SLAB)) maxSpeed += 0.2;
        if(isExempt(ExemptType.VELOCITY)) {
            maxSpeed += data.kblevel;
            maxSpeed += 0.45;
        }
        if(System.currentTimeMillis() - data.lastice < 1800) maxSpeed += 0.35;
        if(isExempt(ExemptType.SLIME)) maxSpeed += 0.3;
        if(data.getDeltaXZ() >= maxSpeed && !exempt) fail("Movement de téléportation impossible", "cs=" + data.getDeltaXZ() + " ms=" + maxSpeed);
    }
}
