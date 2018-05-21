package dima.entity.enums;


public enum TaskStatus {
    PENDING, INPROGRESS, SKIPPED, FAILED, COMPLETED;

    public static TaskStatus getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    @Override
    public String toString() {
        return "STATUS_" + name();
    }
}
