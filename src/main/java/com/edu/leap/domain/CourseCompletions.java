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

@Configurable
@Entity
@Table(name = "course_completions")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_completions")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companyid", "userid", "courseid" })
public class CourseCompletions {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coursecompid")
    private Long coursecompid;

	public Long getCoursecompid() {
        return this.coursecompid;
    }

	public void setCoursecompid(Long id) {
        this.coursecompid = id;
    }

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User userid;

	@ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid", nullable = false)
    private Course courseid;

	@Column(name = "timeenrolled")
    @NotNull
    private Long timeenrolled;

	@Column(name = "timestarted")
    @NotNull
    private Long timestarted;

	@Column(name = "timecompleted")
    private Long timecompleted;

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

	public Course getCourseid() {
        return courseid;
    }

	public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

	public Long getTimeenrolled() {
        return timeenrolled;
    }

	public void setTimeenrolled(Long timeenrolled) {
        this.timeenrolled = timeenrolled;
    }

	public Long getTimestarted() {
        return timestarted;
    }

	public void setTimestarted(Long timestarted) {
        this.timestarted = timestarted;
    }

	public Long getTimecompleted() {
        return timecompleted;
    }

	public void setTimecompleted(Long timecompleted) {
        this.timecompleted = timecompleted;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("companyid", "userid", "courseid").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseCompletions().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseCompletionses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseCompletions o", Long.class).getSingleResult();
    }

	public static List<CourseCompletions> findAllCourseCompletionses() {
        return entityManager().createQuery("SELECT o FROM CourseCompletions o", CourseCompletions.class).getResultList();
    }

	public static List<CourseCompletions> findAllCourseCompletionses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCompletions o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCompletions.class).getResultList();
    }

	public static CourseCompletions findCourseCompletions(Long coursecompid) {
        if (coursecompid == null) return null;
        return entityManager().find(CourseCompletions.class, coursecompid);
    }

	public static List<CourseCompletions> findCourseCompletionsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseCompletions o", CourseCompletions.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseCompletions> findCourseCompletionsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCompletions o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCompletions.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseCompletions attached = CourseCompletions.findCourseCompletions(this.coursecompid);
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
    public CourseCompletions merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseCompletions merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
