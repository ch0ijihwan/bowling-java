package model.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerName {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Z]*$");
    private static final int NAME_SIZE = 3;

    private final String name;

    public PlayerName(final String name) {
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(final String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        if (!matcher.matches() || name.length() != NAME_SIZE) {
            throw new IllegalStateException("플레이어의 이름은 알파벳 대문자로 구성된 3자리 이니셜 입니다.");
        }
    }

    public String getName() {
        return name;
    }
}
