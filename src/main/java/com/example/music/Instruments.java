package com.example.music;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Instruments {
    private final StringProperty imagePath = new SimpleStringProperty();
    private final StringProperty lbDescription = new SimpleStringProperty();
    private final StringProperty buttonText = new SimpleStringProperty();
    private final StringProperty lbMore = new SimpleStringProperty();

    public String getLbMore() {
        return lbMore.get();
    }

    public StringProperty lbMoreProperty() {
        return lbMore;
    }

    public void setLbMore(String lbMore) {
        this.lbMore.set(lbMore);
    }

    public String getImagePath() {
        return imagePath.get();
    }
    public StringProperty imagePathProperty() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }



    public String getLbDescription() {
        return lbDescription.get();
    }

    public StringProperty ibDescriptionProperty() {
        return lbDescription;
    }

    public void setLbDescription(String lbDescription) {
        this.lbDescription.set(lbDescription);
    }

    public String getButtonText() {
        return buttonText.get();
    }

    public StringProperty buttonTextProperty() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText.set(buttonText);
    }
}
