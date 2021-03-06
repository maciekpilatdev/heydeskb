package pl.org.conceptweb.heydeskb.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.security.Principal;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.converter.UserConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.UserService;

@Log
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserConverter userConverter;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public HttpResponseWrapper addUser(@RequestBody String userData, Principal principal) {
        JsonObject jsonObject = new JsonParser().parse(userData).getAsJsonObject();
        return userService.add(
                jsonObject.get("userName").getAsString(),
                jsonObject.get("password").getAsString(),
                jsonObject.get("repeatedPassword").getAsString(),
                principal.getName());
    }

    @GetMapping("/getbycompany")
    public HttpResponseWrapper getAllUsersByCompany(@RequestParam Long companyId) {
        return userService.getAllByCompany(companyId);
    }

    @DeleteMapping("/delete")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, allowedHeaders = "*", methods = {RequestMethod.DELETE}, maxAge = 3600)
    public HttpResponseWrapper deleteUserById(@RequestParam Long userId) {
        return userService.deleteById(userId);
    }
}
