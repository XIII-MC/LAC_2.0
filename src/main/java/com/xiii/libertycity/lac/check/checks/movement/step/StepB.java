package com.xiii.libertycity.lac.check.checks.movement.step;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Step B", category = Category.MOVEMENT)
public class StepB extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {

        boolean exempt = isExempt(ExemptType.PLACE, ExemptType.STAIRS, ExemptType.GLIDE, ExemptType.SLAB, ExemptType.SLIME, ExemptType.FLYING, ExemptType.NEAR_VEHICLE, ExemptType.INSIDE_VEHICLE, ExemptType.TELEPORT, ExemptType.LIQUID, ExemptType.PLACE);
        if(isExempt(ExemptType.VELOCITY)) maxBuffer = 8;
        if(!isExempt(ExemptType.VELOCITY)) maxBuffer = 4;
        if(motionY > 0.117600002289 && !data.onLowBlock && !exempt) fail("airTicks en trop", "my=" + motionY);
        if(motionY <= 0.117600002289) {
            if(!data.inAir) buffer = 0;
            if(data.onSolidGround) buffer = 0;
        }

    }

}
