package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket F", category = Category.PLAYER)
public class BadPacketF extends Check {

    int pCount3;
    double packetB;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == -96 || packet.getPacketId() == -94 || packet.getPacketId() == -95) {
            this.pCount3++;
            if (this.pCount3 > 0)
                packetB = 0.0;
            if (this.data.getDistance(false) <= 0.1D)
                packetB = 0.0;
        } else if (packet.getPacketId() == -93) {
            this.pCount3 = -1;
        }
        if (this.pCount3 <= 0 && this.data.getDistance(false) > 0.1D)
            packetB += 0.5;
        if (packetB > 40) fail("Spam de packets", "pCount3=" + this.pCount3);
    }

}
