package org.modelmapper.bugs;

import java.util.UUID;

public class UUIDObjDTO {
    private UUID uuid;
    public UUIDObjDTO(){
        this.uuid = UUID.randomUUID();
    }
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
