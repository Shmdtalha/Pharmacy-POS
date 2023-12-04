package Service;

import View.BaseView;

/**
 * The BaseService class serves as an abstract base for all service classes in the application.
 * It provides a framework for initializing services with a specified view, ensuring the correct
 * view type, setting up dialog boxes, adding listeners, and refreshing the view.
 */
public abstract class BaseService {
    protected BaseView view;

    /**
     * Constructs a BaseService with the specified view.
     * It checks the view type, sets child references, loads dialog boxes, and initializes the service.
     *
     * @param view The view associated with this service.
     */
    public BaseService(BaseView view) {
        checkViewType(); //Makes sure that correct view has been added
        this.view = view; //Passes view of type BaseView
        setChildReference(); //Passes view of type child, for ease. Not that hard to understand, Ahmed.
        loadDialogBoxes();
        initialize(); //Refresh and add listener
    }

    /**
     * Loads dialog boxes required by the service.
     */
    public abstract void loadDialogBoxes();

    /**
     * Loads dialog boxes required by the service.
     * Importnat because it had to be done before super() ends in the child classes
     */
    protected abstract void setChildReference();

    /**
     * Loads dialog boxes required by the service.
     */
    protected abstract void checkViewType();

    /**
     * Adds necessary listeners to the view.
     * This can include Key, Action and Mouse events etc
     */
    protected abstract void addListeners();
    /**
     * Adds necessary listeners to the view.
     */
    public abstract void refreshView();

    /**
     * Adds necessary listeners to the view.
     */
    public void initialize(){
        refreshView();
        addListeners();
    }
}
