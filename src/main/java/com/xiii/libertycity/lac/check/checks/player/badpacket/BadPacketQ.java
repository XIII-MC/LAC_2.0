package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.steervehicle.WrappedPacketInSteerVehicle;

@CheckInfo(name = "BadPacket Q", category = Category.PLAYER)
public class BadPacketQ extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.STEER_VEHICLE) {
            WrappedPacketInSteerVehicle wrapper = new WrappedPacketInSteerVehicle(packet.getNMSPacket());
            float forwards = Math.abs(wrapper.getForwardValue());
            float sideways = Math.abs(wrapper.getSideValue());

            if (forwards > 0.98f || sideways > 0.98f) fail("Packet impossible", "fw=" + forwards + " sw=" + sideways);
        }
    }

}
