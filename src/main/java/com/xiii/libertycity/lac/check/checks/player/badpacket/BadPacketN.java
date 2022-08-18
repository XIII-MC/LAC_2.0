package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;

import static io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig.PlayerDigType.RELEASE_USE_ITEM;

@CheckInfo(name = "BadPacket N", category = Category.PLAYER)
public class BadPacketN extends Check {

    private int countH = 0;

    public void onPacket(PacketPlayReceiveEvent packet) {
        final boolean digging = packet.getPacketId() == PacketType.Play.Client.BLOCK_DIG;
        final boolean flying = packet.getPacketId() == PacketType.Play.Client.FLYING;
        if (digging) {
            final WrappedPacketInBlockDig p = new WrappedPacketInBlockDig(packet.getNMSPacket());

            handle:
            {
                if (p.getDigType() != RELEASE_USE_ITEM) break handle;

                final boolean invalid = ++countH > 10;

                if (invalid) fail("Mauvais ordre des packets", "BLOCK_IN_DIG_REPEATED");
            }
        } else if (flying) {
            countH = 0;
        }
    }

}
