package ru.mcx73.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import ru.mcx73.entity.Docum;
import ru.mcx73.entity.User;
import ru.mcx73.repos.DocRepo;

@Controller
public class MainController {
    @Autowired
    private DocRepo docRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Docum> doc = docRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            doc = docRepo.findByAuthor(filter);
        } else {
            doc = docRepo.findAll();
        }

        model.addAttribute("messages", doc);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Docum docum,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        docum.setAuthor(user);

        Iterable<Docum> doc = docRepo.findAll();

        model.addAttribute("messages", doc);

        return "main";
    }
}
