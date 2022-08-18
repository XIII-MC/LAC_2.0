package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket E", category = Category.PLAYER)
public class BadPacketE extends Check {

    double lastUse;
    int packetD;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == -68) {
            if (this.lastUse - System.currentTimeMillis() > -50.0D && this.lastUse - System.currentTimeMillis() < -1.0D)
                packetD += 1;
            if (packetD > 3) fail("Utilise un item trop vite", "delay=" + (this.lastUse - System.currentTimeMillis()));
            if (packetD > 3)
                packet.setCancelled(true);
            if (this.lastUse - System.currentTimeMillis() < -50.0D)
                if (packetD >= 1) packetD -= 1;
            this.lastUse = System.currentTimeMillis();
        }
    }

}
