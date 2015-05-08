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
@Table(name = "course_sections")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_sections")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companyid", "courseid" })
public class CourseSections {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("companyid", "courseid").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseSections().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseSectionses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseSections o", Long.class).getSingleResult();
    }

	public static List<CourseSections> findAllCourseSectionses() {
        return entityManager().createQuery("SELECT o FROM CourseSections o", CourseSections.class).getResultList();
    }

	public static List<CourseSections> findAllCourseSectionses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseSections o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseSections.class).getResultList();
    }

	public static CourseSections findCourseSections(Long coursesectionid) {
        if (coursesectionid == null) return null;
        return entityManager().find(CourseSections.class, coursesectionid);
    }

	public static List<CourseSections> findCourseSectionsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseSections o", CourseSections.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseSections> findCourseSectionsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseSections o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseSections.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseSections attached = CourseSections.findCourseSections(this.coursesectionid);
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
    public CourseSections merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseSections merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coursesectionid")
    private Long coursesectionid;

	public Long getCoursesectionid() {
        return this.coursesectionid;
    }

	public void setCoursesectionid(Long id) {
        this.coursesectionid = id;
    }

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid", nullable = false)
    private Course courseid;

	@Column(name = "section", unique = true)
    @NotNull
    private Long section;

	@Column(name = "name", length = 255)
    private String name;

	@Column(name = "summary")
    private String summary;

	@Column(name = "summaryformat")
    @NotNull
    private Short summaryformat;

	@Column(name = "sequence")
    private String sequence;

	@Column(name = "status")
    @NotNull
    private boolean status;

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

	public Long getSection() {
        return section;
    }

	public void setSection(Long section) {
        this.section = section;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
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

	public String getSequence() {
        return sequence;
    }

	public void setSequence(String sequence) {
        this.sequence = sequence;
    }

	public boolean isStatus() {
        return status;
    }

	public void setStatus(boolean status) {
        this.status = status;
    }
}
