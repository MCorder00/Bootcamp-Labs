package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {

    private ProfileDao profileDao;

    @Autowired
    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @GetMapping("{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Profile> getById(@PathVariable int userId) {
        try {
            if (profileDao.getById(userId) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(profileDao.getById(userId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Void> update(@PathVariable int userId, @RequestBody Profile profile) {
        try {
            profileDao.update(userId, profile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping()
    @PreAuthorize("permitAll()")
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        try {
            profileDao.create(profile);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
