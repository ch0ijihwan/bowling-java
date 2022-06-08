package model.bowl;

public class CountOfPin {

    private final int value;
    public CountOfPin(final int countOfPin) {
        if (countOfPin < 0 || countOfPin > 10) {
            throw new IllegalArgumentException("볼링 핀의 갯수는 최대 10인 양수입니다.");
        }
        this.value = countOfPin;
    }

    public int getValue(){
        return value;
    }
}
