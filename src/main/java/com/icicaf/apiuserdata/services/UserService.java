package com.icicaf.apiuserdata.services;

import com.icicaf.apiuserdata.domain.User;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class UserService implements IUserService {

    List<User> userList = null;

    private UserService() {
         userList = new ArrayList<>(
                List.of(User.builder()
                                .id(UUID.randomUUID())
                                .name("Cristian")
                                .lastName("Aguayo")
                                .build(),
                        User.builder()
                                .id(UUID.randomUUID())
                                .name("Alejandro")
                                .lastName("Forteza")
                                .build()));
    }

    public boolean deleteUser(UUID uuid) {
        if(userList.removeIf(user -> user.getId().equals(uuid))) {
            return true;
        } else {
            return false;
        }
    }
}
