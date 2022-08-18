package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket H", category = Category.PLAYER)
public class BadPacketH extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.BLOCK_DIG) {
            boolean isPost = isPost(packet.getPacketId(), (byte) -100);
            if (isPost) fail("Mauvais ordre des packets", "BLOCK_DIG");
            if (isPost) packet.setCancelled(true);
        }
    }

}
