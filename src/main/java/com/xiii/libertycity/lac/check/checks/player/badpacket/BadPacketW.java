package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket W", category = Category.PLAYER)
public class BadPacketW extends Check {

    long placetime;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            placetime = System.currentTimeMillis();
        }
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            long delay = (System.currentTimeMillis() - placetime);
            if (delay > 10 && delay < 50) {
                fail("Mauvais ordre des packets", "BLOCK_PLACE - USE_ENTITY");
            }
        }
    }

}
