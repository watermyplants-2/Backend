package com.buildweekjava.watermyplant2.controllers;

import com.buildweekjava.watermyplant2.models.User;
import com.buildweekjava.watermyplant2.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

//    Returns list of all users

    @GetMapping(value = "/users",
            produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                                    HttpStatus.OK);
    }

// Returns a user that corresponds to that ID
    @GetMapping(value = "/user/{userId}",
            produces = "application/json")
    public ResponseEntity<?> getUserById(
            @PathVariable
                    Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

// Returns user based on username
    @GetMapping(value = "/user/name/{userName}",
            produces = "application/json")
    public ResponseEntity<?> getUserByName(
            @PathVariable
                    String userName)
    {
        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

// Returns users based on the username substring
    @GetMapping(value = "/user/name/like/{userName}",
            produces = "application/json")
    public ResponseEntity<?> getUserLikeName(
            @PathVariable
                    String userName)
    {
        List<User> u = userService.findByNameContaining(userName);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

//    create a new user with accompanying useremail and record

    @PostMapping(value = "/user",
            consumes = "application/json")
    public ResponseEntity<?> addNewUser(
            @Valid
            @RequestBody
                    User newuser) throws
            URISyntaxException
    {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                                    responseHeaders,
                                    HttpStatus.CREATED);
    }

//    Replace the user and user email given the user id and primary key is in the user table

    @PutMapping(value = "/user/{userid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullUser(
            @Valid
            @RequestBody
                    User updateUser,
            @PathVariable
                    long userid)
    {
        updateUser.setUserid(userid);
        userService.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    updates user with associated id with the given data, only provided fields are affected

    @PatchMapping(value = "/user/{id}",
            consumes = "application/json")
    public ResponseEntity<?> updateUser(
            @RequestBody
                    User updateUser,
            @PathVariable
                    long id)
    {
        userService.update(updateUser,
                           id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
// deletes a user with corresponding id deletes everything in it

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Returns user record for currently authenticated used

    @ApiOperation(value = "returns the currently authenticated user",
        response = User.class)
    @GetMapping(value = "/getuserinfo",
        produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }
}