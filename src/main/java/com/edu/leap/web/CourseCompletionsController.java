package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Course;
import com.edu.leap.domain.CourseCompletions;
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

@RequestMapping("/coursecompletionses")
@Controller
@RooWebScaffold(path = "coursecompletionses", formBackingObject = CourseCompletions.class)
public class CourseCompletionsController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CourseCompletions courseCompletions, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseCompletions);
            return "coursecompletionses/create";
        }
        uiModel.asMap().clear();
        courseCompletions.persist();
        return "redirect:/coursecompletionses/" + encodeUrlPathSegment(courseCompletions.getCoursecompid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new CourseCompletions());
        return "coursecompletionses/create";
    }

	@RequestMapping(value = "/{coursecompid}", produces = "text/html")
    public String show(@PathVariable("coursecompid") Long coursecompid, Model uiModel) {
        uiModel.addAttribute("coursecompletions", CourseCompletions.findCourseCompletions(coursecompid));
        uiModel.addAttribute("itemId", coursecompid);
        return "coursecompletionses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("coursecompletionses", CourseCompletions.findCourseCompletionsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) CourseCompletions.countCourseCompletionses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("coursecompletionses", CourseCompletions.findAllCourseCompletionses(sortFieldName, sortOrder));
        }
        return "coursecompletionses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid CourseCompletions courseCompletions, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseCompletions);
            return "coursecompletionses/update";
        }
        uiModel.asMap().clear();
        courseCompletions.merge();
        return "redirect:/coursecompletionses/" + encodeUrlPathSegment(courseCompletions.getCoursecompid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{coursecompid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("coursecompid") Long coursecompid, Model uiModel) {
        populateEditForm(uiModel, CourseCompletions.findCourseCompletions(coursecompid));
        return "coursecompletionses/update";
    }

	@RequestMapping(value = "/{coursecompid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("coursecompid") Long coursecompid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        CourseCompletions courseCompletions = CourseCompletions.findCourseCompletions(coursecompid);
        courseCompletions.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/coursecompletionses";
    }

	void populateEditForm(Model uiModel, CourseCompletions courseCompletions) {
        uiModel.addAttribute("courseCompletions", courseCompletions);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("courses", Course.findAllCourses());
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
