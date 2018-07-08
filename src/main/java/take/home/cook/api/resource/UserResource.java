package take.home.cook.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import take.home.cook.api.repository.user.UserByTypeRepository;
import take.home.cook.api.repository.user.UserRepository;
import take.home.cook.api.resource.utils.VOUtils;
import take.home.cook.api.resource.valueobjects.UserVO;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/user")
@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserByTypeRepository userByTypeRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user detail list", notes = "Get user detail list", responseContainer = "List" ,  response = UserVO.class)
    public List<UserVO> listUsers() {
        return VOUtils.transform(userRepository.findAll() ,UserVO.class , ArrayList::new);
    }

    @GetMapping(path = "/type/{type}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user list by type", notes = "Get user list by type", responseContainer = "List" ,  response = UserVO.class)
    public List<UserVO> listByTypeUsers(@ApiParam(value = "Filter by type") @PathVariable(value = "type")
                                                    String type) {
        return VOUtils.transform(userByTypeRepository.findAllByUserByTypeKeyType(type) ,UserVO.class , ArrayList::new);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register new user", notes = "Register new user",  response = UserVO.class)
    public UserVO addUser(@ApiParam(value = "User type", required = true)  @RequestBody UserVO user) {
        return new UserVO(userRepository.insert(user.getEntity()));
    }

    @PermitAll
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update user details", notes = "Update user details",  response = UserVO.class)
    public UserVO updateUser(@ApiParam(value = "User type", required = true) @RequestBody UserVO user) {
        return new UserVO(userRepository.insert(user.getEntity()));
    }
}
