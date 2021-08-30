package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;


import java.util.List;


public class stepDefinition {
    @Given("^User is on netbanking login page$")
    public void user_is_on_netbanking_login_page() throws Throwable {
        System.out.println("User is on netbanking login page success");

        //throw new PendingException();
    }

    @When("^User log in into application using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_log_in_into_application_using_something_and_something(String strArg1, String strArg2) throws Throwable {
        System.out.println(strArg1);
        System.out.println(strArg2);
        //throw new PendingException();
    }

    @Then("^Homepage is populated$")
    public void homepage_is_populated() throws Throwable {
       //throw new PendingException();
    }

    @And("^Cards displayed \"([^\"]*)\"$")
    public void cards_displayed_something(String strArg1) throws Throwable {
        //throw new PendingException();
        System.out.println(strArg1);
    }
    @Then("^User sign up with following details$")
    public void user_sign_up_with_following_details(DataTable data) throws Throwable {
        List <List<String>> obj = data.asLists();
        System.out.println(obj.get(0).get(0));
        System.out.println(obj.get(0).get(1));
        System.out.println(obj.get(0).get(2));
        System.out.println(obj.get(0).get(3));
        System.out.println(obj.get(0).get(4));
        System.out.println(obj.get(0).get(5));

        //throw new PendingException();
    }

    @When("^User log in into application with (.+) and (.+)$")
    public void user_log_in_into_application_with_and(String username, String password) throws Throwable {
        System.out.println(username);
        System.out.println(password);

        //throw new PendingException();
    }
    @Given("^browser is opened$")
    public void browser_is_opened() throws Throwable {
        System.out.println("browser is opened");
        //throw new PendingException();
    }

    @When("^browser cache clean triggered$")
    public void browser_cache_clean_triggered() throws Throwable {
        System.out.println("browser cache clean triggered");
        //throw new PendingException();
    }

    @Then("^browser started as fresh$")
    public void browser_started_as_fresh() throws Throwable {
        System.out.println("browser started as fresh");
        //throw new PendingException();
    }

}
