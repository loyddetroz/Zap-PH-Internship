public abstract class State {
    public ResultState process(String input, String command) {
        return new ResultState();
    }
}
