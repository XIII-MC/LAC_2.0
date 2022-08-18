package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket B", category = Category.PLAYER)
public class BadPacketB extends Check {

    int pCount2;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == -93) {
            this.pCount2++;
        } else {
            this.pCount2 = 0;
        }
        if (this.pCount2 > 42)
            fail("Spam de packets", "pCount2=" + this.pCount2);
        if (this.pCount2 > 42)
            packet.setCancelled(true);
    }

}
