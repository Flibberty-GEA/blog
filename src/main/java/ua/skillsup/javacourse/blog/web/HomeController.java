package ua.skillsup.javacourse.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.skillsup.javacourse.blog.service.BlogService;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class HomeController {
    public static final int DEFAULT_MESSAGES_PER_PAGE = 25;

    private BlogService blogService;

    @Inject // Внедрить BlogService
    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping({"/","/home"}) // Обрабатывать запросы на получение главной страницы
    public String showHomePage(Map<String, Object> model) {
        model.put("messages", blogService.getRecentMessages(
                DEFAULT_MESSAGES_PER_PAGE)); //Добавить сообщения в модель
        return "home"; // Вернуть имя представления
    }
}
