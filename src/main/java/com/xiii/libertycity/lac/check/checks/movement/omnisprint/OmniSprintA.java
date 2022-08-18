package com.xiii.libertycity.lac.check.checks.movement.omnisprint;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "OmniSprint A", category = Category.MOVEMENT)
public class OmniSprintA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.LIQUID, ExemptType.SLAB, ExemptType.STAIRS, ExemptType.SLIME, ExemptType.VELOCITY);
        if(data.player.isSprinting()) {
            if(data.getAngle() > 90 && !data.inAir && !exempt && !data.onLowBlock) {
                fail("Court invalidement","a=" + data.getAngle());
            } else {
                removeBuffer();
            }
        } else {
            removeBuffer();
        }
    }
}
