package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDaoFactory().awsUserDao();

        //컬럼삭제
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());

        User user = new User("1", "EternityHwan","1123");
        userDao.add(user);
        assertEquals(1,userDao.getCount());

        User selectedUser = userDao.get("1");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());
        Assertions.assertEquals("1123", selectedUser.getPassword());


    }
}