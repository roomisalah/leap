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

@Configurable
@Entity
@Table(name = "course_modules")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_modules")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courseModulesCompletions", "companyid", "moduleid", "courseid" })
public class CourseModules {

	@OneToMany(mappedBy = "coursemoduleid", cascade = CascadeType.ALL)
    private Set<CourseModulesCompletion> courseModulesCompletions;

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "moduleid", referencedColumnName = "moduleid", nullable = false)
    private Modules moduleid;

	@ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid", nullable = false)
    private Course courseid;

	@Column(name = "instance")
    @NotNull
    private Long instance;

	@Column(name = "section")
    @NotNull
    private Long section;

	@Column(name = "idnumber", length = 100)
    private String idnumber;

	@Column(name = "addedon")
    @NotNull
    private Long addedon;

	@Column(name = "score")
    @NotNull
    private Short score;

	@Column(name = "indent")
    @NotNull
    private Integer indent;

	@Column(name = "status")
    @NotNull
    private boolean status;

	@Column(name = "completion")
    @NotNull
    private boolean completion;

	public Set<CourseModulesCompletion> getCourseModulesCompletions() {
        return courseModulesCompletions;
    }

	public void setCourseModulesCompletions(Set<CourseModulesCompletion> courseModulesCompletions) {
        this.courseModulesCompletions = courseModulesCompletions;
    }

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public Modules getModuleid() {
        return moduleid;
    }

	public void setModuleid(Modules moduleid) {
        this.moduleid = moduleid;
    }

	public Course getCourseid() {
        return courseid;
    }

	public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

	public Long getInstance() {
        return instance;
    }

	public void setInstance(Long instance) {
        this.instance = instance;
    }

	public Long getSection() {
        return section;
    }

	public void setSection(Long section) {
        this.section = section;
    }

	public String getIdnumber() {
        return idnumber;
    }

	public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

	public Long getAddedon() {
        return addedon;
    }

	public void setAddedon(Long addedon) {
        this.addedon = addedon;
    }

	public Short getScore() {
        return score;
    }

	public void setScore(Short score) {
        this.score = score;
    }

	public Integer getIndent() {
        return indent;
    }

	public void setIndent(Integer indent) {
        this.indent = indent;
    }

	public boolean isStatus() {
        return status;
    }

	public void setStatus(boolean status) {
        this.status = status;
    }

	public boolean isCompletion() {
        return completion;
    }

	public void setCompletion(boolean completion) {
        this.completion = completion;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseModules().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseModuleses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseModules o", Long.class).getSingleResult();
    }

	public static List<CourseModules> findAllCourseModuleses() {
        return entityManager().createQuery("SELECT o FROM CourseModules o", CourseModules.class).getResultList();
    }

	public static List<CourseModules> findAllCourseModuleses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseModules o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseModules.class).getResultList();
    }

	public static CourseModules findCourseModules(Long coursemoduleid) {
        if (coursemoduleid == null) return null;
        return entityManager().find(CourseModules.class, coursemoduleid);
    }

	public static List<CourseModules> findCourseModulesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseModules o", CourseModules.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseModules> findCourseModulesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseModules o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseModules.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseModules attached = CourseModules.findCourseModules(this.coursemoduleid);
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
    public CourseModules merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseModules merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coursemoduleid")
    private Long coursemoduleid;

	public Long getCoursemoduleid() {
        return this.coursemoduleid;
    }

	public void setCoursemoduleid(Long id) {
        this.coursemoduleid = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courseModulesCompletions", "companyid", "moduleid", "courseid").toString();
    }
}
