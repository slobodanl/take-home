package take.home.cook.api.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/menu")
@RestController
@RequestMapping(path = "/menu")
public class MenuResource {

}
