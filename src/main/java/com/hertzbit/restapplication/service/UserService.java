package com.hertzbit.restapplication.service;

import com.hertzbit.restapplication.exceptions.UserNotFoundException;
import com.hertzbit.restapplication.model.Address;
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

        //Dummy PhoneNumbers
        String[] contactAnthony = {"+44 7911 123456"};
        String[] contactRonaldo = {"+351 21 234 892 6992"};
        String[] contactKohli = {"+91 999 888 7777", "+91 111 222 3333"};
        String[] contactPerez = {"+52 686.553-3847"};
        String[] contactFederer = {"+41 044 909 24 49"};
        String[] contactNehwal = {"+91 888 999 0000"};

        //Dummy Address
        Address addressAnthony = new Address("1A", "10 Downing Street", "London",
                "London", "England", "EC1A");
        Address addressRonaldo = new Address("1243/Z", "R Doutor Alfredo Freitas 17", "Arrifana",
                "Aveiro", "Portugal", "3700-474");
        Address addressKohli = new Address("C-1254", "Sushant Lok Phase 1", "Gurgaon",
                "Haryana", "India", "121001");
        Address addressPerez = new Address("32/B", "H COLEGIO MILITAR NO. 987 NO. B, PUEBLO NUEVO, 21120",
                "Baja California",  "Mexicali", "México", "21120");
        Address addressFederer = new Address("SD-12", "Gerbiweg 17", "Bülach",
                "NA", "Switzerland", "8186");
        Address addressNehwal = new Address("32-B", "Bima Complex, Kalamboli, Kalamboli, Navi Mumbai", "Mumbai",
                "Maharashtra", "India", "410218");

        //Dummy Users
        User userAnthony = new User (1, "Anthony", "Joshua", 100000, addressAnthony, contactAnthony);
        User userRonaldo = new User (2, "Cristiano", "Ronaldo", 500000, addressRonaldo, contactRonaldo);
        User userKohli = new User (3, "Virat", "Kohli", 250000, addressKohli, contactKohli);
        User userPerez = new User (4, "Sergio", "Perez", 90000, addressPerez, contactPerez);
        User userFederer = new User (5, "Roger", "Federer", 120000, addressFederer, contactFederer);
        User userSaina = new User (6, "Saina", "Nehwal", 50000, addressNehwal, contactNehwal);

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
            user.setUserId(userId);
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
