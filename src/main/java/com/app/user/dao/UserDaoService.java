package com.app.user.dao;

import com.app.user.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    //UserDaoService talks to static array list instead of database
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static{
        users.add(new User(++usersCount, "Anita", LocalDate.now().minusYears(28)));
        users.add(new User(++usersCount, "Test", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Test1", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Test2", LocalDate.now().minusYears(20)));
    }

    //get all users
    public List<User> findAll(){
        return users;
    }

    //get detail of single user
    public User findUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        //functional programming
        return users.stream()
                .filter(predicate).findFirst().get();
    }

    //add a user
    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
