package fr.ccavalier.hibernate.course.mapping;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ccavalie on 31/01/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DatabaseConfiguration.class})
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testFindByName() {

        User user = userDao.findByFirstName("Jean");

        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId().intValue());
        Assert.assertEquals("Jean", user.getFirstName());
        Assert.assertEquals("Perpignan", user.getCity());
        Assert.assertEquals(2, user.getContacts().size());
        Assert.assertEquals("0463626712", user.getContacts().get(0).getValue());

    }

    @Test
    @Transactional
    public void testAddUser(){

        User user = new User();
        user.setFirstName("Lara");
        user.setLastName("Croft");
        user.setCity("Southampton");
        userDao.add(user);

        User userFromDb = userDao.findByFirstName("Lara");
        Assert.assertEquals(user.lastName, userFromDb.lastName);
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();

        Assert.assertNotNull("findAll hasn't return anything, have you fill the missing request?",users);
        Assert.assertEquals("findAll has return an incorrect number of item : "+users.size(),3, users.size());
    }

}