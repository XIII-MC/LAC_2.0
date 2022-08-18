package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;

@CheckInfo(name = "BadPacket AA", category = Category.PLAYER)
public class BadPacketAA extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.FLYING) {
            final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getNMSPacket());
            if (wrapper.hasRotationChanged()) {
                final float pitch = Math.abs(wrapper.getPitch());
                final float threshold = isExempt(ExemptType.CLIMBABLE) ? 91.11f : 90.f;
                if (pitch > threshold) {
                    fail("Rotations impossible", "Aucune");
                }
            }
        }
    }

}
