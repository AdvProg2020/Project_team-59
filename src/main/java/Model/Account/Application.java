package Model.Account;

import java.util.ArrayList;

public class Application {
    private String requestId;
    private ArrayList<String> requestDescription;
    private ApplicationState applicationState;

    public Application(String requestId, ArrayList<String> requestDescription) {
        this.requestId = requestId;
        this.requestDescription = requestDescription;
        this.applicationState = ApplicationState.TO_BE_APPROVED;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public ArrayList<String> getRequestDescription() {
        return requestDescription;
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }
}
