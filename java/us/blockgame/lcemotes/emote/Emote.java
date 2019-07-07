package us.blockgame.lcemotes.emote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Emote {

    WAVE(0),
    HANDS_UP(1),
    FLOSS(2),
    DAB(3),
    T_POSE(4),
    SHRUG(5),
    FACEPALM(6);

    public int id;

    public static Emote getByName(String name) {
        return Stream.of(Emote.values()).filter(emote -> emote.toString().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
