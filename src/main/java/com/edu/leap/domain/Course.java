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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

@Entity
@Table(name = "course")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courseCompletionss", "courseFormatOptionss", "courseModuleses", "courseSectionss", "categoryid", "companyid" })
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courseid")
    private Long courseid;

	public Long getCourseid() {
        return this.courseid;
    }

	public void setCourseid(Long id) {
        this.courseid = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courseCompletionss", "courseFormatOptionss", "courseModuleses", "courseSectionss", "categoryid", "companyid").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Course().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Course o", Long.class).getSingleResult();
    }

	public static List<Course> findAllCourses() {
        return entityManager().createQuery("SELECT o FROM Course o", Course.class).getResultList();
    }

	public static List<Course> findAllCourses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Course o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Course.class).getResultList();
    }

	public static Course findCourse(Long courseid) {
        if (courseid == null) return null;
        return entityManager().find(Course.class, courseid);
    }

	public static List<Course> findCourseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Course o", Course.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Course> findCourseEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Course o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Course.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Course attached = Course.findCourse(this.courseid);
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
    public Course merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Course merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@OneToMany(mappedBy = "courseid", cascade = CascadeType.ALL)
    private Set<CourseCompletions> courseCompletionss;

	@OneToMany(mappedBy = "courseid", cascade = CascadeType.ALL)
    private Set<CourseFormatOptions> courseFormatOptionss;

	@OneToMany(mappedBy = "courseid", cascade = CascadeType.ALL)
    private Set<CourseModules> courseModuleses;

	@OneToMany(mappedBy = "courseid", cascade = CascadeType.ALL)
    private Set<CourseSections> courseSectionss;

	@ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid", nullable = false)
    private CourseCategories categoryid;

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@Column(name = "sortorder")
    @NotNull
    private Long sortorder;

	@Column(name = "fullname", length = 254)
    @NotNull
    private String fullname;

	@Column(name = "shortname", length = 255)
    @NotNull
    private String shortname;

	@Column(name = "coursenumber", length = 100)
    @NotNull
    private String coursenumber;

	@Column(name = "summary")
    private String summary;

	@Column(name = "summaryformat")
    @NotNull
    private Short summaryformat;

	@Column(name = "format", length = 21)
    @NotNull
    private String format;

	@Column(name = "showgrades")
    @NotNull
    private Short showgrades;

	@Column(name = "startdate")
    @NotNull
    private Long startdate;

	@Column(name = "enddate")
    @NotNull
    private Long enddate;

	@Column(name = "status")
    @NotNull
    private boolean status;

	@Column(name = "createdtime")
    @NotNull
    private Long createdtime;

	@Column(name = "modifiedtime")
    @NotNull
    private Long modifiedtime;

	@Column(name = "enablecompletion")
    @NotNull
    private boolean enablecompletion;

	@Column(name = "completionnotify")
    @NotNull
    private boolean completionnotify;

	@Column(name = "publishstatus")
    @NotNull
    private Integer publishstatus;

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

	public Set<CourseSections> getCourseSectionss() {
        return courseSectionss;
    }

	public void setCourseSectionss(Set<CourseSections> courseSectionss) {
        this.courseSectionss = courseSectionss;
    }

	public CourseCategories getCategoryid() {
        return categoryid;
    }

	public void setCategoryid(CourseCategories categoryid) {
        this.categoryid = categoryid;
    }

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public Long getSortorder() {
        return sortorder;
    }

	public void setSortorder(Long sortorder) {
        this.sortorder = sortorder;
    }

	public String getFullname() {
        return fullname;
    }

	public void setFullname(String fullname) {
        this.fullname = fullname;
    }

	public String getShortname() {
        return shortname;
    }

	public void setShortname(String shortname) {
        this.shortname = shortname;
    }

	public String getCoursenumber() {
        return coursenumber;
    }

	public void setCoursenumber(String coursenumber) {
        this.coursenumber = coursenumber;
    }

	public String getSummary() {
        return summary;
    }

	public void setSummary(String summary) {
        this.summary = summary;
    }

	public Short getSummaryformat() {
        return summaryformat;
    }

	public void setSummaryformat(Short summaryformat) {
        this.summaryformat = summaryformat;
    }

	public String getFormat() {
        return format;
    }

	public void setFormat(String format) {
        this.format = format;
    }

	public Short getShowgrades() {
        return showgrades;
    }

	public void setShowgrades(Short showgrades) {
        this.showgrades = showgrades;
    }

	public Long getStartdate() {
        return startdate;
    }

	public void setStartdate(Long startdate) {
        this.startdate = startdate;
    }

	public Long getEnddate() {
        return enddate;
    }

	public void setEnddate(Long enddate) {
        this.enddate = enddate;
    }

	public boolean isStatus() {
        return status;
    }

	public void setStatus(boolean status) {
        this.status = status;
    }

	public Long getCreatedtime() {
        return createdtime;
    }

	public void setCreatedtime(Long createdtime) {
        this.createdtime = createdtime;
    }

	public Long getModifiedtime() {
        return modifiedtime;
    }

	public void setModifiedtime(Long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

	public boolean isEnablecompletion() {
        return enablecompletion;
    }

	public void setEnablecompletion(boolean enablecompletion) {
        this.enablecompletion = enablecompletion;
    }

	public boolean isCompletionnotify() {
        return completionnotify;
    }

	public void setCompletionnotify(boolean completionnotify) {
        this.completionnotify = completionnotify;
    }

	public Integer getPublishstatus() {
        return publishstatus;
    }

	public void setPublishstatus(Integer publishstatus) {
        this.publishstatus = publishstatus;
    }
}
