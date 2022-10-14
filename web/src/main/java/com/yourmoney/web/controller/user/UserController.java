package com.yourmoney.web.controller.user;

import com.yourmoney.domain.model.User;
import com.yourmoney.usecases.user.UserCreator;
import com.yourmoney.web.controller.user.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@Validated
public class UserController {
    private UserCreator userCreator;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(UserCreator userCreator, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userCreator = userCreator;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("users")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) {

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return new ResponseEntity<>(toDto(userCreator.create(toDomain(userDto))), HttpStatus.CREATED);
    }

    private User toDomain(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
