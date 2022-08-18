package com.xiii.libertycity.lac.check.checks.movement.fly;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Fly A", category = Category.MOVEMENT)
public class FlyA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.CLIMBABLE, ExemptType.LIQUID, ExemptType.WEB, ExemptType.FLYING, ExemptType.SLIME, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.NEAR_VEHICLE, ExemptType.PISTON, ExemptType.PLACE, ExemptType.VELOCITY);
        if (data.inAir) {
            if (Math.abs(data.predymotion - motionY) > 0.005 && !exempt)
                fail("Predictions non suivis", "my=" + motionY + " py=" + data.predymotion);
        } else buffer = 0;
    }
}
