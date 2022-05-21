package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.account.LoginDto;
import cz.fi.muni.pa165.movierecommender.api.facade.UserFacade;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Jakub Petráš | xpetras@mail.muni.cz
 */
@Slf4j
@RestController
@RequestMapping(RoutesHolder.LOGIN_ROUTE)
public class LoginControllerProvider implements LoginController{

    private final UserFacade accountFacade;

    @Autowired
    public LoginControllerProvider(UserFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public String login(
            @RequestBody LoginDto dto) {
        log.debug("Log in of user: " + dto.getId());
        return accountFacade
                .login(dto.getName(), dto.getPassword());
    }

}
