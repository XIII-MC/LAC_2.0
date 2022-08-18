package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;

@CheckInfo(name = "BadPacket AB", category = Category.PLAYER)
public class BadPacketAB extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            WrappedPacketInEntityAction wrapper = new WrappedPacketInEntityAction(packet.getNMSPacket());
            if(wrapper.getEntityId() == data.player.getEntityId()) fail("Interagit avec soit même", "eID=" + wrapper.getEntityId() + " pID=" + data.player.getEntityId());
        }
    }

}
