package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Course;
import com.edu.leap.domain.CourseCategories;
import com.edu.leap.domain.CourseCompletions;
import com.edu.leap.domain.CourseFormatOptions;
import com.edu.leap.domain.CourseModules;
import com.edu.leap.domain.CourseSections;
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

@RequestMapping("/courses")
@Controller
@RooWebScaffold(path = "courses", formBackingObject = Course.class)
public class CourseController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Course course, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, course);
            return "courses/create";
        }
        uiModel.asMap().clear();
        course.persist();
        return "redirect:/courses/" + encodeUrlPathSegment(course.getCourseid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Course());
        return "courses/create";
    }

	@RequestMapping(value = "/{courseid}", produces = "text/html")
    public String show(@PathVariable("courseid") Long courseid, Model uiModel) {
        uiModel.addAttribute("course", Course.findCourse(courseid));
        uiModel.addAttribute("itemId", courseid);
        return "courses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("courses", Course.findCourseEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Course.countCourses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("courses", Course.findAllCourses(sortFieldName, sortOrder));
        }
        return "courses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Course course, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, course);
            return "courses/update";
        }
        uiModel.asMap().clear();
        course.merge();
        return "redirect:/courses/" + encodeUrlPathSegment(course.getCourseid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{courseid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("courseid") Long courseid, Model uiModel) {
        populateEditForm(uiModel, Course.findCourse(courseid));
        return "courses/update";
    }

	@RequestMapping(value = "/{courseid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("courseid") Long courseid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Course course = Course.findCourse(courseid);
        course.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/courses";
    }

	void populateEditForm(Model uiModel, Course course) {
        uiModel.addAttribute("course", course);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("coursecategorieses", CourseCategories.findAllCourseCategorieses());
        uiModel.addAttribute("coursecompletionses", CourseCompletions.findAllCourseCompletionses());
        uiModel.addAttribute("courseformatoptionses", CourseFormatOptions.findAllCourseFormatOptionses());
        uiModel.addAttribute("coursemoduleses", CourseModules.findAllCourseModuleses());
        uiModel.addAttribute("coursesectionses", CourseSections.findAllCourseSectionses());
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
