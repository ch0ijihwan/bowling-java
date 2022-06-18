package model.state.running;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.ended.Strike;

public class FirstPitch extends RunningState {

    public static RunningState create(){
        return new FirstPitch();
    }

    private FirstPitch(){}

    @Override
    public BowlingState bowl(final PinCount pinCount) {
        if(pinCount.isStrike()){
            return Strike.create();
        }
        return SecondPitch.create(pinCount);
    }

    @Override
    public String getScoreSymbol() {
        throw new IllegalStateException("투구 중에는 점수를 확인 할 수 없습니다.");
    }
}
