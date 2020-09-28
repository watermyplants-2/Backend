package com.buildweekjava.watermyplant2.controllers;

import com.buildweekjava.watermyplant2.models.Role;
import com.buildweekjava.watermyplant2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

// roles data
@RestController
@RequestMapping("/roles")
public class RolesController
{

    @Autowired
    RoleService roleService;

//  list of all roles
    @GetMapping(value = "/roles",
            produces = "application/json")
    public ResponseEntity<?> listRoles()
    {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles,
                                    HttpStatus.OK);
    }

// roll corresponding to the id

    @GetMapping(value = "/role/{roleId}",
            produces = "application/json")
    public ResponseEntity<?> getRoleById(
            @PathVariable
                    Long roleId)
    {
        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r,
                                    HttpStatus.OK);
    }

// role with the given name

    @GetMapping(value = "/role/name/{roleName}",
            produces = "application/json")
    public ResponseEntity<?> getRoleByName(
            @PathVariable
                    String roleName)
    {
        Role r = roleService.findByName(roleName);
        return new ResponseEntity<>(r,
                                    HttpStatus.OK);
    }

//given a role object create a new role
    @PostMapping(value = "/role",
            consumes = "application/json")
    public ResponseEntity<?> addNewRole(
            @Valid
            @RequestBody
                    Role newRole)
    {
        // ids are not recognized by the Post method
        newRole.setRoleid(0);
        newRole = roleService.save(newRole);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleid}")
                .buildAndExpand(newRole.getRoleid())
                .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null,
                                    responseHeaders,
                                    HttpStatus.CREATED);
    }

// update role only
    @PutMapping(value = "/role/{roleid}",
            consumes = {"application/json"})
    public ResponseEntity<?> putUpdateRole(
            @PathVariable
                    long roleid,
            @Valid
            @RequestBody
                    Role newRole)
    {
        newRole = roleService.update(roleid,
                                     newRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
