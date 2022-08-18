package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.helditemslot.WrappedPacketInHeldItemSlot;

@CheckInfo(name = "BadPacket L", category = Category.PLAYER)
public class BadPacketL extends Check {

    private int lastSlot = -1;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.HELD_ITEM_SLOT) {
            WrappedPacketInHeldItemSlot p = new WrappedPacketInHeldItemSlot(packet.getNMSPacket());
            if (p.getCurrentSelectedSlot() == lastSlot) fail("Mauvais ordre des packets", "HELD_ITEM_SLOT_REPEATED");
            lastSlot = p.getCurrentSelectedSlot();
        }
    }

}
