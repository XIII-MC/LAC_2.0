package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket R", category = Category.PLAYER)
public class BadPacketR extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.WINDOW_CLICK) {
            boolean isPost = isPost(packet.getPacketId(), (byte) -100);
            if (isPost) fail("Mauvais ordre des packets", "WINDOW_CLICK");
            if (isPost) packet.setCancelled(true);
        }
    }

}
