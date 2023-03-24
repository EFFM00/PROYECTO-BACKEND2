package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KeycloakUserRepository {

    @Autowired
    private Keycloak keycloak;

    @Value("${dh.keycloak.realm}")
    public String realm;


    public User findById(String id) {
        UserRepresentation users = keycloak.realm(realm).users().get(id).toRepresentation();
        return toUser(users);
    }


    public void createUser(User newUser){

        UserRepresentation user = new UserRepresentation();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEnabled(true);
        CredentialRepresentation password = new CredentialRepresentation();
        password.setTemporary(false);
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue("digitalmedia");
        user.setCredentials(Collections.singletonList(password));
        keycloak.realm(realm).users().create(user);

    }


    public List<User> findNotAdminUsers() {

        List<UserRepresentation> users = keycloak.realm(realm).users().list();
        List<UserRepresentation> notAdminUsers = users.stream().filter(userRepresentation -> {
            List<GroupRepresentation> groups = keycloak.realm(realm).users().get(userRepresentation.getId()).groups();
            return groups.stream().noneMatch(s -> Objects.equals(s.getName(), "admin"));
        }).collect(Collectors.toList());
        return notAdminUsers.stream().map(this::toNoAdminUser).collect(Collectors.toList());
    }


    private User toNoAdminUser(UserRepresentation userRepresentation) {

        return new User(userRepresentation.getUsername());

    }

    public List<User> findByUsername(String name) {
        List<UserRepresentation> users = keycloak.realm(realm).users().search(name);

        return users.stream().map(this::toUser).collect(Collectors.toList());
    }


    private User toUser(UserRepresentation userRepresentation) {
        return new User(userRepresentation.getUsername());
    }

}
