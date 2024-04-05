package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.entity.Role;
import workshopJudge_v2.model.entity.User;
import workshopJudge_v2.model.entity.enumeration.RoleType;
import workshopJudge_v2.model.serviceModel.UserServiceModel;
import workshopJudge_v2.repository.RoleRepository;
import workshopJudge_v2.repository.UserRepository;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.RoleService;
import workshopJudge_v2.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper,
                           CurrentUser currentUser, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRole(this.roleService.getRoleNameUser());

        this.userRepository.save(user);

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        this.currentUser.setId(userServiceModel.getId());
        this.currentUser.setUsername(userServiceModel.getUsername());
        this.currentUser.setRoleType(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
        this.currentUser.setRoleType(null);
    }

    @Override
    public List<String> getAllUsernames() {

        return this.userRepository.findAllUsernames();
    }

    @Override
    public void changeUserRole(String username, String role) {
        User user = this.userRepository.findByUsername(username).get();
        Role newRole = this.roleRepository.findByName(RoleType.valueOf(role.toUpperCase())).get();

        if(user.getRole() != newRole) {
            user.setRole(newRole);
            this.userRepository.save(user);
        }
    }


}
