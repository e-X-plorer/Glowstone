package net.glowstone.net.message.play.entity;

import com.flowpowered.network.Message;
import lombok.Data;

import java.util.UUID;

@Data
public final class SpawnPaintingMessage implements Message {

    private final int id;
    private final UUID uniqueId;
    private final String title;
    private final int x;
    private final int y;
    private final int z;
    private final int facing;

}
