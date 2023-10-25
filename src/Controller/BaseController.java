package Controller;

import View.BaseView;

public abstract class BaseController {
    protected BaseView view;

    public BaseController(BaseView view) {
        this.view = view;
    }

    protected abstract void addListeners();
    public abstract void refreshView();

    public void initialize(){
        refreshView();
        addListeners();
    }
}
