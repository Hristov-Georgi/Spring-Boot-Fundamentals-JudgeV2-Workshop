package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.entity.User;
import workshopJudge_v2.model.serviceModel.UserServiceModel;
import workshopJudge_v2.repository.UserRepository;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.RoleService;
import workshopJudge_v2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
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
}
