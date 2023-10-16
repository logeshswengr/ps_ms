package com.project.userservice.service;

import com.project.userservice.dto.UserRequest;
import com.project.userservice.dto.UserResponse;
import com.project.userservice.entity.UserInfo;
import com.project.userservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponse saveUser(UserRequest request) throws Exception {
        UserInfo userDbObj = userRepository.save(new UserInfo(null, request.getName(), request.getEmail(),
                 request.getPassword()));
        return new UserResponse(userDbObj.getId(), userDbObj.getName(), userDbObj.getEmail(),
                 userDbObj.getPassword());
    }

    public UserResponse getUser(Integer id) throws Exception {
        Optional<UserInfo> User = userRepository.findById(id);
        if (User.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        UserInfo userDbObj = User.get();
        return new UserResponse(userDbObj.getId(), userDbObj.getName(), userDbObj.getEmail(),
                 userDbObj.getPassword());
    }

    public UserResponse updateUser(Integer id, UserRequest request) throws Exception {
        Optional<UserInfo> User = userRepository.findById(id);
        if (User.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        UserInfo UserDb = User.get();
        UserDb.setName(request.getName());
        UserDb.setEmail(request.getEmail());
        UserDb.setPassword(request.getPassword());
        UserInfo UserSaved = userRepository.save(UserDb);
        return new UserResponse(UserSaved.getId(), UserSaved.getName(), UserSaved.getEmail(),
               UserSaved.getPassword());
    }

    public void deleteUser(Integer id) throws Exception {
        userRepository.deleteById(id);
    }
}
