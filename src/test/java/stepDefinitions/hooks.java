package stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;




public class hooks {

    @Before("@mobile")
    public void beforeValidation(){
        System.out.println("before mobile");
    }
    @After("@mobile")
    public void afterValidation(){
        System.out.println("After mobile");
    }
    @Before("@reg")
    public void beforeRegValidation(){
        System.out.println("before reg");
    }
    @After("@reg")
    public void afterRegValidation(){
        System.out.println("After reg");
    }

}
