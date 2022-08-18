package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket Z", category = Category.PLAYER)
public class BadPacketZ extends Check {

    int sprintBuffer = 0;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (data.player.isSprinting()) {
            if (data.player.getFoodLevel() <= 3) {
                sprintBuffer += 1;
                if (sprintBuffer > 8) fail("Court invalidement", "Aucune");
            } else {
                sprintBuffer = 0;
            }
        }
    }

}
