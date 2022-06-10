package model.pin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PinCounts {

    private static final int FIRST_INDEX = 0;
    private static final int MAXIMUM_PIN_COUNT = 10;

    private final List<PinCount> pinCounts;

    public PinCounts(final List<PinCount> pinCounts) {
        this.pinCounts = new ArrayList<>(pinCounts);
    }

    public List<PinCount> getPinCounts() {
        return new ArrayList<>(pinCounts);
    }


    public int getSize() {
        return pinCounts.size();
    }

    public int calculatePinCounts() {
        return pinCounts.stream()
                .mapToInt(PinCount::getValue)
                .sum();
    }

    public PinCounts add(final PinCount input) {
        if(this.isStrike()){
            throw new IllegalArgumentException("스트라이크 이후에는 투구를 할 수 없습니다.");
        }
        this.pinCounts.add(input);
        return new PinCounts(this.getPinCounts());
    }

    public boolean isStrike() {
        return pinCounts.get(FIRST_INDEX)
                .isStrike();
    }

    public boolean isSpare() {
        return sumPinCountValues() == MAXIMUM_PIN_COUNT;
    }

    private int sumPinCountValues() {
        return pinCounts.stream()
                .mapToInt(PinCount::getValue)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinCounts pinCounts1 = (PinCounts) o;
        return Objects.equals(pinCounts, pinCounts1.pinCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCounts);
    }
}
