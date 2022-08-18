package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;

@CheckInfo(name = "BadPacket U", category = Category.PLAYER)
public class BadPacketU extends Check {

    private int streak = 0;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.VEHICLE_MOVE) {
            if (packet.getPacketId() == PacketType.Play.Client.FLYING) {
                WrappedPacketInFlying p = new WrappedPacketInFlying(packet.getNMSPacket());
                if (!p.hasPositionChanged() && packet.getPlayer().getVehicle() == null) {
                    // There must be a position update by the client every 20 ticks
                    if (++streak > 20) {
                        fail("Mauvais ordre des packets", "FLYING_VEHICULE");
                    }
                } else {
                    streak = 0;
                }
            }
        } else if (packet.getPacketId() == PacketType.Play.Client.STEER_VEHICLE) {
            streak = 0;
        }
    }

}
