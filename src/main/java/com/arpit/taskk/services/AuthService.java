package com.arpit.taskk.services;


import com.arpit.taskk.dto.SignupDTO;
import com.arpit.taskk.dto.UserDTO;
import com.arpit.taskk.entity.User;
import com.arpit.taskk.entity.enums.Role;
import com.arpit.taskk.exceptions.RuntimeConflictException;
import com.arpit.taskk.repository.UserRepository;
import com.arpit.taskk.security.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final DailyScoreService dailyScoreService;
    private final WeeklyScoreService weeklyScoreService;


    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Transactional
    public UserDTO signup(SignupDTO signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail())
                .orElse(null);
        if (user != null)
            throw new RuntimeConflictException("The user already exist with email id: " + signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.User));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        //TODO call any role service to create its role

        dailyScoreService.createDailyScore(savedUser);
        weeklyScoreService.createWeeklyScore(savedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);
        return jwtService.generateAccessToken(user);
    }

    public Void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return null;
    }
}
