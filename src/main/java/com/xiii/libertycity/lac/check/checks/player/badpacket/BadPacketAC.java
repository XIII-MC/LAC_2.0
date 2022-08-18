package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;

@CheckInfo(name = "BadPacket AC", category = Category.PLAYER)
public class BadPacketAC extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.LOOK || packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK) {
            WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getNMSPacket());
            if(wrapper.getPitch() > 90 || wrapper.getPitch() < -90) fail("Rotations Impossible", "pitch=" + wrapper.getPitch());
        }
    }

}
