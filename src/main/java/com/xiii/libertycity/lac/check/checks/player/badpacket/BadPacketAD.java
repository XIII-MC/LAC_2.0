package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;

@CheckInfo(name = "BadPacket AD", category = Category.PLAYER)
public class BadPacketAD extends Check {

    public boolean lastSprinting;
    boolean thanksMojang;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.ENTITY_ACTION) {
            WrappedPacketInEntityAction wrapper = new WrappedPacketInEntityAction(packet.getNMSPacket());

            if (wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.START_SPRINTING) {
                if (lastSprinting) {
                    if (!thanksMojang) {
                        thanksMojang = true;
                        return;
                    }
                    fail("Sprint Impossible", "Aucune");
                }

                lastSprinting = true;
            } else if (wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.STOP_SPRINTING) {
                if (!lastSprinting) {
                    if (!thanksMojang) {
                        thanksMojang = true;
                        return;
                    }
                    fail("Sprint Impossible", "Aucune");
                }

                lastSprinting = false;
            }
        }
    }

}
