import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int health;
    private final int weapons;
    private final int lvl;
    private final transient double distance;
}