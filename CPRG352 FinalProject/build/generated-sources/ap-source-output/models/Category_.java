package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Item;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-13T22:27:05")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Item> itemList;
    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile SingularAttribute<Category, Integer> categoryId;

}