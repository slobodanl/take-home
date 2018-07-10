package take.home.cook.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import take.home.cook.api.repository.menu.MenuItemByCategoryRepository;
import take.home.cook.api.repository.menu.MenuItemRepository;
import take.home.cook.api.resource.utils.VOUtils;
import take.home.cook.api.resource.valueobjects.MenuItemVO;

import java.util.ArrayList;
import java.util.List;

@Api(value = "/menu")
@RestController
@RequestMapping(path = "/menu")
public class MenuResource {
    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    MenuItemByCategoryRepository menuItemByCategoryRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get cook menu items", notes = "Get cook menu items", responseContainer = "List" ,  response = MenuItemVO.class)
    public List<MenuItemVO> listAllMenuItems() {
        return VOUtils.transform(menuItemRepository.findAll() ,MenuItemVO.class , ArrayList::new);
    }

    @GetMapping(path = "/{category}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get cook menu items by category", notes = "Get cook menu items by category", responseContainer = "List" ,  response = MenuItemVO.class)
    public List<MenuItemVO> listAllMenuItemsByCategory(@ApiParam(value = "Filter by category") @PathVariable(value = "category")
                                                         String category) {
        return VOUtils.transform(menuItemByCategoryRepository.findAllByKeyId(category) ,MenuItemVO.class , ArrayList::new);
    }
}
