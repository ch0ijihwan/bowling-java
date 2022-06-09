package model.pin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PinCounts {

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
        this.pinCounts.add(input);
        return new PinCounts(this.getPinCounts());
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
