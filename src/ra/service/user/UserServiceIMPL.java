package ra.service.user;

import ra.config.Config;
import ra.model.Order;
import ra.model.Product;
import ra.model.User;
import ra.service.order.IOrderService;
import ra.service.order.OrderserviceIMPL;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);

    private IOrderService orderservice = new OrderserviceIMPL();
    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        new Config<User>().writeToFile(Config.PATH_USER, userList);
    }

    @Override
    public User findById(int id) {
        for (User user:userList) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }



    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<User> searchByName(String searchName) {
        List<User> userListSearch= new ArrayList<>();
        for (User p:userList) {
            if (searchName.toLowerCase().contains(p.getUsername().toLowerCase())){
                userListSearch.add(p);
            }
        }
        return userListSearch;
    }

    @Override
    public boolean existedByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<User> userLogin = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(username)&&userList.get(i).getPassword().equals(password)){
                userLogin.add(userList.get(i));
                new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if(new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size()!=0){
            User user = new Config<User>().readFromFile(Config.PATH_USER_LOGIN).get(0);
            return user;
        }
        return null;
    }

    @Override
    public void logOut() {
        List<User> users = new Config<User>().readFromFile(Config.PATH_USER_LOGIN);
        users.remove(0);
        new Config<User>().writeToFile(Config.PATH_USER_LOGIN, users);
    }

    @Override
    public boolean changeUserStatus(int id) {
        if (findById(id) == null){
            return false;
        }else {
            for (User user : userList) {
                if (user.getId()==id){
                    user.setStatus(!user.isStatus());
                    new Config<User>().writeToFile(Config.PATH_USER,userList);
                    break;
                }
            }
            return true;
        }
    }

    @Override
    public void paying(User userLogin) {
        orderservice.save(new Order(userLogin, userLogin.getCart(), "chưa thanh toán"));
        userLogin.setCart(new ArrayList<>());
        save(userLogin);
    }

//    public static void main(String[] args) {
//        List<User> l = new UserServiceIMPL().userList;
//        for (int i = 0; i < l.size(); i++) {
//            System.out.println(l.get(i));
//        }
//    }

}
