package ra.view;


import ra.config.Config;
import ra.controller.UserController;
import ra.model.User;
import ra.service.role.RoleServiceIMPL;

import java.util.HashSet;


public class Navbar {

    static UserController userController = new UserController();

//    public Navbar() {
//        User user = userController.getUserLogin();
//        if (user != null) {
//            System.out.println("Chào mừng bạn  " + user.getName() + " đến với HaiSonShop");
//            System.out.println("Nhập lựa chọn của bạn:");
//            System.out.println("1. Vào trang chính");
//            System.out.println("2. Đăng xuất tài khoản");
//
//            int chooseMenu = Config.scanner().nextInt();
//            switch (chooseMenu) {
//                case 1:
//            new ProfileView();
//                    break;
//                case 2:
//                    new UserView().logOut();
//                    break;
//        }
//    } else
//
//    {
//        System.out.println("1. Đăng ký");
//        System.out.println("2. Đăng nhập");
//        int chooseMenu = Config.scanner().nextInt();
//        switch (chooseMenu) {
//            case 1:
//                new ra.view.UserView().formRegister();
//                break;
//            case 2:
//                new ra.view.UserView().formLogin();
//                break;
//        }
//    }
//}

public static void userHomeMenu(){
            User user = userController.getUserLogin();
        if (user != null) {
//

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.err.println("                                                          Chào mừng bạn  \u001B[33m" + user.getName() + "\u001B[0m  đến với HS Shop             ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                              1. Vào trang chính                     |     2. Đăng xuất tài khoản                ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                           Lựa chọn của bạn:                                    ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");


            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
            new ProfileView();
                    break;
                case 2:
                    new UserView().logOut();
                    break;
        }

    } else

    {
        System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
        System.err.println("                                                              Welcome to \u001B[33mHS shop\u001B[0m thế giới thời trang phụ kiện");
        System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
        System.out.println("                                              1. Đăng ký tài khoản mới                       |       2. Đăng Nhập                 ");
        System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
        System.out.println("                                              Lựa chọn của bạn:                                                                  ");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                new ra.view.UserView().formRegister();
                break;
            case 2:
                new ra.view.UserView().formLogin();
                break;
        }
    }
    userHomeMenu();
}



    public static void main(String[] args) {
        userHomeMenu();
    }


    public static void generalManager() {
        boolean check = true;
        while (check) {
//            System.out.println("**************QUẢN LÝ HAISONSHOP*************** \n");
//            System.out.println("1. Quản lý sản phẩm");
//            System.out.println("2. Quản lý danh mục");
//            System.out.println("3. Quản lý người dùng");
//            System.out.println("4. Quản lý bán hàng");
//            System.out.println("5. Đăng xuất");

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("\u001B[33m                                                                                    " + "QUẢN LÝ HS SHOP" + "                                       |\u001B[0m");
            System.out.println("\u001B[35m                                         ***************************************************************************************|\u001B[0m");
            System.out.println("                                                1. Quản lý sản phẩm                                                                       ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                2. Quản lý danh mục                                                                       ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                3. Quản lý người dùng                                                                     ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                4. Quản lý bán hàng                                                                       ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                5. Đăng xuất                                                                              ");
           System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                 Lựa chọn cửa bạn là:                                                                     ");

            int choice = Integer.parseInt(Config.scanner().nextLine());
            switch (choice) {
                case 1:
                    ProductView.ProductManagement();
                    break;
                case 2:
                    CatalogView.catalogManager();
                    break;
                case 3:
                    UserView.UserManager();
                    break;
                case 4:
                    OrderView.showOderMenu();
                    break;
                case 5:
                    new UserView().logOut();
                    check = false;
                    break;
                default:
                    break;
            }
            userHomeMenu();
        }
    }


    public static void cartView() {
        User user = userController.getUserLogin();
        while (true) {
            System.out.println("\u001B[33m                                                                 DANH SÁCH TẤT CẢ CÁC SẢN PHẨM HS SHOP: ");
            new ProductView().showProduct();

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                                    HS SHOP                                     ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                1.  Giỏ hàng của bạn                                                          ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                2. Thêm sản phẩm vào giỏ hàng                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                3. Thay đổi số lượng sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                4. Xóa sản phẩm khỏi giỏ hàng                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                5. Thanh toán giỏ hàng                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                6. Thay đổi password                                                            ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                7. Đăng xuất                                                                    ");
            System.out.println("                                                                                                                ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                 Lựa chọn cửa bạn là:                                                           ");

            int choice = Config.scanner().nextInt();
            switch (choice) {
                case 1:
                    new UserView().showListCart(user);
                    break;
                case 2:
                    new UserView().addToCart(user);
                    break;
                case 3:
                    new UserView().changeQuantityItem(user);
                    break;
                case 4:
                    new UserView().deleteCartItem(user);
                    break;
                case 5:
                    new UserView().payingCart();
                    break;
                case 6:
                    new UserView().formUpdateUser();
                    break;
                case 7:
                    new UserView().logOut();
                    userHomeMenu();
                    break;


                default:
                    System.err.println("Nhập lựa chọn từ 1 đến 7");
            }


            if (choice == 6) {
                break;
            }
        }
    }


}