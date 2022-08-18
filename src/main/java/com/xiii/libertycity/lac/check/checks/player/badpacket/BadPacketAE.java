package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;

@CheckInfo(name = "BadPacket AE", category = Category.PLAYER)
public class BadPacketAE extends Check {

    private int hits;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            WrappedPacketInUseEntity wrapper = new WrappedPacketInUseEntity(packet.getNMSPacket());

            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) return;

            if (++hits > 2) {
                fail("Packets Manquant", "hits=" + hits);
            }
        } else if(packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) {
            hits = 0;
        }
    }

}
