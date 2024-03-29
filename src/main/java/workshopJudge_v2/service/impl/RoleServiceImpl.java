package workshopJudge_v2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.entity.Role;
import workshopJudge_v2.model.entity.enumeration.RoleType;
import workshopJudge_v2.service.RoleService;
import workshopJudge_v2.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRoles() {

        if(this.roleRepository.count() == 0) {
            Role userRole = new Role(RoleType.USER);
            Role adminRole = new Role(RoleType.ADMIN);

            this.roleRepository.save(userRole);
            this.roleRepository.save(adminRole);
        }
    }

    @Override
    public Role getRoleNameUser() {

        return this.roleRepository.findByName(RoleType.USER).get();

    }
}
