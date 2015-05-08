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
@Table(name = "modules")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "modules")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courseModuleses", "companyid" })
public class Modules {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Modules().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countModuleses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Modules o", Long.class).getSingleResult();
    }

	public static List<Modules> findAllModuleses() {
        return entityManager().createQuery("SELECT o FROM Modules o", Modules.class).getResultList();
    }

	public static List<Modules> findAllModuleses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Modules o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Modules.class).getResultList();
    }

	public static Modules findModules(Long moduleid) {
        if (moduleid == null) return null;
        return entityManager().find(Modules.class, moduleid);
    }

	public static List<Modules> findModulesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Modules o", Modules.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Modules> findModulesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Modules o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Modules.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Modules attached = Modules.findModules(this.moduleid);
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
    public Modules merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Modules merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@OneToMany(mappedBy = "moduleid", cascade = CascadeType.ALL)
    private Set<CourseModules> courseModuleses;

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@Column(name = "name", length = 20)
    @NotNull
    private String name;

	@Column(name = "cron")
    @NotNull
    private Long cron;

	@Column(name = "lastcron")
    @NotNull
    private Long lastcron;

	@Column(name = "search", length = 255)
    @NotNull
    private String search;

	@Column(name = "status")
    @NotNull
    private boolean status;

	public Set<CourseModules> getCourseModuleses() {
        return courseModuleses;
    }

	public void setCourseModuleses(Set<CourseModules> courseModuleses) {
        this.courseModuleses = courseModuleses;
    }

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public Long getCron() {
        return cron;
    }

	public void setCron(Long cron) {
        this.cron = cron;
    }

	public Long getLastcron() {
        return lastcron;
    }

	public void setLastcron(Long lastcron) {
        this.lastcron = lastcron;
    }

	public String getSearch() {
        return search;
    }

	public void setSearch(String search) {
        this.search = search;
    }

	public boolean isStatus() {
        return status;
    }

	public void setStatus(boolean status) {
        this.status = status;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courseModuleses", "companyid").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "moduleid")
    private Long moduleid;

	public Long getModuleid() {
        return this.moduleid;
    }

	public void setModuleid(Long id) {
        this.moduleid = id;
    }
}
