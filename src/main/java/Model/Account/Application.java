package Model.Account;

import java.util.ArrayList;

public class Application {
    private static int requestNumber = 0;
    private int requestId;
    private ArrayList<String> requestDescription;
    private ApplicationState applicationState;
    private ApplicationType applicationType;

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public Application( ArrayList<String> requestDescription , ApplicationType applicationType) {
        this.requestId = requestNumber++;
        this.requestDescription = requestDescription;
        this.applicationState = ApplicationState.TO_BE_APPROVED;
        this.applicationType = applicationType;
    }

    public int getRequestId() {
        return requestId;
    }

    public ArrayList<String> getRequestDescription() {
        return requestDescription;
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }
}
