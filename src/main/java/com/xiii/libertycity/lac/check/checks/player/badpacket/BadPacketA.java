package com.xiii.libertycity.lac.check.checks.player.badpacket;

import com.xiii.libertycity.lac.check.Category;
import com.xiii.libertycity.lac.check.Check;
import com.xiii.libertycity.lac.check.CheckInfo;
import com.xiii.libertycity.lac.utils.SampleList;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket A", category = Category.PLAYER)
public class BadPacketA extends Check {

    double packetC;

    long lastMs = System.currentTimeMillis();
    long lastPosition = System.currentTimeMillis();
    double bal;
    double lastBal;
    SampleList<Double> balances = new SampleList(4);

    public void onPacket(PacketPlayReceiveEvent packet) {

        if (packet.getPacketId() == -93) {
            long diff = System.currentTimeMillis() - this.lastMs;
            long diff2 = System.currentTimeMillis() - this.lastPosition;
            this.lastBal = this.bal;
            this.bal += (50L - diff);
            if (this.bal >= 5.0D && diff2 < 400L) {
                this.balances.add(Double.valueOf(this.bal));
                if (this.balances.isCollected())
                    if (this.balances.getAverageDouble(this.balances) >= 50.0D) {
                        packetC += 1;
                        if(packetC > 2) fail("Spam de packets", "bal=" + this.bal);
                    } else {
                        if(packetC >= 0.1) packetC -= 0.1;
                    }
                this.bal = 0.0D;
            }
            this.lastMs = System.currentTimeMillis();
        } else if (packet.getPacketId() == -96 || packet.getPacketId() == -95 || packet.getPacketId() == -94) {
            if (this.bal < -100.0D)
                this.bal = -20.0D;
            this.lastPosition = System.currentTimeMillis();
        }

    }

}
