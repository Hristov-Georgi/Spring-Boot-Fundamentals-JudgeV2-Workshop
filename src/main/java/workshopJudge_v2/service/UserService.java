package workshopJudge_v2.service;

import workshopJudge_v2.model.binding.UserLoginBindingModel;
import workshopJudge_v2.model.binding.UserRegisterBindingModel;
import workshopJudge_v2.model.serviceModel.UserServiceModel;

import java.util.List;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> getAllUsernames();

    void changeUserRole(String username, String role);
}
