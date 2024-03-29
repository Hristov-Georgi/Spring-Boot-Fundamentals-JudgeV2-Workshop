package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.binding.UserLoginBindingModel;
import workshopJudge_v2.model.entity.User;
import workshopJudge_v2.model.serviceModel.UserServiceModel;
import workshopJudge_v2.repository.UserRepository;
import workshopJudge_v2.service.RoleService;
import workshopJudge_v2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRole(this.roleService.getRoleNameUser());

        this.userRepository.save(user);

    }

    @Override
    public UserLoginBindingModel findByUsernameAndPassword(String username, String password) {

        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(u -> this.modelMapper.map(u, UserLoginBindingModel.class))
                .orElse(null);
    }
}
