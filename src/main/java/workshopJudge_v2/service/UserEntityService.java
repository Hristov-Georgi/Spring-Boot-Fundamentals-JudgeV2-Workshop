package workshopJudge_v2.service;

import workshopJudge_v2.model.binding.RoleAddBindingModel;
import workshopJudge_v2.model.binding.UserRegisterBindingModel;
import workshopJudge_v2.model.entity.UserEntity;
import workshopJudge_v2.model.view.UserProfileViewModel;

import java.util.List;

public interface UserEntityService {

    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    List<String> getAllUsernames();

    void changeUserRole(RoleAddBindingModel roleAddBindingModel);

    boolean comparePasswords(UserRegisterBindingModel userRegisterBindingModel);

    UserProfileViewModel findProfileData(String username);

    UserEntity findByUsername(String username);

    Long findUsersCount();

    List<String> findTopStudents();
}
