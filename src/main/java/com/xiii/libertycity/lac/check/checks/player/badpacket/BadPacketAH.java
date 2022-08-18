package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.GameMode;

@CheckInfo(name = "BadPacket AH", category = Category.PLAYER)
public class BadPacketAH extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.SPECTATE) {
            if(packet.getPlayer().getGameMode() != GameMode.SPECTATOR) fail("Packets Impossible", "SPECTATE");
        }
    }

}
