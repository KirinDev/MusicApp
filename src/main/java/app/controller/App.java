package app.controller;

import app.auth.AuthFacade;
import app.auth.UserSession;
import app.domain.model.KirinDev;
import app.domain.shared.Constants;
import app.properties.PropertiesCache;

public class App {

    private KirinDev kirin;
    private AuthFacade authFacade;

    private App() {

        try {
            PropertiesCache props = new PropertiesCache();
            this.kirin = new KirinDev(props.getProperty(Constants.PARAMS_KIRINDEV_DESIGNATION));
            this.authFacade = this.kirin.getAuthFacade();

        }catch(Exception e){
            e.printStackTrace();
        }
        bootstrap();
    }

    public KirinDev getKirinDev()
    {
        return this.kirin;
    }

    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_USER,Constants.ROLE_USER);
        this.authFacade.addUserWithRole("Software User", "weeb@shows.pt", "1811",Constants.ROLE_USER);
    }

    private static App singleton = null;
    public static App getInstance() {
        if(singleton == null) {
            synchronized(App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }
}
