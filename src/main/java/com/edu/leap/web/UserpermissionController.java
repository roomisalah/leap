package com.edu.leap.web;
import com.edu.leap.domain.Company;
import com.edu.leap.domain.Permission;
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

@RequestMapping("/userpermissions")
@Controller
@RooWebScaffold(path = "userpermissions", formBackingObject = Userpermission.class)
public class UserpermissionController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Userpermission userpermission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userpermission);
            return "userpermissions/create";
        }
        uiModel.asMap().clear();
        userpermission.persist();
        return "redirect:/userpermissions/" + encodeUrlPathSegment(userpermission.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Userpermission());
        return "userpermissions/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("userpermission", Userpermission.findUserpermission(id));
        uiModel.addAttribute("itemId", id);
        return "userpermissions/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("userpermissions", Userpermission.findUserpermissionEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Userpermission.countUserpermissions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("userpermissions", Userpermission.findAllUserpermissions(sortFieldName, sortOrder));
        }
        return "userpermissions/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Userpermission userpermission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userpermission);
            return "userpermissions/update";
        }
        uiModel.asMap().clear();
        userpermission.merge();
        return "redirect:/userpermissions/" + encodeUrlPathSegment(userpermission.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Userpermission.findUserpermission(id));
        return "userpermissions/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Userpermission userpermission = Userpermission.findUserpermission(id);
        userpermission.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/userpermissions";
    }

	void populateEditForm(Model uiModel, Userpermission userpermission) {
        uiModel.addAttribute("userpermission", userpermission);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("permissions", Permission.findAllPermissions());
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
