package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;

@CheckInfo(name = "BadPacket O", category = Category.PLAYER)
public class BadPacketO extends Check {

    private float lastYaw = 0.0f, lastPitch = 0.0f;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.FLYING) {
            WrappedPacketInFlying p = new WrappedPacketInFlying(packet.getNMSPacket());
            if (!p.hasRotationChanged()) return;

            final float yaw = p.getYaw();
            final float pitch = p.getPitch();

            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.TPS, ExemptType.INSIDE_VEHICLE);

            if (yaw == lastYaw && pitch == lastPitch && !exempt) {
                fail("Mauvais ordre des packets", "FLYING");
            }

            this.lastYaw = yaw;
            this.lastPitch = pitch;
        }
    }

}
