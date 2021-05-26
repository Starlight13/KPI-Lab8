import entities.Message;
import entities.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OrdersApiTestSteps {
    private Response response;
    private long id;
    private Order order;


    @Given("order id is {long}")
    public void givenOrderId(long id) {
        this.id = id;
    }

    @When("user tries to get order by id")
    public void getOrderById() {
        response = new ApiEndpoints().getOrderById(id);
    }

    @Then("user receives status code {int}")
    public void compareStatusCodes(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("user receive message {string}")
    public void compareResponseMessage(String expectedMessage) {
        Message message = response.body().as(Message.class);
        Assert.assertEquals(expectedMessage, message.getMessage());
    }

    @Then("response not equal to zero")
    public void checkThatResponseNotNull() {
        response.then().body(Matchers.notNullValue());
    }

    @Given("user tries to create login with next data")
    public void givenDataToOrderCreation(List<List<String>> list) {
        order = new Order(list.get(1).get(0), Integer.parseInt(list.get(1).get(1)));
    }

    @Given("user tries to update login with next data")
    public void givenDataToOrderUpdate(List<List<String>> list) {
        order = new Order(Integer.parseInt(list.get(1).get(0)), list.get(1).get(1), Integer.parseInt(list.get(1).get(2)));
    }


    @When("user does request to get all orders")
    public void userGetAllOrders() {
        response = new ApiEndpoints().getAllOrders();
    }

    @When("user tries to create order")
    public void userCreateOrder() {
        response = new ApiEndpoints().addOrder(order);
    }

    @When("user tries to update order")
    public void userUpdateOrder() {
        response = new ApiEndpoints().updateOrder(order);
    }

    @When("user take last id")
    public void findLastId() {
        List<Order> orders = Arrays.asList(response.body().as(Order[].class));
        orders.sort(Comparator.comparing(Order::getId));
        id = orders.get(orders.size() - 1).getId();
    }

    @When("user tries to delete order")
    public void userDeleteOrder() {
        response = new ApiEndpoints().deleteOrder(id);
    }

}
