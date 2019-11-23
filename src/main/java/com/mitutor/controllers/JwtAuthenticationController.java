package com.mitutor.controllers;

import com.mitutor.dtos.Responses.UserRegisterResponse;
import com.mitutor.dtos.input.CreateUserInput;
import com.mitutor.dtos.input.LoginUserInput;
import com.mitutor.dtos.output.AuthenticatedUserOutput;
import com.mitutor.entities.Person;
import com.mitutor.entities.Student;
import com.mitutor.entities.University;
import com.mitutor.entities.User;
import com.mitutor.enums.RoleType;
import com.mitutor.services.IUniversityService;
import com.mitutor.services.IUserRegisterService;
import com.mitutor.services.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/authentication")
@Api(tags = "Authentication", value = "Web Service RESTfull for authentication")
public class JwtAuthenticationController {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUniversityService universityService;
    @Autowired
    private IUserRegisterService userRegisterService;
    
    /*
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    //region Authenticate
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            System.out.println("disabled");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("invalid");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    //endregion
    */


    //region CreateAuthenticationToken
    @ApiOperation(value = "Authenticates an user", notes = "Authenticates an user by its username and password")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Wrong username or password"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthenticatedUserOutput> createAuthenticationToken(
            @RequestBody LoginUserInput loginUserInput
    ) {
        try {
            Optional<User> foundUser = userService.findByUsername(loginUserInput.getUsername());

            if (!foundUser.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            User user = foundUser.get();

            if (!passwordEncoder.matches(loginUserInput.getPassword(), user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            String token = generateJWTToken(user.getUsername(), user.getRole());

            AuthenticatedUserOutput authenticatedUserOutput = new AuthenticatedUserOutput()
                    .withId(user.getId())
                    .withEmail(user.getEmail())
                    .withUsername(user.getUsername())
                    .withRole(user.getRole())
                    .withToken(token)
                    .withName(user.getPerson().getName());

            return ResponseEntity.ok(authenticatedUserOutput);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //endregion


    private String generateJWTToken(String username, String role) {
        String secretKey = "mitutoraos";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim(
                        "authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
    
    
    
    @ApiOperation(value = "Register user", notes = "Method create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created successfully"),
            @ApiResponse(code = 404, message = "University not found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(
    		value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserRegisterResponse> register(
            @RequestBody() CreateUserInput createUser
    ) {
        try {
            Optional<University> foundUniversity = universityService.findById(createUser.getUniversityId());

            if (!foundUniversity.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Person newPerson = new Person()
                    .withName(createUser.getName())
                    .withLastname(createUser.getLastName())
                    .withSemester(createUser.getSemester())
                    .withCareer(createUser.getCareer())
                    .withUniversity(foundUniversity.get());

            Student newStudent = new Student()
                    .withPoints(0.00f)
                    .withQualificationCount(0);

            // Encoding password
            String encodedPassword = passwordEncoder.encode(createUser.getPassword());

            User newUser = new User()
                    .withUsername(createUser.getUsername())
                    .withPassword(encodedPassword)
                    .withEmail(createUser.getEmail())
                    .withRole(RoleType.STUDENT);

            newPerson.setUser(newUser);
            newUser.setPerson(newPerson);

            newPerson.setStudent(newStudent);
            newStudent.setPerson(newPerson);

            User userResult = userRegisterService.register(newPerson, newStudent, newUser);

            UserRegisterResponse userResponse = new UserRegisterResponse()
                    .withId(userResult.getId())
                    .withEmail(userResult.getEmail())
                    .withUsername(userResult.getUsername())
                    .withRole(userResult.getRole());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        } catch (Exception e) {

//            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
