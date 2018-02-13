package fr.ccavalier.hibernate.course.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by charlotte on 20/02/17.
 */
@Repository
public class Requests {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public Requests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * retourner les numéros et libellés des articles dont le stock est inférieur à 10
     *
     */
    public List findNameQuantityInf10() {
        String sql = "";//Ecrire votre requete ici
        List result = jdbcTemplate.queryForList(sql);
        return result;

    }

    /**
     * retourner les noms et adresses des fournisseurs qui proposent des articles
     * pour lesquels le délai d'approvisionnement est supérieur à 20 jours
     */
    public List findFourDelaiSup20(){
        String sql = "";
        List result = jdbcTemplate.queryForList(sql);
        return result;
    }

    /**
     * Supprimer les fraises de la table des produits
     */
    public void deleteFraises(){
        String sql = "";//Ecrire votre requete ici
        jdbcTemplate.execute(sql);
        sql = "";//Ecrire votre requete ici
        jdbcTemplate.execute(sql);
    }

    public List queryForList(String sql){
        return jdbcTemplate.queryForList(sql);
    }

}
