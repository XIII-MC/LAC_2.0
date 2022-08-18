package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;

@CheckInfo(name = "BadPacket P", category = Category.PLAYER)
public class BadPacketP extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            WrappedPacketInUseEntity p = new WrappedPacketInUseEntity(packet.getNMSPacket());
            handle:
            {
                if (p.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) break handle;

                boolean placing = false;
                if (System.currentTimeMillis() - data.lastblockplace < 1) {
                    placing = true;
                }

                if (placing) fail("Mauvais ordre des packets", "USE_ENTITY");
            }
        }
    }

}
