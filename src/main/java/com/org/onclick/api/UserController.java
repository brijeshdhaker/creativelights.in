package com.org.onclick.api;

import com.org.onclick.entities.User;
import com.org.onclick.services.UserService;
import static com.org.onclick.api.ApiController.ALL_USERS_ENDPOINT;
import com.org.onclick.exceptions.UserExistsException;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController extends ApiController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = ALL_USERS_ENDPOINT, method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        LOG.info("getting all users");
        List<User> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            LOG.info("no users found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = ADD_USER_ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) throws Exception {
        LOG.info("creating new user: {}", user);
        ResponseEntity<Void> response = null;
        try {

            userService.addUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI("https://localhost:9443/mysbapp/" + ADD_USER_ENDPOINT));
            response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);

        } catch (UserExistsException e) {
            LOG.info("User with name " + user.getFirstName() + " already exists");
            response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        return response;
    }

    @RequestMapping(value = GET_USER_ENDPOINT, method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        LOG.info("getting user with id: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = GET_USER_ENDPOINT, method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        LOG.info("updating user: {}", user);
        User currentUser = userService.getUserById(id);

        if (currentUser == null) {
            LOG.info("User with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setId(user.getId());
        currentUser.setFirstName(user.getFirstName());
        userService.update(user);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = GET_USER_ENDPOINT, method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        LOG.info("deleting user with id: {}", id);
        ResponseEntity<Void> respoonse = null;
        try {
            userService.delete(id);
            respoonse = new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Unable to delete. User with id {} not found", id);
            respoonse = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return respoonse;
    }
}
