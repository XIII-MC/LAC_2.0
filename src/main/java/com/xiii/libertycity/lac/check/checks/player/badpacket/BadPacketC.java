package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket C", category = Category.PLAYER)
public class BadPacketC extends Check {

    double lastWindowTime;
    double lastUseEntityTime;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == -105)
            this.lastWindowTime = System.currentTimeMillis();
        if (packet.getPacketId() == -100)
            this.lastUseEntityTime = System.currentTimeMillis();
        double delay = this.lastUseEntityTime - this.lastWindowTime;
        if (packet.getPacketId() == -100 &&
                delay < 50.0D)
            fail("Clique son inventaire trop vite", "delay=" + delay);
    }

}
