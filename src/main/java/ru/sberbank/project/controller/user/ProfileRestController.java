package ru.sberbank.project.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.project.model.AbstractBaseEntity;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.model.User;
import ru.sberbank.project.model.UserTo;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.get(authorizedUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.update(userTo, authorizedUser.getId());
    }

    @RequestMapping(value = "/allusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<Integer ,String> getAllUsers() {
        return super.getAll().stream()
                .collect(Collectors.toMap (AbstractBaseEntity::getId, User::getFullName));
    }


}