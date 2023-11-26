package Model.Entity;

import java.util.List;

public abstract class ItemContainer {
    protected List<Item> items;
    int id;
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public void add(Item it){
        items.add(it);
    }

    public void remove(Item it){

    }

    public void update(Item it, int quantity){

    }

    public double total(){
        double total = 0;
        for(Item it: items){
            total+=it.total();
        }
        return total;
    }
}