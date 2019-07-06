package us.blockgame.lcemotes.emote;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public static boolean exists(String name) {
        for (Emote emote : Emote.values()) {
            if (emote.toString().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Emote getByName(String name) {
        for (Emote emote : Emote.values()) {
            if (emote.toString().equalsIgnoreCase(name)) {
                return emote;
            }
        }
        return null;
    }

}
