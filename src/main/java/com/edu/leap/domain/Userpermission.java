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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "userpermission")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "userpermission")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "companyid", "permissiontype", "userid" })
public class Userpermission {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Userpermission().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countUserpermissions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Userpermission o", Long.class).getSingleResult();
    }

	public static List<Userpermission> findAllUserpermissions() {
        return entityManager().createQuery("SELECT o FROM Userpermission o", Userpermission.class).getResultList();
    }

	public static List<Userpermission> findAllUserpermissions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Userpermission o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Userpermission.class).getResultList();
    }

	public static Userpermission findUserpermission(Long id) {
        if (id == null) return null;
        return entityManager().find(Userpermission.class, id);
    }

	public static List<Userpermission> findUserpermissionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Userpermission o", Userpermission.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Userpermission> findUserpermissionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Userpermission o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Userpermission.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Userpermission attached = Userpermission.findUserpermission(this.id);
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
    public Userpermission merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Userpermission merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "permissiontype", referencedColumnName = "permissiontype", nullable = false)
    private Permission permissiontype;

	@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User userid;

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public Permission getPermissiontype() {
        return permissiontype;
    }

	public void setPermissiontype(Permission permissiontype) {
        this.permissiontype = permissiontype;
    }

	public User getUserid() {
        return userid;
    }

	public void setUserid(User userid) {
        this.userid = userid;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("companyid", "permissiontype", "userid").toString();
    }
}
