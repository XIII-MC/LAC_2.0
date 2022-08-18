package com.xiii.libertycity.lac.check.checks.movement.speed;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import org.bukkit.potion.PotionEffectType;

@CheckInfo(name = "Speed F", category = Category.MOVEMENT)
public class SpeedF extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {

        boolean step = mathOnGround(motionY) && mathOnGround(data.from.getY());
        boolean jumped = motionY > 0 && data.from.getY() % (1D/64) == 0 && !data.playerGround && !step;
        double expectedJumpMotion = 0.42F + (double)(data.player.hasPotionEffect(PotionEffectType.JUMP) ? (data.player.getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1) * 0.1F : 0);
        boolean exempt = isExempt(ExemptType.INSIDE_VEHICLE, ExemptType.VELOCITY, ExemptType.CLIMBABLE, ExemptType.FLYING, ExemptType.SLIME, ExemptType.BLOCK_ABOVE, ExemptType.PISTON, ExemptType.LIQUID, ExemptType.NEAR_VEHICLE, ExemptType.TELEPORT, ExemptType.WEB, ExemptType.TRAPDOOR);

        if (jumped && !exempt) {
            if (motionY < expectedJumpMotion) {
                fail("Saute trop bas", "my=" + motionY + " ejm=" + expectedJumpMotion);
            }
        }
    }

}
