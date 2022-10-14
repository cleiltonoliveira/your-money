package com.yourmoney.persistence.user;

import com.yourmoney.domain.model.User;
import com.yourmoney.usecases.user.adapter.UserAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGateway implements UserAdapter {

    private final ModelMapper modelMapper;
    private final UserRepository repository;

    public UserGateway(ModelMapper modelMapper, UserRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return toDomain(repository.save(toEntity(user)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(this::toDomain);
    }

    private User toDomain(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }

    private UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }
}
