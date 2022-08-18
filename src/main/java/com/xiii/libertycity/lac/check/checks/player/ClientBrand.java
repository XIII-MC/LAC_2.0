package com.xiii.libertycity.lac.check.checks.player;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.custompayload.WrappedPacketInCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CheckInfo(name = "Client Brand", category = Category.PLAYER)
public class ClientBrand extends Check {

    String brand = "vanilla";
    boolean hasBrand = false;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.CUSTOM_PAYLOAD) {
            WrappedPacketInCustomPayload wrapper = new WrappedPacketInCustomPayload(packet.getNMSPacket());

            String channelName;
            Object channelObject = wrapper.getChannelName();
            if (channelObject != null) {
                channelName = (String) channelObject;
            } else {
                channelName = "Unknown";
            }

            if (channelName.equalsIgnoreCase("minecraft:brand") || // 1.13+
                    wrapper.getChannelName().equals("MC|Brand")) { // 1.12

                byte[] data = wrapper.getData();

                if (data.length == 0) {
                    brand = "received empty brand";
                    return;
                }

                byte[] minusLength = new byte[data.length - 1];
                System.arraycopy(data, 1, minusLength, 0, minusLength.length);

                brand = new String(minusLength).replace(" (Velocity)", "");

                if (!hasBrand) {
                    hasBrand = true;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("LibertyCity.LAC.clientbrandalert")) p.sendMessage("§e§lLAC §8» §f" + packet.getPlayer().getName() + " §7a rejoint avec §f" + brand);
                    }
                }
            }
        }
    }

    public String getBrand() {
        return brand;
    }

}
