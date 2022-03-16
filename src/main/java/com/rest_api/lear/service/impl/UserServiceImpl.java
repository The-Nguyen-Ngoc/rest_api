package com.rest_api.lear.service.impl;

import com.rest_api.lear.dto.UserDto;
import com.rest_api.lear.entity.User;
import com.rest_api.lear.repository.UserRepo;
import com.rest_api.lear.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        User userCheckUnique = userRepo.findByEmail(userDto.getEmail());

        if(userCheckUnique != null){ throw new RuntimeException("User already exist"); }
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        String randomString = RandomString.make(30);
        user.setUserId(randomString);

        User saveUser = userRepo.save(user);
        UserDto returnUserDto = new UserDto();
        BeanUtils.copyProperties(saveUser,returnUserDto);
        return returnUserDto;
    }

    @Override
    public UserDto getUser(String email) {
        User userRepoByEmail = userRepo.findByEmail(email);
        if(userRepoByEmail==null) throw new RuntimeException("User not found with email: "+email);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRepoByEmail,userDto);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userRepoByEmail = userRepo.findByEmail(email);

        if(userRepoByEmail==null) throw new UsernameNotFoundException("User not found with email: "+email);

        return new org.springframework.security.core.userdetails.User(userRepoByEmail.getEmail(),userRepoByEmail.getEncryptedPassword(),new ArrayList<>());
    }
}
