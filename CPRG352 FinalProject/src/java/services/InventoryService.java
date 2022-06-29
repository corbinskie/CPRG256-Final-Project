package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

public class InventoryService {

    public List<Item> getAll(String email) throws Exception {
        ItemDB itemsDB = new ItemDB();
        List<Item> items = itemsDB.getAll(email);
        return items;
    }

    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categoryList = categoryDB.getAll();
        return categoryList;
    }

    public Item getFromID(int itemId) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.getFromID(itemId);
        return item;
    }

    public void insert(String email, int categoryId, String itemName, double price) throws Exception {
        Item item = new Item(0, itemName, price);
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryId);
        item.setOwner(user);
        item.setCategory(category);

        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }

    public void update(String email, int categoryId, String itemName, double price) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.getEmail(email);
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryId);
        item.setCategory(category);
        item.setItemName(itemName);
        item.setPrice(price);

        itemDB.update(item);
    }

    public void delete(int itemId) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.getFromID(itemId);
        itemDB.delete(item);

    }

}
