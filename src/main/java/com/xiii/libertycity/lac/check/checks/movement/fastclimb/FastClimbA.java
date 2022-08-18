package com.xiii.libertycity.lac.check.checks.movement.fastclimb;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "FastClimb A", category = Category.MOVEMENT)
public class FastClimbA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {

        boolean exempt = isExempt(ExemptType.PLACE, ExemptType.STAIRS, ExemptType.GLIDE, ExemptType.SLAB, ExemptType.SLIME, ExemptType.FLYING, ExemptType.NEAR_VEHICLE, ExemptType.INSIDE_VEHICLE, ExemptType.TELEPORT, ExemptType.LIQUID, ExemptType.PLACE);

        if(motionY > 0.117600002289 && !data.onLowBlock && !exempt && isExempt(ExemptType.CLIMBABLE)) fail("Grimpe trop vite", "my=" + motionY);
        if(motionY <= 0.117600002289) removeBuffer();

    }

}
