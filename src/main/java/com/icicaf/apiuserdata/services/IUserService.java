package com.icicaf.apiuserdata.services;

import com.icicaf.apiuserdata.domain.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    public List<User> getUserList();

    public boolean deleteUser(UUID uuid);
}
