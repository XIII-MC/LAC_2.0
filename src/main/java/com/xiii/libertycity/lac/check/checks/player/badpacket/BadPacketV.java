package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;

@CheckInfo(name = "BadPacket V", category = Category.PLAYER)
public class BadPacketV extends Check {

    boolean sent;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.BLOCK_DIG) {
            WrappedPacketInBlockDig dig = new WrappedPacketInBlockDig(packet.getNMSPacket());
            if (dig.getDigType().toString().contains("START_DESTROY_BLOCK")) {
                if (sent) {
                    fail("Mauvais ordre des packets", "START_DESTROY_BLOCK");
                    sent = true;
                }
            }
            if (dig.getDigType().toString().contains("STOP_DESTROY_BLOCK")) {
                if (!sent) {
                    fail("Mauvais ordre des packets", "STOP_DESTROY_BLOCK");
                    sent = false;
                }
            }
        }
    }

}
