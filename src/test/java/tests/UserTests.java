package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.UserService;

public class UserTests extends BaseTest {
    private UserService userService;

    public UserTests() {
        userService = new UserService(authToken);
    }

    @Test
    public void testGetUser() {
        test = extent.createTest("Test Get User");
        
        Response response = userService.getUser(2);  // Get user with ID 2 (ReqRes sample)
        test.log(Status.INFO, "Fetching user with ID 2");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        test.log(Status.PASS, "Status code is 200");
        
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
        test.log(Status.PASS, "User first name is Janet");
    }

    @Test
    public void testCreateUser() {
        test = extent.createTest("Test Create User");

        String payload = "{\"name\": \"John Doe\", \"job\": \"Software Developer\"}";
        Response response = userService.createUser(payload);
        
        test.log(Status.INFO, "Creating user with payload: " + payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        test.log(Status.PASS, "User created successfully with status code 201");
    }
}
