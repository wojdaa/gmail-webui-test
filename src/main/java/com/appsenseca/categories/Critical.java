package com.appsenseca.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by wojdaa on 2016-02-24.
 */

@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public class Critical {
}
