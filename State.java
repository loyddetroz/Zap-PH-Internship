public abstract class State {
    public ResultState process(String input, String command, int prevState) {
        return new ResultState();
    }
}
