package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;

@CheckInfo(name = "BadPacket Y", category = Category.PLAYER)
public class BadPacketY extends Check {

    int sneakBuffer = 0;
    boolean sentsneak;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            WrappedPacketInEntityAction action = new WrappedPacketInEntityAction(packet.getNMSPacket());
            if (action.getAction() == WrappedPacketInEntityAction.PlayerAction.STOP_SNEAKING) {
                if (!sentsneak) {
                    sneakBuffer += 1;
                    if (sneakBuffer > 3) fail("Sneak invalid", "Aucune");
                } else {
                    sentsneak = false;
                    sneakBuffer -= 1;
                }
            }
            if (action.getAction() == WrappedPacketInEntityAction.PlayerAction.START_SNEAKING) {
                if (sentsneak) {
                    fail("Sneak invalid", "Aucune");
                } else {
                    sentsneak = true;
                    sneakBuffer -= 1;
                }
            }
        }
    }
}
