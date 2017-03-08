package fr.ccavalier.hibernate.course.mapping;
//[imports] { autofold

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//}

/**
 * Created by ccavalie on 31/01/2017.
 */
@Repository
public class UserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public User findByFirstName(String name) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        String sql = "";//Ecrire votre requete ici pour recuperer le User

        User result = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new UserMapper());

        return result;

    }


    public void add(User user) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "";
        int value = namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * get all users from database
     *
     * @return List<User> All Users
     */
    public List<User> findAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "Select * from USERS";//write your request here
        List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
        return result;

    }


    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Write the mapper to convert a ResultSet to a User
            return null;
        }
    }

    private static final class MediaMapper implements RowMapper<User.Media> {

        @Override
        public User.Media mapRow(ResultSet resultSet, int i) throws SQLException {
            // Write the mapper to convert a ResultSet to a Media
            return null;
        }
    }

}
