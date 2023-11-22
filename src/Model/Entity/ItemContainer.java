package Model.Entity;

public abstract class ItemContainer {
    protected Item[] items;

    public Item[] getItems() { return items; }
    public void setItems(Item[] items) { this.items = items; }
}