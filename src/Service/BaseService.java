package Service;

import View.BaseView;

public abstract class BaseService {
    protected BaseView view;

    public BaseService(BaseView view) {
        checkViewType(); //Makes sure that correct view has been added
        this.view = view; //Passes view of type BaseView
        setChildReference(); //Passes view of type child, for ease. Not that hard to understand, Ahmed.
        initialize(); //Refresh and add listener
    }

    protected abstract void setChildReference();

    protected abstract void checkViewType();

    protected abstract void addListeners();
    public abstract void refreshView();

    public void initialize(){
        refreshView();
        addListeners();
    }
}
