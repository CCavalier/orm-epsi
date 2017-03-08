package fr.ccavalier.hibernate.course.mapping;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.junit.Assert.fail;

/**
 * Created by ccavalie on 31/01/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DatabaseConfiguration.class})
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    public <T, V> void assertMethodExistAndTestExecution(T instance, String methodName, Consumer<V> assertion) {
        try {
            Method getContact = instance.getClass().getMethod(methodName);
            assertion.accept((V) getContact.invoke(instance));
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            fail(instance.getClass().getSimpleName()+ " does not implement method "+methodName);
        }
    }

    @Test
    public void testFindByName() {
        User user = userDao.findByFirstName("Jean");

        Assert.assertNotNull("le user renvoyé est null", user);
        Assert.assertEquals("l'attribut id de l'objet User n'est pas renseigné",1, user.getId().intValue());
        Assert.assertEquals("l'attribut firstName de l'objet User n'est pas renseigné","Jean", user.getFirstName());
        Assert.assertEquals("l'attribut city de l'objet User n'est pas renseigné","Perpignan", user.getCity());
    }

    @Test
    public void testMediaMappingOnFindByName() {
        User user = userDao.findByFirstName("Jean");

        Assert.assertNotNull("le user renvoyé est null", user);
        Assert.assertEquals("l'attribut id de l'objet User n'est pas renseigné",1, user.getId().intValue());
        Assert.assertEquals("l'attribut firstName de l'objet User n'est pas renseigné","Jean", user.getFirstName());
        Assert.assertEquals("l'attribut city de l'objet User n'est pas renseigné","Perpignan", user.getCity());
        assertMethodExistAndTestExecution(user, "getContacts", (List<?> list) -> {
            Assert.assertEquals(2, list.size());
            assertMethodExistAndTestExecution(list.get(0), "getValue", (String s) -> Assert.assertEquals("0463626712", s));
            assertMethodExistAndTestExecution(list.get(1), "getValue", (String s) -> Assert.assertEquals("0663233212", s));
        });

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
