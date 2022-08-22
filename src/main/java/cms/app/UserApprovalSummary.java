package cms.app;

public class UserApprovalSummary {
    int type;
    String username;
    String name;

    public UserApprovalSummary(int type, String username, String name) {
            this.type = type;
            this.username = username;
            this.name = name;
    }
}
