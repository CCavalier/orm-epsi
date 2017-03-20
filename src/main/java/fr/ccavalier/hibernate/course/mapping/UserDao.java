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

        String sql = "Select * from users where first_name=:name";//Ecrire votre requete ici pour recuperer le User

        User result = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new UserMapper());

        params.put("user", result.getId());
        result.setContacts(namedParameterJdbcTemplate.query(
                                "SELECT m.*, a.contact from Media m "
                                        +"inner join media_users a on m.id = a.id_media "
                                        + "where a.id_user = :user",
                                params,
                                new MediaMapper()));

        return result;

    }


    public void add(User user) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "Insert into users(first_name, last_name, address) values(:firstName, :lastName, :address)";
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        params.put("address", user.getCity());
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
            User user = new User();
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setId(rs.getInt("id"));
            user.setCity(rs.getString("address"));
            return user;
        }
    }

    private static final class MediaMapper implements RowMapper<User.Media> {

        @Override
        public User.Media mapRow(ResultSet resultSet, int i) throws SQLException {
            User.Media media = new User.Media();
            media.setId(resultSet.getInt("id"));
            media.setType(resultSet.getString("type"));
            media.setValue(resultSet.getString("contact"));
            return media;
        }
    }

}
