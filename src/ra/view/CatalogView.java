package ra.view;

import ra.config.Config;
import ra.controller.catalog.CataControllerIMPL;
import ra.model.Catalog;

import java.util.List;
import java.util.Scanner;

import static ra.config.Config.scanner;

public class CatalogView {
    public static CataControllerIMPL catacontroller = new CataControllerIMPL();
    public static List<Catalog> catalogList = catacontroller.findAll();

    public void showCatalog() {
        System.out.println("                                                                    \u001B[33mList Danh Mục Sản Phẩm");
        System.out.format("                                                            +---------+-----------------+-----------------+%n");
        System.out.format("                                                            | User ID |       Name      |  Trạng thái     |%n");
        System.out.format("                                                            +---------+-----------------+-----------------+%n");
        for (Catalog cata : catalogList
        ) {
            String leftAlignFormat = "                                                            | %-7d | %-15s | %-15s |%n";


            System.out.format(leftAlignFormat,cata.getId(),cata.getName(),cata.isStatus());

//            System.out.println(cata);
        }
    }

    public void createCatalog() {
        Catalog newCatalog = new Catalog();
        if (catalogList.size()==0){
            newCatalog.setId(1);
        }else {
            newCatalog.setId(catalogList.get(catalogList.size()-1).getId()+1);
        }
        System.out.println("Nhập vào tên danh mục mới bạn muốn thêm: ");
        newCatalog.setName(Config.scanner().nextLine());
        System.out.println("Nhập vào trạng thái bạn muốn thêm: ");
        newCatalog.setStatus(Boolean.parseBoolean(Config.scanner().nextLine()));
        catacontroller.save(newCatalog);
    }

    public void updateCatalog() {
        System.out.println("Nhập Id bạn muốn update: ");
        int id = Integer.parseInt(Config.scanner().nextLine());
        if (catacontroller.findById(id) == null) {
            System.out.println("Id không thể tìm thấy");
        } else {
            System.out.println("Nhập tên danh mục sản phẩm bạn muốn sua");
            String nameUp = Config.scanner().nextLine();
            System.out.println("Nhập tên trạng thái danh mục muốn sửa: ");
            boolean status = Boolean.parseBoolean(Config.scanner().nextLine());
            Catalog newCatalog = new Catalog(id, nameUp, status);
            catacontroller.save(newCatalog);
        }
    }

    public void deleteCatalog(){
        System.out.println("Nhập Id bạn muốn delete: ");
        int id = Integer.parseInt(Config.scanner().nextLine());
        catacontroller.deleteById(id);
    }

    public void searchByName(){
        System.out.println("Nhap ten danh muc ban muon tim kiem:");
        String searchName= Config.scanner().nextLine();

        for (Catalog c:catacontroller.searchByName(searchName)) {
            System.out.println(c);
        }
    }

    public static void catalogManager(){
        boolean check = true;
        while (check){
//            System.out.println("***************CATALOG MANAGEMENT**************");
//            System.out.println("1. Hiển thị daqnh mục sản phẩm");
//            System.out.println("2. Tạo danh mục sản phẩm mới");
//            System.out.println("3. Update danh mục sản phẩm");
//            System.out.println("4. Xóa danh mục sản phẩm");
//            System.out.println("5. Tìm kiếm danh mục sản phẩm");
//            System.out.println("6. Thoát");

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("\u001B[33m                                                                                    " + "Quản Lý Danh mục Sản phẩm" + "                                    |\u001B[0m");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                1. Hiển thị daqnh mục sản phẩm                                                          ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                2. Tạo danh mục sản phẩm mới                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                3. Update danh mục sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                4. Xóa danh mục sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                5. Tìm kiếm danh mục sản phẩm                                                            ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                6. Thoát                                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                 Lựa chọn cửa bạn là:                                                           ");

            int choice = Integer.parseInt(Config.scanner().nextLine());

            switch (choice) {
                case 1:
                    new CatalogView().showCatalog();
                    break;
                case 2:
                    new CatalogView().createCatalog();
                    break;
                case 3:
                    new CatalogView().updateCatalog();
                    break;
                case 4:
                    new CatalogView().deleteCatalog();
                    break;
                case 5:
                    new CatalogView().searchByName();
                case 6:
                    check = false;

            }
        }
    }




}
