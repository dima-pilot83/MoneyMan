package dima.entity.enums;


/**
 * Created by Dmitriy on 18.05.2018.
 */

public enum PipelineStatus {
    PENDING, INPROGRESS, SKIPPED, FAILED, COMPLETED;

    @Override
    public String toString() {
        return "STATUS_" + name();
    }
}
