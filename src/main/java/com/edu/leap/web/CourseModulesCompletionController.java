package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.CourseModules;
import com.edu.leap.domain.CourseModulesCompletion;
import com.edu.leap.domain.User;
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

@RequestMapping("/coursemodulescompletions")
@Controller
@RooWebScaffold(path = "coursemodulescompletions", formBackingObject = CourseModulesCompletion.class)
public class CourseModulesCompletionController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CourseModulesCompletion courseModulesCompletion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseModulesCompletion);
            return "coursemodulescompletions/create";
        }
        uiModel.asMap().clear();
        courseModulesCompletion.persist();
        return "redirect:/coursemodulescompletions/" + encodeUrlPathSegment(courseModulesCompletion.getCourmodcompid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new CourseModulesCompletion());
        return "coursemodulescompletions/create";
    }

	@RequestMapping(value = "/{courmodcompid}", produces = "text/html")
    public String show(@PathVariable("courmodcompid") Long courmodcompid, Model uiModel) {
        uiModel.addAttribute("coursemodulescompletion", CourseModulesCompletion.findCourseModulesCompletion(courmodcompid));
        uiModel.addAttribute("itemId", courmodcompid);
        return "coursemodulescompletions/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("coursemodulescompletions", CourseModulesCompletion.findCourseModulesCompletionEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) CourseModulesCompletion.countCourseModulesCompletions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("coursemodulescompletions", CourseModulesCompletion.findAllCourseModulesCompletions(sortFieldName, sortOrder));
        }
        return "coursemodulescompletions/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid CourseModulesCompletion courseModulesCompletion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseModulesCompletion);
            return "coursemodulescompletions/update";
        }
        uiModel.asMap().clear();
        courseModulesCompletion.merge();
        return "redirect:/coursemodulescompletions/" + encodeUrlPathSegment(courseModulesCompletion.getCourmodcompid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{courmodcompid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("courmodcompid") Long courmodcompid, Model uiModel) {
        populateEditForm(uiModel, CourseModulesCompletion.findCourseModulesCompletion(courmodcompid));
        return "coursemodulescompletions/update";
    }

	@RequestMapping(value = "/{courmodcompid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("courmodcompid") Long courmodcompid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        CourseModulesCompletion courseModulesCompletion = CourseModulesCompletion.findCourseModulesCompletion(courmodcompid);
        courseModulesCompletion.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/coursemodulescompletions";
    }

	void populateEditForm(Model uiModel, CourseModulesCompletion courseModulesCompletion) {
        uiModel.addAttribute("courseModulesCompletion", courseModulesCompletion);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("coursemoduleses", CourseModules.findAllCourseModuleses());
        uiModel.addAttribute("users", User.findAllUsers());
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
