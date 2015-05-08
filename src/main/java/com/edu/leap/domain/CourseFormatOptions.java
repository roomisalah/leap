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
@Table(name = "course_format_options")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_format_options")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companyid", "courseid" })
public class CourseFormatOptions {

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid", nullable = false)
    private Course courseid;

	@Column(name = "format", length = 21, unique = true)
    @NotNull
    private String format;

	@Column(name = "sectionid", unique = true)
    @NotNull
    private Long sectionid;

	@Column(name = "name", length = 100, unique = true)
    @NotNull
    private String name;

	@Column(name = "value")
    private String value;

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public Course getCourseid() {
        return courseid;
    }

	public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

	public String getFormat() {
        return format;
    }

	public void setFormat(String format) {
        this.format = format;
    }

	public Long getSectionid() {
        return sectionid;
    }

	public void setSectionid(Long sectionid) {
        this.sectionid = sectionid;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getValue() {
        return value;
    }

	public void setValue(String value) {
        this.value = value;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("companyid", "courseid").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseFormatOptions().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseFormatOptionses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseFormatOptions o", Long.class).getSingleResult();
    }

	public static List<CourseFormatOptions> findAllCourseFormatOptionses() {
        return entityManager().createQuery("SELECT o FROM CourseFormatOptions o", CourseFormatOptions.class).getResultList();
    }

	public static List<CourseFormatOptions> findAllCourseFormatOptionses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseFormatOptions o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseFormatOptions.class).getResultList();
    }

	public static CourseFormatOptions findCourseFormatOptions(Long formatoptionid) {
        if (formatoptionid == null) return null;
        return entityManager().find(CourseFormatOptions.class, formatoptionid);
    }

	public static List<CourseFormatOptions> findCourseFormatOptionsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseFormatOptions o", CourseFormatOptions.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseFormatOptions> findCourseFormatOptionsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseFormatOptions o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseFormatOptions.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseFormatOptions attached = CourseFormatOptions.findCourseFormatOptions(this.formatoptionid);
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
    public CourseFormatOptions merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseFormatOptions merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "formatoptionid")
    private Long formatoptionid;

	public Long getFormatoptionid() {
        return this.formatoptionid;
    }

	public void setFormatoptionid(Long id) {
        this.formatoptionid = id;
    }
}
