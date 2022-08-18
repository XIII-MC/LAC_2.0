package com.xiii.libertycity.lac.check.checks.movement.step;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Step C", category = Category.MOVEMENT)
public class StepC extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.SLIME, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.NEAR_VEHICLE, ExemptType.CLIMBABLE, ExemptType.LIQUID, ExemptType.STAIRS, ExemptType.SLAB, ExemptType.WEB);
        if(lastmotionY - motionY < 0.01)  {
            if(!exempt && motionY != 0 && !data.onLowBlock) fail("Différence de movement impossible", "lmY=" + lastmotionY + " mY=" + motionY);
        }
        if(lastmotionY - motionY > 0.01) removeBuffer();
    }
}
