package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.abilities.WrappedPacketInAbilities;

@CheckInfo(name = "BadPacket AF", category = Category.PLAYER)
public class BadPacketAF extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.ABILITIES) {
            WrappedPacketInAbilities wrapper = new WrappedPacketInAbilities(packet.getNMSPacket());
            if(wrapper.isFlying() && !packet.getPlayer().isFlying()) fail("Packets Impossible", "ABILITIES");
        }
    }

}
