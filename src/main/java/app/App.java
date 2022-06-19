package app;

import controller.Controller;
import view.input.ConsoleInput;
import view.input.Input;
import view.output.ConsoleOutput;
import view.output.Output;

public class App {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Controller controller = new Controller(input, output);
        controller.run();
    }
}
