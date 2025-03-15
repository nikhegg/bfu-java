package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public abstract class AbstractCommand {
    private String name;
    private String description;
    private boolean isAdmin;

    public AbstractCommand(String name, boolean isAdmin) {
        this.name = name.toLowerCase();
        this.description = "No description provided";
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdminOnly() {
        return this.isAdmin;
    }

    public void execute(ConsoleApp app) {}
}
