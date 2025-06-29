
public class StatusService {
    private ExternalApi api;

    public StatusService(ExternalApi api) {
        this.api = api;
    }

    public String[] checkStatusMultipleTimes() {
        return new String[] {
            api.getStatus(),
            api.getStatus(),
            api.getStatus()
        };
    }
}
