package app.ui.console;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
public class MenuItem {

    private String description;
    private Runnable ui;

    public MenuItem(String description,  Runnable ui)
    {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");

        this.description = description;
        this.ui = ui;
    }

    public void run()
    {
        this.ui.run();
    }

    public boolean hasDescription(String description)
    {
        return this.description.equals(description);
    }

    public String toString()
    {
        return this.description;
    }
}
