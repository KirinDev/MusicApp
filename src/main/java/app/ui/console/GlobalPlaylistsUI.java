package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GlobalPlaylistsUI implements Runnable {

    public GlobalPlaylistsUI() { }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Create a new PlayList" , new CreatePlaylistUI() ));
        options.add(new MenuItem("Add music to PlayList" , new AddToPlaylistUI() ));

        int option = 0;
        do {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

                if ( (option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } catch (Exception e){System.out.print("Invalid option! Try again");}
        }
        while (option != -1 );
    }
}
