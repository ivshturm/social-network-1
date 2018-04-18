package ru.sberbank.project.controller.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.util.UserUtil;

@Controller
public class UserController extends AbstractUserController {

    @GetMapping("/search")
    public String findAllByParam(ModelMap model, @RequestParam("name") String parameter) {
        model.addAttribute("users", super.findAllByNameOrLastName(parameter));
        model.addAttribute("name", String.format( "Поиск по параметру \"%s\"", parameter));
        if (parameter.isEmpty()) {
            model.addAttribute("emptyList", "Вы задали пустой запрос, поэтому ничего не нашлось");
        } else {
            model.addAttribute("emptyList", String.format("По вашему запросу \"%s\" ничего не найдено", parameter));
        }
        return "listusers";
    }

    @GetMapping("/followers/{id}")
    public String getAllFollowers(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int id) {
        if (authorizedUser.getId() == id) {
            model.addAttribute("users", super.getAllFollowersByUserId(authorizedUser.getId()));
            model.addAttribute("name", "Пользователи на которых вы подписаны");
            model.addAttribute("emptyList", "У вас нет подписчиков");
        } else {
            model.addAttribute("users", super.getAllFollowersByUserId(id));
            model.addAttribute("name", "Подписчики пользователя");
            model.addAttribute("emptyList", "У пользователя нет подписчиков");
        }
        return "listusers";
    }

    @GetMapping("/following/{id}")
    public String getAllFollowing(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int id) {
        if (authorizedUser.getId() == id) {
            model.addAttribute("users", super.getAllFollowingByUserId(authorizedUser.getId()));
            model.addAttribute("name", "Ваши подписчики");
            model.addAttribute("emptyList", "Вы ни на кого не подписаны");
        } else {
            model.addAttribute("users", super.getAllFollowingByUserId(id));
            model.addAttribute("name", "Пользователи на которых подписан пользователь");
            model.addAttribute("emptyList", "Пользователь ни на кого не подписан");
        }
        return "listusers";
    }

    @GetMapping("/user/{userIdPage}")
    public String getUser(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int userIdPage) {
        model.addAttribute("user", UserUtil.asTo(super.get(userIdPage)));
        String checkFollow = super.checkSubscribe(authorizedUser.getId(), userIdPage) == null ? "no" : "yes";
        model.addAttribute("checkFollow", checkFollow);
        model.addAttribute("authUserId", authorizedUser.getId());
        return "user";
    }

    @GetMapping("/subscribe/{userIdPage}")
    public String subscribe(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int userIdPage) {
        super.subscribeToUser(authorizedUser.getId(), userIdPage);
        model.addAttribute("user", UserUtil.asTo(super.get(userIdPage)));
        String checkFollow = super.checkSubscribe(authorizedUser.getId(), userIdPage) == null ? "no" : "yes";
        model.addAttribute("checkFollow", checkFollow);
        model.addAttribute("authUserId", authorizedUser.getId());
        return "redirect:/user/{userIdPage}";
    }

    @GetMapping("/unsubscribe/{userIdPage}")
    public String unsubscribe(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int userIdPage) {
        super.unsubscribeToUser(authorizedUser.getId(), userIdPage);
        model.addAttribute("user", UserUtil.asTo(super.get(userIdPage)));
        String checkFollow = super.checkSubscribe(authorizedUser.getId(), userIdPage) == null ? "no" : "yes";
        model.addAttribute("checkFollow", checkFollow);
        model.addAttribute("authUserId", authorizedUser.getId());
        return "redirect:/user/{userIdPage}";
    }

    @GetMapping("/news")
    public String getNews() {
        return "news";
    }
}
