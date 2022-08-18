package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket I", category = Category.PLAYER)
public class BadPacketI extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            boolean isPost = isPost(packet.getPacketId(), (byte) -100);
            if (isPost) fail("Mauvais ordre des packets", "ENTITY_ACTION");
            if (isPost) packet.setCancelled(true);
        }
    }

}
