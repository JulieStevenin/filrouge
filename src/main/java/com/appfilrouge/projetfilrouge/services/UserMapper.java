package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.dto.UserDTO;
import com.appfilrouge.projetfilrouge.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFname(),
                user.getLname(),
                user.getMail(),
                user.getPhoto(),
                user.getPassword(),
                user.getBuyer(),
                user.getSeller()

        );
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFname(userDTO.getFname());
        user.setLname(userDTO.getLname());
        user.setMail(userDTO.getMail());
        user.setPhoto(userDTO.getPhoto());
        user.setPassword(userDTO.getPassword());
        user.setBuyer(userDTO.getBuyer());
        user.setSeller(userDTO.getSeller());
        return user;
    }
}
