package cz.fi.muni.pa165.movierecommender.rest.api;


import cz.fi.muni.pa165.movierecommender.api.dto.account.LoginDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(RoutesHolder.LOGIN_ROUTE)
public interface LoginController {


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    String login(@RequestBody LoginDto loginDto);

}
