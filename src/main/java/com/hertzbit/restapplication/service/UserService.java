package com.hertzbit.restapplication.service;

import com.hertzbit.restapplication.exceptions.UserNotFoundException;
import com.hertzbit.restapplication.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private Map<Integer, User> userDatabase = null;

    public UserService() {

        this.userDatabase = new HashMap<>();

        //Dummy Users
        User userAnthony = new User (1, "Anthony", "Joshua", "Boxing", "England", 100000);
        User userRonaldo = new User (2, "Cristiano", "Ronaldo", "Football", "Portugal", 500000);
        User userKohli = new User (3, "Virat", "Kohli", "Cricket", "India", 250000);
        User userPerez = new User (4, "Sergio", "Perez", "Formula 1", "Mexico", 90000);
        User userFederer = new User (5, "Roger", "Federer", "Tennis", "Switzerland", 120000);
        User userSaina = new User (6, "Saina", "Nehwal", "Badminton", "India", 50000);

        this.userDatabase.put(userAnthony.getUserId(), userAnthony);
        this.userDatabase.put(userRonaldo.getUserId(), userRonaldo);
        this.userDatabase.put(userKohli.getUserId(), userKohli);
        this.userDatabase.put(userPerez.getUserId(), userPerez);
        this.userDatabase.put(userFederer.getUserId(), userFederer);
        this.userDatabase.put(userSaina.getUserId(), userSaina);
    }

    public List<User> getAllUsers () {

        List<User> userList = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : this.userDatabase.entrySet()) {
            userList.add(entry.getValue());
        }
        return userList;
    }

    public User getSpecificUser (Integer userId) {

        if (this.userDatabase.containsKey(userId)) {
            return this.userDatabase.get(userId);
        } else {
            throw new UserNotFoundException("User with userId : " + userId + " doesn't exists.");
        }
    }

    public User createUser (User user) {

        int currentSizeOfDatabase = this.userDatabase.size();
        user.setUserId(currentSizeOfDatabase + 1);
        this.userDatabase.put(user.getUserId(), user);
        return user;
    }

    public User updateUser (Integer userId, User user) {

        if (this.userDatabase.containsKey(userId)) {
            this.userDatabase.put(userId, user);
            return user;
        } else {
            throw new UserNotFoundException("User with userId : " + userId + " doesn't exists.");
        }
    }

    public void deleteUser (Integer userId) {

        if (this.userDatabase.containsKey(userId)) {
            this.userDatabase.remove(userId);
        } else {
            throw new UserNotFoundException("User with userId : " + userId + " doesn't exists.");
        }
    }
}
