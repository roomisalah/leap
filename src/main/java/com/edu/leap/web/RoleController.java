package com.edu.leap.web;
import com.edu.leap.domain.Role;
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

@RequestMapping("/roles")
@Controller
@RooWebScaffold(path = "roles", formBackingObject = Role.class)
public class RoleController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, role);
            return "roles/create";
        }
        uiModel.asMap().clear();
        role.persist();
        return "redirect:/roles/" + encodeUrlPathSegment(role.getRoleid().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Role());
        return "roles/create";
    }

	@RequestMapping(value = "/{roleid}", produces = "text/html")
    public String show(@PathVariable("roleid") Long roleid, Model uiModel) {
        uiModel.addAttribute("role", Role.findRole(roleid));
        uiModel.addAttribute("itemId", roleid);
        return "roles/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("roles", Role.findRoleEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Role.countRoles() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("roles", Role.findAllRoles(sortFieldName, sortOrder));
        }
        return "roles/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, role);
            return "roles/update";
        }
        uiModel.asMap().clear();
        role.merge();
        return "redirect:/roles/" + encodeUrlPathSegment(role.getRoleid().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{roleid}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("roleid") Long roleid, Model uiModel) {
        populateEditForm(uiModel, Role.findRole(roleid));
        return "roles/update";
    }

	@RequestMapping(value = "/{roleid}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("roleid") Long roleid, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Role role = Role.findRole(roleid);
        role.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/roles";
    }

	void populateEditForm(Model uiModel, Role role) {
        uiModel.addAttribute("role", role);
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
