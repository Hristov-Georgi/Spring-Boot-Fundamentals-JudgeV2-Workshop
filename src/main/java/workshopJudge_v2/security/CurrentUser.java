package workshopJudge_v2.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import workshopJudge_v2.model.entity.enumeration.RoleType;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private RoleType roleType;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public boolean isAnonymous() {
        return this.username == null;
    }

    public boolean isAdmin() {
        return this.roleType == RoleType.ADMIN;
    }
}
