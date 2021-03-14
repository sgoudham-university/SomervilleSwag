package org.somerville.swag.data.entities;

import org.somerville.swag.data.state.GuestState;
import org.somerville.swag.data.state.LoggedInState;
import org.somerville.swag.data.state.State;

public class Customer  {
    //singleton and state design will need to go in here
    private String Username;
    private String Password;
    private State state;

    public Customer() {
        this.state = new GuestState(this);
    }

    public Customer(String username, String password) {
        this.Username = username;
        this.Password = password;
        this.state = new LoggedInState(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
