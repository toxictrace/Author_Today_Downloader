package com.toxictrace.atdownloader.network;

import retrofit2.Call;
import retrofit2.http.*;

public interface AuthorTodayApi {
    // Endpoint to get book data
    @GET("books")
    Call<List<Book>> getBooks();

    // Endpoint for user authentication
    @POST("auth/login")
    Call<User> authenticateUser(@Body UserCredentials userCredentials);
}

// Model classes for Book and User
class Book {
    private String title;
    private String author;
    // Add other fields, constructors, getters, and setters
}

class User {
    private String username;
    private String token;
    // Add other fields, constructors, getters, and setters
}

class UserCredentials {
    private String username;
    private String password;
    // Add constructors, getters, and setters
}