package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Course;
import com.edu.leap.domain.CourseModules;
import com.edu.leap.domain.CourseModulesCompletion;
import com.edu.leap.domain.Modules;
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

@RequestMapping("/coursemoduleses")
@Controller
@RooWebScaffold(path = "coursemoduleses", formBackingObject = CourseModules.class)
public class CourseModulesController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CourseModules courseModules, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseModules);
            return "coursemoduleses/create";
        }
        uiModel.asMap().clear();
        courseModules.persist();
        return "redirect:/coursemoduleses/" + encodeUrlPathSegment(courseModules.getCoursemoduleid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new CourseModules());
        return "coursemoduleses/create";
    }

	@RequestMapping(value = "/{coursemoduleid}", produces = "text/html")
    public String show(@PathVariable("coursemoduleid") Long coursemoduleid, Model uiModel) {
        uiModel.addAttribute("coursemodules", CourseModules.findCourseModules(coursemoduleid));
        uiModel.addAttribute("itemId", coursemoduleid);
        return "coursemoduleses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("coursemoduleses", CourseModules.findCourseModulesEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) CourseModules.countCourseModuleses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("coursemoduleses", CourseModules.findAllCourseModuleses(sortFieldName, sortOrder));
        }
        return "coursemoduleses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid CourseModules courseModules, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseModules);
            return "coursemoduleses/update";
        }
        uiModel.asMap().clear();
        courseModules.merge();
        return "redirect:/coursemoduleses/" + encodeUrlPathSegment(courseModules.getCoursemoduleid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{coursemoduleid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("coursemoduleid") Long coursemoduleid, Model uiModel) {
        populateEditForm(uiModel, CourseModules.findCourseModules(coursemoduleid));
        return "coursemoduleses/update";
    }

	@RequestMapping(value = "/{coursemoduleid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("coursemoduleid") Long coursemoduleid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        CourseModules courseModules = CourseModules.findCourseModules(coursemoduleid);
        courseModules.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/coursemoduleses";
    }

	void populateEditForm(Model uiModel, CourseModules courseModules) {
        uiModel.addAttribute("courseModules", courseModules);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("courses", Course.findAllCourses());
        uiModel.addAttribute("coursemodulescompletions", CourseModulesCompletion.findAllCourseModulesCompletions());
        uiModel.addAttribute("moduleses", Modules.findAllModuleses());
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
