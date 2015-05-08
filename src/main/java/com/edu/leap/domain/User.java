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
@Table(name = "user")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "user")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "courseCompletionss", "courseModulesCompletions", "userpermissions", "companyid", "roletype" })
public class User {

	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private Set<CourseCompletions> courseCompletionss;

	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private Set<CourseModulesCompletion> courseModulesCompletions;

	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private Set<Userpermission> userpermissions;

	@ManyToOne
    @JoinColumn(name = "companyid", referencedColumnName = "companyid", nullable = false)
    private Company companyid;

	@ManyToOne
    @JoinColumn(name = "roletype", referencedColumnName = "roletype", nullable = false)
    private Role roletype;

	@Column(name = "auth", length = 20)
    @NotNull
    private String auth;

	@Column(name = "active")
    @NotNull
    private boolean active;

	@Column(name = "username", length = 100, unique = true)
    @NotNull
    private String username;

	@Column(name = "password", length = 255)
    @NotNull
    private String password;

	@Column(name = "firstname", length = 100)
    @NotNull
    private String firstname;

	@Column(name = "lastname", length = 100)
    @NotNull
    private String lastname;

	@Column(name = "email", length = 100)
    @NotNull
    private String email;

	@Column(name = "createtime")
    @NotNull
    private Long createtime;

	@Column(name = "updatetime")
    @NotNull
    private Long updatetime;

	@Column(name = "emailstop")
    @NotNull
    private boolean emailstop;

	@Column(name = "skype", length = 50)
    @NotNull
    private String skype;

	@Column(name = "yahoo", length = 50)
    @NotNull
    private String yahoo;

	@Column(name = "gmail", length = 50)
    @NotNull
    private String gmail;

	@Column(name = "phone1", length = 20)
    @NotNull
    private String phone1;

	@Column(name = "phone2", length = 20)
    @NotNull
    private String phone2;

	@Column(name = "institution", length = 255)
    @NotNull
    private String institution;

	@Column(name = "department", length = 255)
    @NotNull
    private String department;

	@Column(name = "address", length = 255)
    @NotNull
    private String address;

	@Column(name = "city", length = 120)
    @NotNull
    private String city;

	@Column(name = "country", length = 2)
    @NotNull
    private String country;

	@Column(name = "lang", length = 30)
    @NotNull
    private String lang;

	@Column(name = "calendartype", length = 30)
    @NotNull
    private String calendartype;

	@Column(name = "theme", length = 50)
    @NotNull
    private String theme;

	@Column(name = "timezone", length = 100)
    @NotNull
    private String timezone;

	@Column(name = "currentlogin")
    @NotNull
    private Long currentlogin;

	@Column(name = "autosubscribe")
    @NotNull
    private boolean autosubscribe;

	public Set<CourseCompletions> getCourseCompletionss() {
        return courseCompletionss;
    }

	public void setCourseCompletionss(Set<CourseCompletions> courseCompletionss) {
        this.courseCompletionss = courseCompletionss;
    }

	public Set<CourseModulesCompletion> getCourseModulesCompletions() {
        return courseModulesCompletions;
    }

	public void setCourseModulesCompletions(Set<CourseModulesCompletion> courseModulesCompletions) {
        this.courseModulesCompletions = courseModulesCompletions;
    }

	public Set<Userpermission> getUserpermissions() {
        return userpermissions;
    }

	public void setUserpermissions(Set<Userpermission> userpermissions) {
        this.userpermissions = userpermissions;
    }

	public Company getCompanyid() {
        return companyid;
    }

	public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

	public Role getRoletype() {
        return roletype;
    }

	public void setRoletype(Role roletype) {
        this.roletype = roletype;
    }

	public String getAuth() {
        return auth;
    }

	public void setAuth(String auth) {
        this.auth = auth;
    }

	public boolean isActive() {
        return active;
    }

	public void setActive(boolean active) {
        this.active = active;
    }

	public String getUsername() {
        return username;
    }

	public void setUsername(String username) {
        this.username = username;
    }

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getFirstname() {
        return firstname;
    }

	public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

	public String getLastname() {
        return lastname;
    }

	public void setLastname(String lastname) {
        this.lastname = lastname;
    }

	public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public Long getCreatetime() {
        return createtime;
    }

	public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public Long getUpdatetime() {
        return updatetime;
    }

	public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

	public boolean isEmailstop() {
        return emailstop;
    }

	public void setEmailstop(boolean emailstop) {
        this.emailstop = emailstop;
    }

	public String getSkype() {
        return skype;
    }

	public void setSkype(String skype) {
        this.skype = skype;
    }

	public String getYahoo() {
        return yahoo;
    }

	public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

	public String getGmail() {
        return gmail;
    }

	public void setGmail(String gmail) {
        this.gmail = gmail;
    }

	public String getPhone1() {
        return phone1;
    }

	public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

	public String getPhone2() {
        return phone2;
    }

	public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

	public String getInstitution() {
        return institution;
    }

	public void setInstitution(String institution) {
        this.institution = institution;
    }

	public String getDepartment() {
        return department;
    }

	public void setDepartment(String department) {
        this.department = department;
    }

	public String getAddress() {
        return address;
    }

	public void setAddress(String address) {
        this.address = address;
    }

	public String getCity() {
        return city;
    }

	public void setCity(String city) {
        this.city = city;
    }

	public String getCountry() {
        return country;
    }

	public void setCountry(String country) {
        this.country = country;
    }

	public String getLang() {
        return lang;
    }

	public void setLang(String lang) {
        this.lang = lang;
    }

	public String getCalendartype() {
        return calendartype;
    }

	public void setCalendartype(String calendartype) {
        this.calendartype = calendartype;
    }

	public String getTheme() {
        return theme;
    }

	public void setTheme(String theme) {
        this.theme = theme;
    }

	public String getTimezone() {
        return timezone;
    }

	public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

	public Long getCurrentlogin() {
        return currentlogin;
    }

	public void setCurrentlogin(Long currentlogin) {
        this.currentlogin = currentlogin;
    }

	public boolean isAutosubscribe() {
        return autosubscribe;
    }

	public void setAutosubscribe(boolean autosubscribe) {
        this.autosubscribe = autosubscribe;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long userid;

	public Long getUserid() {
        return this.userid;
    }

	public void setUserid(Long id) {
        this.userid = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new User().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM User o", Long.class).getSingleResult();
    }

	public static List<User> findAllUsers() {
        return entityManager().createQuery("SELECT o FROM User o", User.class).getResultList();
    }

	public static List<User> findAllUsers(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM User o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, User.class).getResultList();
    }

	public static User findUser(Long userid) {
        if (userid == null) return null;
        return entityManager().find(User.class, userid);
    }

	public static List<User> findUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM User o", User.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<User> findUserEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM User o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, User.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            User attached = User.findUser(this.userid);
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
    public User merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        User merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("courseCompletionss", "courseModulesCompletions", "userpermissions", "companyid", "roletype").toString();
    }
}
