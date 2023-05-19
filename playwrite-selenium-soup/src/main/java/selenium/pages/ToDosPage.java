package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToDosPage extends BasePageSl {

    public ToDosPage(WebDriver localDriver) {
        super(localDriver);
    }
//
//    public ToDosPage addName(String name){
//        type(By.id(Locators.nameField), name);
//        return this;
//    }
//
//    public ToDosPage createToDo(String toDo, boolean isBusiness ){
//        type(By.id(Locators.addItemField), toDo);
//
//        if(isBusiness) click( By.cssSelector(Locators.bussinessCheckboxField));
//        else click(By.cssSelector(Locators.personalCheckboxField));
//
//        getWebElClickable(By.cssSelector(Locators.addToDoButton),2);
//        click(By.cssSelector(Locators.addToDoButton));
//        return this;
//    }
}
