package com.boubyan.boubyantask.mapper;

import com.boubyan.boubyantask.entity.User;
import com.boubyan.boubyantask.model.dto.UserDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-01T14:01:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO mapUserToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        if ( user.getEmail() != null ) {
            userDTO.email( user.getEmail() );
        }
        if ( user.getPassword() != null ) {
            userDTO.password( user.getPassword() );
        }

        return userDTO.build();
    }

    @Override
    public User mapUserDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO.getEmail() != null ) {
            user.setEmail( userDTO.getEmail() );
        }
        if ( userDTO.getPassword() != null ) {
            user.setPassword( userDTO.getPassword() );
        }

        return user;
    }
}
