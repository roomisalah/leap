package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.CourseCompletions;
import com.edu.leap.domain.CourseModulesCompletion;
import com.edu.leap.domain.Role;
import com.edu.leap.domain.User;
import com.edu.leap.domain.Userpermission;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/users")
@Controller
@RooWebScaffold(path = "users", formBackingObject = User.class)
public class UserController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "users/create";
        }
        uiModel.asMap().clear();
        user.persist();
        return "redirect:/users/" + encodeUrlPathSegment(user.getUserid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new User());
        return "users/create";
    }

	@RequestMapping(value = "/{userid}", produces = "text/html")
    public String show(@PathVariable("userid") Long userid, Model uiModel) {
        uiModel.addAttribute("user", User.findUser(userid));
        uiModel.addAttribute("itemId", userid);
        return "users/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("users", User.findUserEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) User.countUsers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("users", User.findAllUsers(sortFieldName, sortOrder));
        }
        return "users/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "users/update";
        }
        uiModel.asMap().clear();
        user.merge();
        return "redirect:/users/" + encodeUrlPathSegment(user.getUserid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{userid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("userid") Long userid, Model uiModel) {
        populateEditForm(uiModel, User.findUser(userid));
        return "users/update";
    }

	@RequestMapping(value = "/{userid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("userid") Long userid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        User user = User.findUser(userid);
        user.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/users";
    }

	void populateEditForm(Model uiModel, User user) {
        uiModel.addAttribute("user", user);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("coursecompletionses", CourseCompletions.findAllCourseCompletionses());
        uiModel.addAttribute("coursemodulescompletions", CourseModulesCompletion.findAllCourseModulesCompletions());
        uiModel.addAttribute("roles", Role.findAllRoles());
        uiModel.addAttribute("userpermissions", Userpermission.findAllUserpermissions());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
