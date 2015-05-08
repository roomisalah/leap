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
@Table(name = "course_categories")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "course_categories")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courses", "courseCategorieses", "companyid", "categoryparentid" })
public class CourseCategories {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryid")
    private Long categoryid;

	public Long getCategoryid() {
        return this.categoryid;
    }

	public void setCategoryid(Long id) {
        this.categoryid = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseCategories().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseCategorieses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseCategories o", Long.class).getSingleResult();
    }

	public static List<CourseCategories> findAllCourseCategorieses() {
        return entityManager().createQuery("SELECT o FROM CourseCategories o", CourseCategories.class).getResultList();
    }

	public static List<CourseCategories> findAllCourseCategorieses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCategories o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCategories.class).getResultList();
    }

	public static CourseCategories findCourseCategories(Long categoryid) {
        if (categoryid == null) return null;
        return entityManager().find(CourseCategories.class, categoryid);
    }

	public static List<CourseCategories> findCourseCategoriesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseCategories o", CourseCategories.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseCategories> findCourseCategoriesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCategories o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCategories.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseCategories attached = CourseCategories.findCourseCategories(this.categoryid);
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
    public CourseCategories merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseCategories merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@OneToMany(mappedBy = "categoryid", cascade = CascadeType.ALL)
    private Set<Course> courses;

	@OneToMany(mappedBy = "categoryparentid", cascade = CascadeType.ALL)
    private Set<CourseCategories> courseCategorieses;

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "categoryparentid", referencedColumnName = "categoryid", nullable = false, insertable = false, updatable = false)
    private CourseCategories categoryparentid;

	@Column(name = "title", length = 255)
    @NotNull
    private String title;

	@Column(name = "categorynumber", length = 100)
    private String categorynumber;

	@Column(name = "description")
    private String description;

	@Column(name = "sortorder")
    @NotNull
    private Long sortorder;

	@Column(name = "status")
    @NotNull
    private boolean status;

	@Column(name = "createdtime")
    @NotNull
    private Long createdtime;

	@Column(name = "modifiedtime")
    @NotNull
    private Long modifiedtime;

	@Column(name = "categorydepth")
    @NotNull
    private Long categorydepth;

	@Column(name = "path", length = 255)
    @NotNull
    private String path;

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

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public CourseCategories getCategoryparentid() {
        return categoryparentid;
    }

	public void setCategoryparentid(CourseCategories categoryparentid) {
        this.categoryparentid = categoryparentid;
    }

	public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getCategorynumber() {
        return categorynumber;
    }

	public void setCategorynumber(String categorynumber) {
        this.categorynumber = categorynumber;
    }

	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public Long getSortorder() {
        return sortorder;
    }

	public void setSortorder(Long sortorder) {
        this.sortorder = sortorder;
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

	public Long getCategorydepth() {
        return categorydepth;
    }

	public void setCategorydepth(Long categorydepth) {
        this.categorydepth = categorydepth;
    }

	public String getPath() {
        return path;
    }

	public void setPath(String path) {
        this.path = path;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courses", "courseCategorieses", "companyid", "categoryparentid").toString();
    }
}
