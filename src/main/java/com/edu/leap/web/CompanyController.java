package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Course;
import com.edu.leap.domain.CourseCategories;
import com.edu.leap.domain.CourseCompletions;
import com.edu.leap.domain.CourseFormatOptions;
import com.edu.leap.domain.CourseModules;
import com.edu.leap.domain.CourseModulesCompletion;
import com.edu.leap.domain.CourseSections;
import com.edu.leap.domain.Modules;
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

@RequestMapping("/companys")
@Controller
@RooWebScaffold(path = "companys", formBackingObject = Company.class)
public class CompanyController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Company company, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, company);
            return "companys/create";
        }
        uiModel.asMap().clear();
        company.persist();
        return "redirect:/companys/" + encodeUrlPathSegment(company.getCompanyid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Company());
        return "companys/create";
    }

	@RequestMapping(value = "/{companyid}", produces = "text/html")
    public String show(@PathVariable("companyid") Long companyid, Model uiModel) {
        uiModel.addAttribute("company", Company.findCompany(companyid));
        uiModel.addAttribute("itemId", companyid);
        return "companys/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("companys", Company.findCompanyEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Company.countCompanys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("companys", Company.findAllCompanys(sortFieldName, sortOrder));
        }
        return "companys/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Company company, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, company);
            return "companys/update";
        }
        uiModel.asMap().clear();
        company.merge();
        return "redirect:/companys/" + encodeUrlPathSegment(company.getCompanyid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{companyid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("companyid") Long companyid, Model uiModel) {
        populateEditForm(uiModel, Company.findCompany(companyid));
        return "companys/update";
    }

	@RequestMapping(value = "/{companyid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("companyid") Long companyid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Company company = Company.findCompany(companyid);
        company.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/companys";
    }

	void populateEditForm(Model uiModel, Company company) {
        uiModel.addAttribute("company", company);
        uiModel.addAttribute("courses", Course.findAllCourses());
        uiModel.addAttribute("coursecategorieses", CourseCategories.findAllCourseCategorieses());
        uiModel.addAttribute("coursecompletionses", CourseCompletions.findAllCourseCompletionses());
        uiModel.addAttribute("courseformatoptionses", CourseFormatOptions.findAllCourseFormatOptionses());
        uiModel.addAttribute("coursemoduleses", CourseModules.findAllCourseModuleses());
        uiModel.addAttribute("coursemodulescompletions", CourseModulesCompletion.findAllCourseModulesCompletions());
        uiModel.addAttribute("coursesectionses", CourseSections.findAllCourseSectionses());
        uiModel.addAttribute("moduleses", Modules.findAllModuleses());
        uiModel.addAttribute("users", User.findAllUsers());
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
