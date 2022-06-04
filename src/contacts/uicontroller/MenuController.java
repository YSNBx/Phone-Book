package contacts.uicontroller;

import contacts.ui.SuperInterface;

public class MenuController {
    private SuperInterface superInterface;

    public void setInterface(SuperInterface superInterface) {
        this.superInterface = superInterface;
    }

    public void execute() {
        this.superInterface.start();
    }
}
