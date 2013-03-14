/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.User;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface UserDao {

    public void createUser(User user) throws DataAccessException;

    public void deleteUser(User user) throws DataAccessException;

    public void updateUser(User user) throws DataAccessException;

    public User getUserById(Long id) throws DataAccessException;

    public User getUserByEmail(String email) throws DataAccessException;

    public User getUserByFbUserId(String fbUserId) throws DataAccessException;

    public User getUserByResetString(String resetString) throws DataAccessException;

    public List<User> getAllUsers() throws DataAccessException;
}
