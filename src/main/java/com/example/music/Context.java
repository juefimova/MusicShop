package com.example.music;

public class Context {
    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    private static Context instance = null;

    private Context() {

    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    Errors errors;
    ErrorController errorController;
    LoginMainController loginMainController;
    StringMore stringMore;
    InstrumentsCell instrumentsCell;
    StringInfo stringInfo;
    HomeController homeController;

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public StringInfo getStringInfo() {
        return stringInfo;
    }

    public void setStringInfo(StringInfo stringInfo) {
        this.stringInfo = stringInfo;
    }

    public InstrumentsCell getInstrumentsCell() {
        return instrumentsCell;
    }

    public void setInstrumentsCell(InstrumentsCell instrumentsCell) {
        this.instrumentsCell = instrumentsCell;
    }

    public StringMore getStringMore() {
        return stringMore;
    }

    public void setStringMore(StringMore stringMore) {
        this.stringMore = stringMore;
    }




    public LoginMainController getLoginMainController() {
        return loginMainController;
    }

    public void setLoginMainController(LoginMainController loginMainController) {
        this.loginMainController = loginMainController;
    }

    public ErrorController getErrorController() {
        return errorController;
    }

    public void setErrorController(ErrorController errorController) {
        this.errorController = errorController;
    }

    Mailer mailer;

    public Mailer getMailer() {
        return mailer;
    }

    public void setMail(Mailer mailer) {
        this.mailer = mailer;
    }

    ForgotPasswordController forgot;

    public ForgotPasswordController getForgotPasswordController() {
        return forgot;
    }

    public void setForgotPasswordController(ForgotPasswordController forgot) {
        this.forgot = forgot;
    }

    HomepageController homepageController;

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }


}
