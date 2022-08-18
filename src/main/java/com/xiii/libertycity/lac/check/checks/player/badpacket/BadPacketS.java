package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.steervehicle.WrappedPacketInSteerVehicle;

@CheckInfo(name = "BadPacket S", category = Category.PLAYER)
public class BadPacketS extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.STEER_VEHICLE) {
            WrappedPacketInSteerVehicle p = new WrappedPacketInSteerVehicle(packet.getNMSPacket());
            final float forward = Math.abs(p.getForwardValue());
            final float side = Math.abs(p.getSideValue());

            // The max forward/side value is .98 or -.98
            final boolean invalid = side > .98F || forward > .98F;

            if (invalid) {
                fail("Mauvais ordre des packets", "STREE_VEHICULE_IMPOSSIBLE");
            }
        }
    }

}
