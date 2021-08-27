package me.william278.huskhomes2.teleport;

import me.william278.huskhomes2.HuskHomes;

import java.time.Instant;

public class TeleportRequest {

    private final String senderName;
    private final long requestExpiryTime;
    private final RequestType requestType;

    public TeleportRequest(String senderName, RequestType requestType) {
        this.senderName = senderName;
        this.requestType = requestType;
        long currentUnixTime = Instant.now().getEpochSecond();
        requestExpiryTime = currentUnixTime + HuskHomes.getSettings().getTeleportRequestExpiryTime();
    }

    public String getSenderName() {
        return senderName;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public boolean isExpired() {
        long currentUnixTime = Instant.now().getEpochSecond();
        return currentUnixTime > requestExpiryTime;
    }

    public enum RequestType {
        TPA,
        TPA_HERE
    }
}