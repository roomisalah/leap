package com.edu.leap.domain;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "company")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "company")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courses", "courseCategorieses", "courseCompletionss", "courseFormatOptionss", "courseModuleses", "courseModulesCompletions", "courseSectionss", "moduleses", "users", "userpermissions" })
public class Company {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Company().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCompanys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Company o", Long.class).getSingleResult();
    }

	public static List<Company> findAllCompanys() {
        return entityManager().createQuery("SELECT o FROM Company o", Company.class).getResultList();
    }

	public static List<Company> findAllCompanys(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Company o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Company.class).getResultList();
    }

	public static Company findCompany(Long companyid) {
        if (companyid == null) return null;
        return entityManager().find(Company.class, companyid);
    }

	public static List<Company> findCompanyEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Company o", Company.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Company> findCompanyEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Company o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Company.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Company attached = Company.findCompany(this.companyid);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Company merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Company merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courses", "courseCategorieses", "courseCompletionss", "courseFormatOptionss", "courseModuleses", "courseModulesCompletions", "courseSectionss", "moduleses", "users", "userpermissions").toString();
    }

	@OneToMany(mappedBy = "companyid")
    private Set<Course> courses;

	@OneToMany(mappedBy = "companyid", cascade = CascadeType.ALL)
    private Set<CourseCategories> courseCategorieses;

	@OneToMany(mappedBy = "companyid")
    private Set<CourseCompletions> courseCompletionss;

	@OneToMany(mappedBy = "companyid")
    private Set<CourseFormatOptions> courseFormatOptionss;

	@OneToMany(mappedBy = "companyid")
    private Set<CourseModules> courseModuleses;

	@OneToMany(mappedBy = "companyid")
    private Set<CourseModulesCompletion> courseModulesCompletions;

	@OneToMany(mappedBy = "companyid")
    private Set<CourseSections> courseSectionss;

	@OneToMany(mappedBy = "companyid", cascade = CascadeType.ALL)
    private Set<Modules> moduleses;

	@OneToMany(mappedBy = "companyid", cascade = CascadeType.ALL)
    private Set<User> users;

	@OneToMany(mappedBy = "companyid", cascade = CascadeType.ALL)
    private Set<Userpermission> userpermissions;

	@Column(name = "companyname", length = 255)
    @NotNull
    private String companyname;

	@Column(name = "description", length = 100)
    @NotNull
    private String description;

	@Column(name = "active")
    @NotNull
    private boolean active;

	@Column(name = "createtime")
    @NotNull
    private Long createtime;

	@Column(name = "updatetime")
    @NotNull
    private Long updatetime;

	public Set<Course> getCourses() {
        return courses;
    }

	public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

	public Set<CourseCategories> getCourseCategorieses() {
        return courseCategorieses;
    }

	public void setCourseCategorieses(Set<CourseCategories> courseCategorieses) {
        this.courseCategorieses = courseCategorieses;
    }

	public Set<CourseCompletions> getCourseCompletionss() {
        return courseCompletionss;
    }

	public void setCourseCompletionss(Set<CourseCompletions> courseCompletionss) {
        this.courseCompletionss = courseCompletionss;
    }

	public Set<CourseFormatOptions> getCourseFormatOptionss() {
        return courseFormatOptionss;
    }

	public void setCourseFormatOptionss(Set<CourseFormatOptions> courseFormatOptionss) {
        this.courseFormatOptionss = courseFormatOptionss;
    }

	public Set<CourseModules> getCourseModuleses() {
        return courseModuleses;
    }

	public void setCourseModuleses(Set<CourseModules> courseModuleses) {
        this.courseModuleses = courseModuleses;
    }

	public Set<CourseModulesCompletion> getCourseModulesCompletions() {
        return courseModulesCompletions;
    }

	public void setCourseModulesCompletions(Set<CourseModulesCompletion> courseModulesCompletions) {
        this.courseModulesCompletions = courseModulesCompletions;
    }

	public Set<CourseSections> getCourseSectionss() {
        return courseSectionss;
    }

	public void setCourseSectionss(Set<CourseSections> courseSectionss) {
        this.courseSectionss = courseSectionss;
    }

	public Set<Modules> getModuleses() {
        return moduleses;
    }

	public void setModuleses(Set<Modules> moduleses) {
        this.moduleses = moduleses;
    }

	public Set<User> getUsers() {
        return users;
    }

	public void setUsers(Set<User> users) {
        this.users = users;
    }

	public Set<Userpermission> getUserpermissions() {
        return userpermissions;
    }

	public void setUserpermissions(Set<Userpermission> userpermissions) {
        this.userpermissions = userpermissions;
    }

	public String getCompanyname() {
        return companyname;
    }

	public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public boolean isActive() {
        return active;
    }

	public void setActive(boolean active) {
        this.active = active;
    }

	public Long getCreatetime() {
        return createtime;
    }

	public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public Long getUpdatetime() {
        return updatetime;
    }

	public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "companyid")
    private Long companyid;

	public Long getCompanyid() {
        return this.companyid;
    }

	public void setCompanyid(Long id) {
        this.companyid = id;
    }
}
