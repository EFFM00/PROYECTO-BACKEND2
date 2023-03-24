package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.repository.UserRepository;
import com.digitalmedia.users.repository.KeycloakUserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {


  @Value("${dh.keycloak.realm}")
  private String realm;

  private final UserRepository userRepository;

  private final KeycloakUserRepository keycloakUserRepository;


  @Override
  public void createUser(User user){

    keycloakUserRepository.createUser(user);
  }

  @Override
  public User validateAndGetUserExtra(String username) {
    return userRepository.validateAndGetUser(username);
  }

  @Override
  public Optional<User> getUserExtra(String username) {
    return userRepository.getUserExtra(username);
  }

  @Override
  public User saveUserExtra(User user) {
    return userRepository.saveUserExtra(user);
  }

  @Override
  public List<User> findUsersNoAdmin() {
    return keycloakUserRepository.findNotAdminUsers();
  }

  @Override
  public List<User> findByUsernamePublic(String username) {
    return null;
  }
}
