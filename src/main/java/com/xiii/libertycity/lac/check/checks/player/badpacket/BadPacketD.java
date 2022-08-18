package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket D", category = Category.PLAYER)
public class BadPacketD extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (this.data.eatDelay < 1300.0D) {
            fail("A manger trop vite", "delay=" + this.data.eatDelay);
            this.data.eatDelay = 1300.0D;
        }
        if (this.data.lastShootDelay < 99.0D) {
            fail("Tire trop vite", "delay=" + this.data.lastShootDelay);
            this.data.lastShootDelay = 99.0D;
        }
        if (this.data.shootDelay < 299.0D) {
            fail("Tire trop vite", "delay=" + this.data.shootDelay);
            this.data.shootDelay = 299.0D;
        }
    }

}
