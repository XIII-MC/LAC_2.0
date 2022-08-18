package com.xiii.libertycity.lac.check.checks.movement.fly;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Fly F", category = Category.MOVEMENT)
public class FlyF extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.TELEPORT, ExemptType.SLIME, ExemptType.LIQUID, ExemptType.WEB, ExemptType.GROUND, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.FLYING);
        if(!exempt && !data.onLowBlock) {
            if (Math.abs(motionY - data.predymotion) > 1.45 && (System.currentTimeMillis() - data.lasthurt > 1400)) {
                fail("Téléportation verticale impossible", "pred=" + Math.abs(motionY - data.predymotion));
            } else removeBuffer();
        } else removeBuffer();
    }

}
