package Lab3.src.Commands;

import Lab3.src.ConsoleApp;
// import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.CinemaManager;

public class CreateFilmCommand extends AbstractCommand {
    public CreateFilmCommand() {
        super("add_film", true);
        this.setDescription("Adds a new film to the storage");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
        
        System.out.println("What is the name of the film?");
        String filmName = app.input();
        if(manager.findFilm(filmName) != null) {
            System.out.println("Film with the name " + filmName + " already added");
            return;
        }
        System.out.println("How long is the film in minutes?");
        int duration = app.inputInt();

        manager.registerFilm(filmName, duration);
        System.out.println("Successfully added new film!");
    }
}
