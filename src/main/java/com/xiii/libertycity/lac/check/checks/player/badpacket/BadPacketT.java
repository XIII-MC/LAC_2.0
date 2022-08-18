package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.keepalive.WrappedPacketInKeepAlive;

@CheckInfo(name = "BadPacket T", category = Category.PLAYER)
public class BadPacketT extends Check {

    private long lastId = -1;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.KEEP_ALIVE) {
            WrappedPacketInKeepAlive p = new WrappedPacketInKeepAlive(packet.getNMSPacket());
            if (p.getId() == lastId) {
                fail("Mauvais ordre des packets", "KEEP_ALIVE");
            }

            lastId = p.getId();
        }
    }

}
