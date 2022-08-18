package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket AG", category = Category.PLAYER)
public class BadPacketAG extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.STEER_VEHICLE) {
            if(!packet.getPlayer().isInsideVehicle()) fail("Packets Impossible", "STREE_VEHICULES NON EXISTANT");
        }
    }

}
