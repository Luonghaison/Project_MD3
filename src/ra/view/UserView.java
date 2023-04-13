package ra.view;

import ra.config.Config;
import ra.controller.UserController;
import ra.controller.product.ProductControllerIMPL;
import ra.dto.request.SignInDTO;
import ra.dto.request.SignUpDTO;
import ra.dto.response.ResponseMessage;
import ra.model.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class UserView {
    UserController userController = new UserController();
    User userLogin = userController.getUserLogin();
    ProductControllerIMPL productcontroller = new ProductControllerIMPL();

    List<User> userList = userController.getListUser();

    public void formRegister() {
        System.out.println("size -->" + userList.size());
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        System.out.println("id =---" + id);
        boolean validateName;
        String name;
        while (true) {
//            System.out.println("Nhập tên bạn: ");
//            name = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                         Nhập tên của bạn                                 ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.print("                                                                           ");  name = Config.scanner().nextLine();

            validateName = Pattern.matches("[A-Z][a-zA-Z[\\\\s]]{1,10}", name);
            if (validateName) {
                break;
            } else {
                System.err.println("Tên không đúng định dạng");
            }
        }

        boolean validateUsername;
        String username;
        while (true) {
//            System.out.println("Nhập username của bạn: ");
//            username = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                         Nhập username của bạn:                                ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.print("                                                                           ");  username = Config.scanner().nextLine();

            validateUsername = Pattern.matches("^[a-zA-Z0-9]{1,40}$", username);
            if (validateUsername) {
                break;
            } else {
                System.err.println("Username không đúng định dạng");
            }
        }

        boolean validateEmail;
        String email;
        while (true) {
//            System.out.println("Nhập email của bạn: ");
//            email = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                         Nhập email của bạn:                                ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.print("                                                                           ");  email = Config.scanner().nextLine();

            validateEmail = Pattern.matches("^(.+)@(.+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.err.println("Email không đúng định dạng");
            }
        }
        boolean validatePassword;
        String password;
        while (true) {
//            System.out.println("Nhập password của bạn: ");
//            password = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                         Nhập mật khẩu của bạn:                                ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.print("                                                                           ");  password = Config.scanner().nextLine();

            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (validatePassword) {
                break;
            } else {
                System.err.println("Password không đúng định dạng");
            }
        }
//        System.out.println("Enter the role: ");
//        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add("user");

        SignUpDTO sign = new SignUpDTO(id, name, username, email, password, strRole);
        while (true) {
            ResponseMessage responseMessage = userController.register(sign);
            if (responseMessage.getMessage().equals("user_existed")) {
                System.err.println("user name existed!");
                username = Config.scanner().nextLine();
                sign.setUsername(username);
            } else if (responseMessage.getMessage().equals("email_existed")) {
                System.err.println("email name existed!");
                email = Config.scanner().nextLine();
                sign = new SignUpDTO(id, name, username, email, password, strRole);
            } else if (responseMessage.getMessage().equals("create_success")) {
                formLogin();
                break;
            }
        }
    }

    public void formLogin() {

        boolean validateUsername;
        String username;
        while (true) {
            System.out.println("Form Login!");
//            System.out.println("Nhập username: ");
//            username = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                         NHẬP TÊN ĐĂNG NHẬP VÀO                                 ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.print("                                                                           ");  username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("^[a-zA-Z0-9]{1,40}$", username);
            if (validateUsername) {
                break;
            } else {
                System.err.println("Username không đúng định dạng");
            }
        }

        boolean validatePassword;
        String password;
        while (true) {
//            System.out.println("Nhập mật khẩu của bạn: ");
//            password = Config.scanner().nextLine();
            System.out.println("                                                                                NHẬP MẬT KHẨU ");
            System.out.println("\u001B[35m                                         ***************************************************************************************|\u001B[0m");
            System.out.print("                                                                            "); password = Config.scanner().nextLine();

            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (validatePassword) {
                break;
            } else {
                System.err.println("Password không đúng định dạng");
            }
        }

        SignInDTO signInDTO = new SignInDTO(username, password);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if (responseMessage.getMessage().equals("login_failed")) {
                System.err.println("Login failed! Please check your account!");
//                System.out.println("Enter your username: ");
//                username = Config.scanner().nextLine();
//                System.out.println("Enter your password: ");
//                password = Config.scanner().nextLine();

                System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
                System.out.println("                                                                         NHẬP TÊN ĐĂNG NHẬP VÀO                                 ");
                System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
                System.out.print("                                                                           ");  username = Config.scanner().nextLine();
                System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
                System.out.println("                                                                                NHẬP MẬT KHẨU ");
                System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
                System.out.print("                                                                            "); password = Config.scanner().nextLine();

                signInDTO.setUsername(username);
                signInDTO.setPassword(password);
            } else {
//                System.out.println("LOGIN SUCCESS!");
                System.out.println("\u001B[33mLOGIN SUCCESS!\u001B[0m");
                Navbar.userHomeMenu();
                break;
            }
        }
    }

    public void showListUser() {
        System.out.println("                                                         \u001B[33mList Danh sách người dùng");
        System.out.format("+---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n");
        System.out.format("| User ID |       Name      |    Username     |        Email           |  Password    |    Avatar    |      Status       |        Role         |%n");
        System.out.format("+---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n");
        String leftAlignFormat = "| %-7d | %-15s | %-15s | %-22s | %-12s | %-12s | %-17s | %-19s |%n";

        for (User list:userController.getListUser()
             ) {
            System.out.format(leftAlignFormat, list.getId(),list.getName(),list.getUsername(),list.getEmail(),list.getPassword(),list.getAvatar(),list.isStatus(),list.getRoles());
        }
//        System.out.println(userController.getListUser());

    }

    public void logOut() {
        userController.logOut();
        new Navbar();
    }

    public void changeUserStatus() {
        System.out.println("Nhập id");
        int id = Config.scanner().nextInt();
        User user = userController.findById(id);
        if (user == null) {
            System.out.println("Id không đúng");
        } else {
            userController.changeUserStatus(id);
        }
    }

    public boolean checkCartItemExits(List<CartItem> cart,int idCartItem) {
        for (CartItem item : cart) {
            if (item.getId()==idCartItem){
                return true;
            }
        }
        return false;
    }

    public void showListCart(User user){
        System.out.println("                                                                        \u001B[33mGiỏ Hàng Của Bạn");
        System.out.format("                                                 +---------+-----------------+-----------------+-----------------+--------+%n");
        System.out.format("                                                 | User ID |  Tên danh mục   |   Tên Sản Phẩm  |   Giá tiền      |S.lượng |%n");
        System.out.format("                                                 +---------+-----------------+-----------------+-----------------+--------+%n");
        String leftAlignFormat = "                                                 | %-7d | %-15s | %-15s | %-15s | %-6s |%n";
        float total = 0;

            for (CartItem cartItem:user.getCart()){
                System.out.format(leftAlignFormat,cartItem.getId(),cartItem.getProduct().getProductCatalog().getName(),cartItem.getProduct().getProductName(),cartItem.getProduct().getProductPrice(),cartItem.getQuantity());
                total += cartItem.getProduct().getProductPrice()*cartItem.getQuantity();
//            System.out.println(cartItem);
        }
        System.out.println("                                                                       \u001B[35mTổng giá trị đơn hàng của bạn là: \u001B[31m" + total + "\u001B[0m");
        System.out.println();
    }

    public void addToCart(User user){
        List<CartItem> cart = user.getCart();
        System.out.println("Nhập Id sản phẩm");
        int id = Config.scanner().nextInt();
        if(productcontroller.findById(id) == null){
            System.err.println("Id không đúng");
        }else {
            Product product =productcontroller.findById(id);
            System.out.println("Nhập số lượng sản phẩm");
            int quantity = Config.scanner().nextInt();
            int idNew = (cart.size()==0)?1:(cart.get(cart.size()-1).getId()+1);
            CartItem newCartItem = new CartItem(idNew,product,quantity);
            if (cart.size()==0){
                // giò hàng trống
                cart.add(newCartItem);
            }else {
                if (checkCartItemExits(cart,id)){
//                    sản phẩm trùng
                    for (CartItem item: cart) {
                        if (item.getProduct().getProductId()==id){
                            item.setQuantity(item.getQuantity()+quantity);
                            break;
                        }
                    }
                }else {
//                    sản phâm ko bị trùng
                    cart.add(newCartItem);
                }

            }
            System.out.println("add to cart success");
            user.setCart(cart);
            userController.update(user);
        }
    }
    public void changeQuantityItem(User user){
        List<CartItem> cart = user.getCart();
        System.out.println("Nhập id của giỏ hàng");
        int idCartItem = Config.scanner().nextInt();
        if (checkCartItemExits(cart,idCartItem)){
            for (CartItem item : cart) {
                if (item.getId()==idCartItem){
                    System.out.println("Nhập số lượng mới , số lượng cũ là "+item.getQuantity());
                    item.setQuantity(Config.scanner().nextInt());
                    user.setCart(cart);
                    userController.update(user);
                    System.out.println("Update thành công");
                    break;
                }
            }
        }else {
            System.err.println("id giỏ hàng không tồn tại");
        }
    }

    public void deleteCartItem(User user){
        List<CartItem> cart = user.getCart();
        System.out.println("Nhập id cart cần xóa");
        int idCartItem = Config.scanner().nextInt();
        if (checkCartItemExits(cart,idCartItem)) {
            for (CartItem item : cart) {
                if (item.getId()==idCartItem){
                    cart.remove(item);
                    user.setCart(cart);
                    userController.update(user);
                    break;
                }
            }
        }else {
            System.err.println("id không tồn tại");
        }
    }

    public void searchByUsername(){
        System.out.println("Nhập tên username bạn muốn tìm kiếm:");
        String searchName= Config.scanner().nextLine();
        for (User p:userController.searchByName(searchName)) {
            System.out.println(p);
        }
    }

    public void
    formUpdateUser(){
        while (true) {
            System.out.print("Enter old password: ");
            String oldPassword = Config.scanner().nextLine();
            //Kiểm tra mật khẩu cũ
            if (!userLogin.getPassword().equals(oldPassword)) {
                System.err.print("Password does not match please re-enter!!!!");
                continue;
            }
            System.out.print("Enter new password: ");
            String newPassWord = Config.scanner().nextLine();
            for (User u : userList) {
                if (u.getId() == userLogin.getId()) {
                    u.setPassword(newPassWord);
                    userController.update(u);
                    break;
                }
            }
            System.out.println("Update password success!!!");
            break;
        }
        }

    public void payingCart(){
        userController.paying(userLogin);
    }




    public static void UserManager() {
        boolean check = true;
        while (check) {
//            System.out.println("**************QUẢN LÝ NGƯỜI DÙNG*************** \n");
//            System.out.println("1. Hiển thị danh sách người dùng");
//            System.out.println("2. Thay đổi trạng thái người dùng");
//            System.out.println("3. Tìm kiếm tài khoản theo tên đăng nhập ");
//            System.out.println("4. Thoát");
//            System.out.println("***********************************************");
//            System.out.println("Nhập lựa chọn của bạn: ");

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("\u001B[33m                                                                                " + "Quản Lý Người Dùng" + "                                  \u001B[0m");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                1. Hiển thị danh sách người dùng                                                        ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                2. Thay đổi trạng thái người dùng                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                3. Tìm kiếm tài khoản theo tên đăng nhập                                                  ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                4. Thoát                                                  ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
           System.out.println("                                                 Lựa chọn cửa bạn là:                                                           ");

            int choice = Integer.parseInt(Config.scanner().nextLine());
            switch (choice) {
                case 1:
                    new ra.view.UserView().showListUser();
                    break;
                case 2:
                    new ra.view.UserView().changeUserStatus();
                    break;
                case 3:
                    new ra.view.UserView().searchByUsername();
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }
}
