package org.example;

import org.example.User;
import org.example.UserService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private User mockUser;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting UserService tests...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("UserService tests completed.");
    }

    @BeforeEach
    void setUp() {
        userService = new UserService();
        mockUser = mock(User.class);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up...");
    }

    // Test for user registration

    @Test
    void testRegisterUser_Successful() {
        when(mockUser.getUsername()).thenReturn("JohnDoe");
        assertTrue(userService.registerUser(mockUser), "User should be registered successfully.");
    }

    @Test
    void testRegisterUser_Failure_DuplicateUsername() {
        when(mockUser.getUsername()).thenReturn("JohnDoe");
        userService.registerUser(mockUser); // Register first time
        assertFalse(userService.registerUser(mockUser), "Duplicate registration should fail.");
    }

    @Test
    void testRegisterUser_EdgeCase_EmptyUsername() {
        when(mockUser.getUsername()).thenReturn("");
        assertTrue(userService.registerUser(mockUser), "User registration should fail with an empty username.");
    }

    // Test for user login

    @Test
    void testLoginUser_Successful() {
        when(mockUser.getUsername()).thenReturn("JohnDoe");
        when(mockUser.getPassword()).thenReturn("password");
        userService.registerUser(mockUser);
        assertNotNull(userService.loginUser("JohnDoe", "password"), "Login should be successful.");
    }

    @Test
    void testLoginUser_Failure_WrongPassword() {
        when(mockUser.getUsername()).thenReturn("JohnDoe");
        when(mockUser.getPassword()).thenReturn("password");
        userService.registerUser(mockUser);
        assertNull(userService.loginUser("JohnDoe", "wrongpassword"), "Login should fail with incorrect password.");
    }

    @Test
    void testLoginUser_EdgeCase_NonExistentUser() {
        assertNull(userService.loginUser("NonExistentUser", "password"), "Login should fail for a non-existent user.");
    }

    // Ignoring test example

    @Test
    void testRegisterUser_Disabled() {
        when(mockUser.getUsername()).thenReturn("JaneDoe");
        assertTrue(userService.registerUser(mockUser), "This test is disabled.");
    }
}
