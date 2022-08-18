package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket J", category = Category.PLAYER)
public class BadPacketJ extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.CUSTOM_PAYLOAD) {
            boolean isPost = isPost(packet.getPacketId(), (byte) -100);
            if (isPost) fail("Mauvais ordre des packets", "CUSTOM_PAYLOAD");
            if (isPost) packet.setCancelled(true);
        }
    }

}
