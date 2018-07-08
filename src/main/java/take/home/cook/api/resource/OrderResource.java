package take.home.cook.api.resource;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/order")
@RestController
@RequestMapping(path = "/order")
public class OrderResource {

}
