package com.ice.shamim.programmingguide.MainMenu.Fragment.Course;


import com.ice.shamim.programmingguide.MainMenu.MainMenu;

import java.util.ArrayList;
import java.util.List;

import static com.ice.shamim.programmingguide.MainMenu.MainMenu.*;

public class OurData extends MainMenu {

    MainMenu mainMenu = (MainMenu) getApplicationContext();
    public int a = mainMenu.getSelectedItem();

    List<Product> productList;

    OurData() {



            if(a==1) {

                productList = new ArrayList<>();
                //adding some items to our list
                productList.add(
                        new Product(
                                "Fundamental",
                                "C programming"
                        ));

                productList.add(
                        new Product(
                                "Data-type",
                                ""
                        ));

                productList.add(
                        new Product(
                                "Variables",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "TestModel",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Array",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Operators",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Function",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Strings",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Enum, Struct and Union",
                                "C programming"
                        ));
                productList.add(
                        new Product(
                                "Pointers",
                                "C programming"
                        ));

            }
            else if(a==3){
                productList = new ArrayList<>();


                //adding some items to our list
                productList.add(
                        new Product(
                                "Fundamental",
                                "C programming"
                        ));

                productList.add(
                        new Product(
                                "Data-type",
                                "Java"
                        ));

                productList.add(
                        new Product(
                                "Variables",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "TestModel",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Array",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Operators",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Function",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Strings",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Enum, Struct and Union",
                                "Java"
                        ));
                productList.add(
                        new Product(
                                "Pointers",
                                "Java"
                        ));

            }


        }




}
