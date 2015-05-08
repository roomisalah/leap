package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Course;
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

@RequestMapping("/coursesectionses")
@Controller
@RooWebScaffold(path = "coursesectionses", formBackingObject = CourseSections.class)
public class CourseSectionsController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CourseSections courseSections, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseSections);
            return "coursesectionses/create";
        }
        uiModel.asMap().clear();
        courseSections.persist();
        return "redirect:/coursesectionses/" + encodeUrlPathSegment(courseSections.getCoursesectionid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new CourseSections());
        return "coursesectionses/create";
    }

	@RequestMapping(value = "/{coursesectionid}", produces = "text/html")
    public String show(@PathVariable("coursesectionid") Long coursesectionid, Model uiModel) {
        uiModel.addAttribute("coursesections", CourseSections.findCourseSections(coursesectionid));
        uiModel.addAttribute("itemId", coursesectionid);
        return "coursesectionses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("coursesectionses", CourseSections.findCourseSectionsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) CourseSections.countCourseSectionses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("coursesectionses", CourseSections.findAllCourseSectionses(sortFieldName, sortOrder));
        }
        return "coursesectionses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid CourseSections courseSections, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, courseSections);
            return "coursesectionses/update";
        }
        uiModel.asMap().clear();
        courseSections.merge();
        return "redirect:/coursesectionses/" + encodeUrlPathSegment(courseSections.getCoursesectionid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{coursesectionid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("coursesectionid") Long coursesectionid, Model uiModel) {
        populateEditForm(uiModel, CourseSections.findCourseSections(coursesectionid));
        return "coursesectionses/update";
    }

	@RequestMapping(value = "/{coursesectionid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("coursesectionid") Long coursesectionid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        CourseSections courseSections = CourseSections.findCourseSections(coursesectionid);
        courseSections.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/coursesectionses";
    }

	void populateEditForm(Model uiModel, CourseSections courseSections) {
        uiModel.addAttribute("courseSections", courseSections);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("courses", Course.findAllCourses());
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
