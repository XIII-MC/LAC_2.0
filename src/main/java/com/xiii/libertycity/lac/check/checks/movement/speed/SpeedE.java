package com.xiii.libertycity.lac.check.checks.movement.speed;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Speed E", category = Category.MOVEMENT)
public class SpeedE extends Check {

    int AirTicks;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.GLIDE, ExemptType.FLYING, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.PLACE, ExemptType.NEAR_VEHICLE, ExemptType.INSIDE_VEHICLE, ExemptType.PISTON, ExemptType.SLIME, ExemptType.ICE);
        if (!exempt) {
            if (!data.playerGround) {
                AirTicks++;
            } else {
                AirTicks = 0;
            }
            if (AirTicks > 3 && !data.blockabove) {
                double value = ((Math.pow((motionX + motionZ), 8)) - (Math.pow(data.getMotionX(3) + data.getMotionZ(3), 8)) * 0.91);
                if (value > 0.000086 || value < -0.00086) {
                    fail("Se déplace en l'air", "str=" + value); // 3.5 and add 1
                } else {
                    removeBuffer(); // 0.35
                }
            }
        }
    }

}
