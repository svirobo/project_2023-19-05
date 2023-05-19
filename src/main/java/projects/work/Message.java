package projects.work;

public record Message(Identifiable item, Operation operation, Class<? extends Identifiable> type) {

    public enum Operation {
        ADD,
        PRINT_ALL,
        DELETE_ALL
    }
}
