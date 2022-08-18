package com.xiii.libertycity.lac.check.checks.world.baritone;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.utils.MathUtil;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Baritone A", category = Category.WORLD)
public class BaritoneA extends Check {

    private int verbose;
    private float dY;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {

        final float divisorY = (float) MathUtil.gcd(deltaYaw, dY);
        dY = Math.abs(deltaYaw);
        final float dP = Math.abs(deltaPitch);
        if (deltaYaw == 0 && dP > 0 && dP < 1 && (Math.abs(data.to.getPitch()) != 90.0f)) {
            if (divisorY < MathUtil.MINIMUM_DIVISOR) {
                verbose++;
                if (verbose > 8) {
                    fail("Rotations robotique/automatisé", "null");
                }
            } else {
                verbose = 0;
            }
        }

    }
}