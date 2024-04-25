package workshopJudge_v2.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.binding.RoleAddBindingModel;
import workshopJudge_v2.model.binding.UserRegisterBindingModel;
import workshopJudge_v2.model.entity.Role;
import workshopJudge_v2.model.entity.UserEntity;
import workshopJudge_v2.model.entity.enumeration.RoleType;
import workshopJudge_v2.model.serviceModel.UserServiceModel;
import workshopJudge_v2.model.view.UserProfileViewModel;
import workshopJudge_v2.repository.RoleRepository;
import workshopJudge_v2.repository.UserEntityRepository;
import workshopJudge_v2.service.RoleService;
import workshopJudge_v2.service.UserEntityService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userRepository, RoleService roleService, ModelMapper modelMapper,
                                 RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean comparePasswords(UserRegisterBindingModel userRegisterBindingModel) {
        return userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        userRegisterBindingModel.setPassword(this.passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        UserEntity userEntity = this.modelMapper.map(userRegisterBindingModel, UserEntity.class);
        userEntity.setRole(this.roleService.getRoleNameUser());

        this.userRepository.save(userEntity);

    }

    @Override
    public List<String> getAllUsernames() {

        return this.userRepository.findAllUsernames();
    }

    @Override
    public void changeUserRole(RoleAddBindingModel roleAddBindingModel) {
        String username = roleAddBindingModel.getUsername();
        String roleName = roleAddBindingModel.getRole();

        UserEntity user = this.userRepository.findByUsername(username).get();
        Role newRole = this.roleRepository.findByName(RoleType.valueOf(roleName.toUpperCase())).get();

        if(user.getRole() != newRole) {
            user.setRole(newRole);
            this.userRepository.save(user);
        }
    }

    @Override
    public UserProfileViewModel findProfileData(String username) {

        UserEntity userEntity = this.userRepository.findByUsername(username).get();

        UserProfileViewModel userProfile = this.modelMapper.map(
                userEntity, UserProfileViewModel.class);

        userProfile.setHomeworks(userEntity.getHomeworks().stream().map(h ->
                h.getExercise().getName()).collect(Collectors.toList()));

        return userProfile;
    }

    @Override
    public UserEntity findByUsername(String username) {

        return this.userRepository.findByUsername(username).get();

    }

    @Override
    public Long findUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> findTopStudents() {

        return this.userRepository.findTopStudents();

    }
}
