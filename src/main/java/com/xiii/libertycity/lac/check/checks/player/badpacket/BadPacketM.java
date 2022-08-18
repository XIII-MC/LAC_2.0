package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;

@CheckInfo(name = "BadPacket M", category = Category.PLAYER)
public class BadPacketM extends Check {

    private int count = 0;
    private WrappedPacketInEntityAction.PlayerAction lastAction;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            WrappedPacketInEntityAction p = new WrappedPacketInEntityAction(packet.getNMSPacket());
            final boolean invalid = ++count > 1 && p.getAction() == lastAction;
            if (invalid) fail("Mauvais ordre des packets", "ENTITY_ACTION_REPEATED");
            this.lastAction = p.getAction();
        } else if (packet.getPacketId() == PacketType.Play.Client.FLYING) count = 0;
    }

}
