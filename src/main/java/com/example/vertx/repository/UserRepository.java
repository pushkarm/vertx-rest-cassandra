package com.example.vertx.repository;

import com.datastax.driver.core.*;
import com.example.vertx.domain.User;
import com.example.vertx.utils.CassandraConnector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pushkarmurkute on 25/04/17.
 */
public class UserRepository {


    public User addUser(User user) throws Exception {


        CassandraConnector connector = null;
        try {

            connector = new CassandraConnector();
            Session session = connector.getSession();

            PreparedStatement statement = session.prepare (
                    "INSERT INTO users(id, username, email, password) VALUES(?, ?, ?, ? ) ");

            BoundStatement boundStatement = statement.bind(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());

            ResultSet resultSet = session.execute(boundStatement);

            System.out.print(resultSet.toString());

        } finally {
            if(null != connector) {
                connector.closeSession();
            }
        }

         return user;
    }

}
