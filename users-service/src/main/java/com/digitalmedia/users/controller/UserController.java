package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final IUserService userService;

  @GetMapping("/me")
  public User getUserExtra(@RequestParam String principal) {
    return userService.validateAndGetUserExtra(principal);
  }


  @PreAuthorize("hasAuthority('GROUP_admin')")
  @GetMapping("/admin")
  public List<User> findUsersNoAdmin() {

    return userService.findUsersNoAdmin();
  }


  @PreAuthorize("hasAuthority('GROUP_admin')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/create")
  public void createKeycloakUser(@Valid @RequestBody User user) {

    userService.createUser(user);
  }


  @PostMapping("/me")
  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, @RequestParam(value = "principal") String principal) {
    Optional<User> userOptional = userService.getUserExtra(principal);
    User userExtra = userOptional.orElseGet(() -> new User(principal));
    userExtra.setAvatar(updateUserRequest.getAvatar());
    return userService.saveUserExtra(userExtra);
  }
}
