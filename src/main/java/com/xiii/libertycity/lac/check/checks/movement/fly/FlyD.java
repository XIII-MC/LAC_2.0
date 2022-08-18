package com.xiii.libertycity.lac.check.checks.movement.fly;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Fly D", category = Category.MOVEMENT)
public class FlyD extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.SLIME, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.VELOCITY, ExemptType.INSIDE_VEHICLE, ExemptType.NEAR_VEHICLE, ExemptType.CLIMBABLE);
        double diff = Math.abs(data.motionY - data.predymotion);
        if(diff > 0.001 && data.inAir && !exempt) fail("Predictions non suivis", "d=" + diff); else removeBuffer();
    }

}
