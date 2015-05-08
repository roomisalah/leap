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
@Table(name = "permission")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "permission")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "userpermissions" })
public class Permission {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Permission().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPermissions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Permission o", Long.class).getSingleResult();
    }

	public static List<Permission> findAllPermissions() {
        return entityManager().createQuery("SELECT o FROM Permission o", Permission.class).getResultList();
    }

	public static List<Permission> findAllPermissions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Permission o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Permission.class).getResultList();
    }

	public static Permission findPermission(Long id) {
        if (id == null) return null;
        return entityManager().find(Permission.class, id);
    }

	public static List<Permission> findPermissionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Permission o", Permission.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Permission> findPermissionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Permission o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Permission.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Permission attached = Permission.findPermission(this.id);
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
    public Permission merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Permission merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@OneToMany(mappedBy = "permissiontype", cascade = CascadeType.ALL)
    private Set<Userpermission> userpermissions;

	@Column(name = "permissiontype", length = 100, unique = true)
    @NotNull
    private String permissiontype;

	@Column(name = "description", length = 100)
    @NotNull
    private String description;

	@Column(name = "active")
    @NotNull
    private boolean active;

	public Set<Userpermission> getUserpermissions() {
        return userpermissions;
    }

	public void setUserpermissions(Set<Userpermission> userpermissions) {
        this.userpermissions = userpermissions;
    }

	public String getPermissiontype() {
        return permissiontype;
    }

	public void setPermissiontype(String permissiontype) {
        this.permissiontype = permissiontype;
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

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("userpermissions").toString();
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
}
