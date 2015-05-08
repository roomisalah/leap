package com.edu.leap.domain;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "course_modules_completion")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_modules_completion")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companyid", "userid", "coursemoduleid" })
public class CourseModulesCompletion {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("companyid", "userid", "coursemoduleid").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courmodcompid")
    private Long courmodcompid;

	public Long getCourmodcompid() {
        return this.courmodcompid;
    }

	public void setCourmodcompid(Long id) {
        this.courmodcompid = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseModulesCompletion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseModulesCompletions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseModulesCompletion o", Long.class).getSingleResult();
    }

	public static List<CourseModulesCompletion> findAllCourseModulesCompletions() {
        return entityManager().createQuery("SELECT o FROM CourseModulesCompletion o", CourseModulesCompletion.class).getResultList();
    }

	public static List<CourseModulesCompletion> findAllCourseModulesCompletions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseModulesCompletion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseModulesCompletion.class).getResultList();
    }

	public static CourseModulesCompletion findCourseModulesCompletion(Long courmodcompid) {
        if (courmodcompid == null) return null;
        return entityManager().find(CourseModulesCompletion.class, courmodcompid);
    }

	public static List<CourseModulesCompletion> findCourseModulesCompletionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseModulesCompletion o", CourseModulesCompletion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseModulesCompletion> findCourseModulesCompletionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseModulesCompletion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseModulesCompletion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseModulesCompletion attached = CourseModulesCompletion.findCourseModulesCompletion(this.courmodcompid);
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
    public CourseModulesCompletion merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseModulesCompletion merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User userid;

	@ManyToOne
    @JoinColumn(name = "coursemoduleid", referencedColumnName = "coursemoduleid", nullable = false)
    private CourseModules coursemoduleid;

	@Column(name = "completionstate")
    @NotNull
    private boolean completionstate;

	@Column(name = "viewed")
    private Boolean viewed;

	@Column(name = "modifiedtime")
    @NotNull
    private Long modifiedtime;

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public User getUserid() {
        return userid;
    }

	public void setUserid(User userid) {
        this.userid = userid;
    }

	public CourseModules getCoursemoduleid() {
        return coursemoduleid;
    }

	public void setCoursemoduleid(CourseModules coursemoduleid) {
        this.coursemoduleid = coursemoduleid;
    }

	public boolean isCompletionstate() {
        return completionstate;
    }

	public void setCompletionstate(boolean completionstate) {
        this.completionstate = completionstate;
    }

	public Boolean getViewed() {
        return viewed;
    }

	public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

	public Long getModifiedtime() {
        return modifiedtime;
    }

	public void setModifiedtime(Long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
}
