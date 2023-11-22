package Service;

import View.BaseView;

public abstract class BaseService {
    protected BaseView view;

    public BaseService(BaseView view) {
        checkViewType();
        this.view = view;
    }

    protected abstract void checkViewType();

    protected abstract void addListeners();
    public abstract void refreshView();

    public void initialize(){
        refreshView();
        addListeners();
    }
}
