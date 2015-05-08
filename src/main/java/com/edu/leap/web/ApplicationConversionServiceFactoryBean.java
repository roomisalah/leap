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
import com.edu.leap.domain.Permission;
import com.edu.leap.domain.Role;
import com.edu.leap.domain.User;
import com.edu.leap.domain.Userpermission;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<Company, String> getCompanyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Company, java.lang.String>() {
            public String convert(Company company) {
                return new StringBuilder().append(company.getCompanyname()).append(' ').append(company.getDescription()).append(' ').append(company.getCreatetime()).append(' ').append(company.getUpdatetime()).toString();
            }
        };
    }

	public Converter<Long, Company> getIdToCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Company>() {
            public com.edu.leap.domain.Company convert(java.lang.Long id) {
                return Company.findCompany(id);
            }
        };
    }

	public Converter<String, Company> getStringToCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Company>() {
            public com.edu.leap.domain.Company convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Company.class);
            }
        };
    }

	public Converter<Course, String> getCourseToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Course, java.lang.String>() {
            public String convert(Course course) {
                return new StringBuilder().append(course.getSortorder()).append(' ').append(course.getFullname()).append(' ').append(course.getShortname()).append(' ').append(course.getCoursenumber()).toString();
            }
        };
    }

	public Converter<Long, Course> getIdToCourseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Course>() {
            public com.edu.leap.domain.Course convert(java.lang.Long id) {
                return Course.findCourse(id);
            }
        };
    }

	public Converter<String, Course> getStringToCourseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Course>() {
            public com.edu.leap.domain.Course convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Course.class);
            }
        };
    }

	public Converter<CourseCategories, String> getCourseCategoriesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseCategories, java.lang.String>() {
            public String convert(CourseCategories courseCategories) {
                return new StringBuilder().append(courseCategories.getTitle()).append(' ').append(courseCategories.getCategorynumber()).append(' ').append(courseCategories.getDescription()).append(' ').append(courseCategories.getSortorder()).toString();
            }
        };
    }

	public Converter<Long, CourseCategories> getIdToCourseCategoriesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseCategories>() {
            public com.edu.leap.domain.CourseCategories convert(java.lang.Long id) {
                return CourseCategories.findCourseCategories(id);
            }
        };
    }

	public Converter<String, CourseCategories> getStringToCourseCategoriesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseCategories>() {
            public com.edu.leap.domain.CourseCategories convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseCategories.class);
            }
        };
    }

	public Converter<CourseCompletions, String> getCourseCompletionsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseCompletions, java.lang.String>() {
            public String convert(CourseCompletions courseCompletions) {
                return new StringBuilder().append(courseCompletions.getTimeenrolled()).append(' ').append(courseCompletions.getTimestarted()).append(' ').append(courseCompletions.getTimecompleted()).toString();
            }
        };
    }

	public Converter<Long, CourseCompletions> getIdToCourseCompletionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseCompletions>() {
            public com.edu.leap.domain.CourseCompletions convert(java.lang.Long id) {
                return CourseCompletions.findCourseCompletions(id);
            }
        };
    }

	public Converter<String, CourseCompletions> getStringToCourseCompletionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseCompletions>() {
            public com.edu.leap.domain.CourseCompletions convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseCompletions.class);
            }
        };
    }

	public Converter<CourseFormatOptions, String> getCourseFormatOptionsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseFormatOptions, java.lang.String>() {
            public String convert(CourseFormatOptions courseFormatOptions) {
                return new StringBuilder().append(courseFormatOptions.getFormat()).append(' ').append(courseFormatOptions.getSectionid()).append(' ').append(courseFormatOptions.getName()).append(' ').append(courseFormatOptions.getValue()).toString();
            }
        };
    }

	public Converter<Long, CourseFormatOptions> getIdToCourseFormatOptionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseFormatOptions>() {
            public com.edu.leap.domain.CourseFormatOptions convert(java.lang.Long id) {
                return CourseFormatOptions.findCourseFormatOptions(id);
            }
        };
    }

	public Converter<String, CourseFormatOptions> getStringToCourseFormatOptionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseFormatOptions>() {
            public com.edu.leap.domain.CourseFormatOptions convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseFormatOptions.class);
            }
        };
    }

	public Converter<CourseModules, String> getCourseModulesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseModules, java.lang.String>() {
            public String convert(CourseModules courseModules) {
                return new StringBuilder().append(courseModules.getInstance()).append(' ').append(courseModules.getSection()).append(' ').append(courseModules.getIdnumber()).append(' ').append(courseModules.getAddedon()).toString();
            }
        };
    }

	public Converter<Long, CourseModules> getIdToCourseModulesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseModules>() {
            public com.edu.leap.domain.CourseModules convert(java.lang.Long id) {
                return CourseModules.findCourseModules(id);
            }
        };
    }

	public Converter<String, CourseModules> getStringToCourseModulesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseModules>() {
            public com.edu.leap.domain.CourseModules convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseModules.class);
            }
        };
    }

	public Converter<CourseModulesCompletion, String> getCourseModulesCompletionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseModulesCompletion, java.lang.String>() {
            public String convert(CourseModulesCompletion courseModulesCompletion) {
                return new StringBuilder().append(courseModulesCompletion.getModifiedtime()).toString();
            }
        };
    }

	public Converter<Long, CourseModulesCompletion> getIdToCourseModulesCompletionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseModulesCompletion>() {
            public com.edu.leap.domain.CourseModulesCompletion convert(java.lang.Long id) {
                return CourseModulesCompletion.findCourseModulesCompletion(id);
            }
        };
    }

	public Converter<String, CourseModulesCompletion> getStringToCourseModulesCompletionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseModulesCompletion>() {
            public com.edu.leap.domain.CourseModulesCompletion convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseModulesCompletion.class);
            }
        };
    }

	public Converter<CourseSections, String> getCourseSectionsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.CourseSections, java.lang.String>() {
            public String convert(CourseSections courseSections) {
                return new StringBuilder().append(courseSections.getSection()).append(' ').append(courseSections.getName()).append(' ').append(courseSections.getSummary()).append(' ').append(courseSections.getSummaryformat()).toString();
            }
        };
    }

	public Converter<Long, CourseSections> getIdToCourseSectionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.CourseSections>() {
            public com.edu.leap.domain.CourseSections convert(java.lang.Long id) {
                return CourseSections.findCourseSections(id);
            }
        };
    }

	public Converter<String, CourseSections> getStringToCourseSectionsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.CourseSections>() {
            public com.edu.leap.domain.CourseSections convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), CourseSections.class);
            }
        };
    }

	public Converter<Modules, String> getModulesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Modules, java.lang.String>() {
            public String convert(Modules modules) {
                return new StringBuilder().append(modules.getName()).append(' ').append(modules.getCron()).append(' ').append(modules.getLastcron()).append(' ').append(modules.getSearch()).toString();
            }
        };
    }

	public Converter<Long, Modules> getIdToModulesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Modules>() {
            public com.edu.leap.domain.Modules convert(java.lang.Long id) {
                return Modules.findModules(id);
            }
        };
    }

	public Converter<String, Modules> getStringToModulesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Modules>() {
            public com.edu.leap.domain.Modules convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Modules.class);
            }
        };
    }

	public Converter<Permission, String> getPermissionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Permission, java.lang.String>() {
            public String convert(Permission permission) {
                return new StringBuilder().append(permission.getPermissiontype()).append(' ').append(permission.getDescription()).toString();
            }
        };
    }

	public Converter<Long, Permission> getIdToPermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Permission>() {
            public com.edu.leap.domain.Permission convert(java.lang.Long id) {
                return Permission.findPermission(id);
            }
        };
    }

	public Converter<String, Permission> getStringToPermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Permission>() {
            public com.edu.leap.domain.Permission convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Permission.class);
            }
        };
    }

	public Converter<Role, String> getRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Role, java.lang.String>() {
            public String convert(Role role) {
                return new StringBuilder().append(role.getRoletype()).append(' ').append(role.getDescription()).append(' ').append(role.getSortorder()).append(' ').append(role.getArchetype()).toString();
            }
        };
    }

	public Converter<Long, Role> getIdToRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Role>() {
            public com.edu.leap.domain.Role convert(java.lang.Long id) {
                return Role.findRole(id);
            }
        };
    }

	public Converter<String, Role> getStringToRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Role>() {
            public com.edu.leap.domain.Role convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Role.class);
            }
        };
    }

	public Converter<User, String> getUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.User, java.lang.String>() {
            public String convert(User user) {
                return new StringBuilder().append(user.getAuth()).append(' ').append(user.getUsername()).append(' ').append(user.getPassword()).append(' ').append(user.getFirstname()).toString();
            }
        };
    }

	public Converter<Long, User> getIdToUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.User>() {
            public com.edu.leap.domain.User convert(java.lang.Long id) {
                return User.findUser(id);
            }
        };
    }

	public Converter<String, User> getStringToUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.User>() {
            public com.edu.leap.domain.User convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), User.class);
            }
        };
    }

	public Converter<Userpermission, String> getUserpermissionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.edu.leap.domain.Userpermission, java.lang.String>() {
            public String convert(Userpermission userpermission) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<Long, Userpermission> getIdToUserpermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.edu.leap.domain.Userpermission>() {
            public com.edu.leap.domain.Userpermission convert(java.lang.Long id) {
                return Userpermission.findUserpermission(id);
            }
        };
    }

	public Converter<String, Userpermission> getStringToUserpermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.edu.leap.domain.Userpermission>() {
            public com.edu.leap.domain.Userpermission convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Userpermission.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getCompanyToStringConverter());
        registry.addConverter(getIdToCompanyConverter());
        registry.addConverter(getStringToCompanyConverter());
        registry.addConverter(getCourseToStringConverter());
        registry.addConverter(getIdToCourseConverter());
        registry.addConverter(getStringToCourseConverter());
        registry.addConverter(getCourseCategoriesToStringConverter());
        registry.addConverter(getIdToCourseCategoriesConverter());
        registry.addConverter(getStringToCourseCategoriesConverter());
        registry.addConverter(getCourseCompletionsToStringConverter());
        registry.addConverter(getIdToCourseCompletionsConverter());
        registry.addConverter(getStringToCourseCompletionsConverter());
        registry.addConverter(getCourseFormatOptionsToStringConverter());
        registry.addConverter(getIdToCourseFormatOptionsConverter());
        registry.addConverter(getStringToCourseFormatOptionsConverter());
        registry.addConverter(getCourseModulesToStringConverter());
        registry.addConverter(getIdToCourseModulesConverter());
        registry.addConverter(getStringToCourseModulesConverter());
        registry.addConverter(getCourseModulesCompletionToStringConverter());
        registry.addConverter(getIdToCourseModulesCompletionConverter());
        registry.addConverter(getStringToCourseModulesCompletionConverter());
        registry.addConverter(getCourseSectionsToStringConverter());
        registry.addConverter(getIdToCourseSectionsConverter());
        registry.addConverter(getStringToCourseSectionsConverter());
        registry.addConverter(getModulesToStringConverter());
        registry.addConverter(getIdToModulesConverter());
        registry.addConverter(getStringToModulesConverter());
        registry.addConverter(getPermissionToStringConverter());
        registry.addConverter(getIdToPermissionConverter());
        registry.addConverter(getStringToPermissionConverter());
        registry.addConverter(getRoleToStringConverter());
        registry.addConverter(getIdToRoleConverter());
        registry.addConverter(getStringToRoleConverter());
        registry.addConverter(getUserToStringConverter());
        registry.addConverter(getIdToUserConverter());
        registry.addConverter(getStringToUserConverter());
        registry.addConverter(getUserpermissionToStringConverter());
        registry.addConverter(getIdToUserpermissionConverter());
        registry.addConverter(getStringToUserpermissionConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
