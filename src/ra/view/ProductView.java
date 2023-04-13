package ra.view;

import ra.config.Config;
import ra.controller.catalog.CataControllerIMPL;
import ra.controller.product.ProductControllerIMPL;
import ra.model.Catalog;
import ra.model.Product;

import java.util.List;

import static ra.config.Config.scanner;

public class ProductView {
    ProductControllerIMPL productcontroller = new ProductControllerIMPL();
    List<Product> productList = productcontroller.findAll();

    public void showProduct(){
        System.out.println("                                                                        \u001B[33mList Sản Phẩm HS Shop");
        System.out.format("                                                 +---------+-----------------+-----------------+-----------------+--------+%n");
        System.out.format("                                                 | User ID |  productCatalog |    productName  |   Product Price | Status |%n");
        System.out.format("                                                 +---------+-----------------+-----------------+-----------------+--------+%n");
        String leftAlignFormat = "                                                 | %-7d | %-15s | %-15s | %-15s | %-6s |%n";
        for (Product listproduct:productList
             ) {
            System.out.format(leftAlignFormat,listproduct.getProductId(),listproduct.getProductCatalog().getName(),listproduct.getProductName(),listproduct.getProductPrice(),listproduct.getProductStatus());
//            System.out.println(listproduct);
        }
    }

    public void createProduct(){
        Product product = new Product();
        if (productList.size()==0){
            product.setProductId(1);
        }else {
            product.setProductId(productList.get(productList.size()-1).getProductId()+1);
        }
        Catalog catalog = null;
        List<Catalog> catalogList = new CataControllerIMPL().findAll();
        System.out.println("Thông tin danh mục sản phẩm");
        for (Catalog c:catalogList) {
            System.out.println(c);
        }
        System.out.println("Tìm kiếm danh mục sản phẩm theo Id :");
        int idC = Integer.parseInt(Config.scanner().nextLine());
        for (Catalog c:catalogList) {
            if (c.getId()==idC){
                catalog = c;
            }
        }
        product.setProductCatalog(catalog);
        System.out.println("Nhập tên sản phẩm cần thêm mới:");
        product.setProductName(Config.scanner().nextLine());
        System.out.println("Nhập giá sản phẩm:");
        product.setProductPrice(Float.parseFloat(Config.scanner().nextLine()));
        System.out.println("Nhập trạng thái sản phẩm");
        product.setProductStatus(Boolean.parseBoolean(Config.scanner().nextLine()));
        productcontroller.save(product);
    }

    public void deleteProduct(){
        System.out.println("Nhập Id sản phẩm cần xóa: ");
        int id = Integer.parseInt(Config.scanner().nextLine());
        productcontroller.delete(id);
    }

    public void searchByName(){
        System.out.println("Nhập tên sản phẩm bạn muốn tìm kiếm:");
        String searchName= Config.scanner().nextLine();
        for (Product p:productcontroller.searchByName(searchName)) {
            System.out.println(p);
        }
    }

    public void updateProduct(){
        System.out.println("Nhập Id của sản phẩm bạn muốn update:");
        int idUpdate = Integer.parseInt(Config.scanner().nextLine());
        if (productcontroller.findById(idUpdate)==null){
            System.err.println(" Id không tồn tại !");
        }else {

            System.out.println("Nhập tên sản phẩm bạn muốn thay đổi:");
            String nameUpdate = Config.scanner().nextLine();
            Catalog catalogUpdate = null;
            List<Catalog> catalogList = new CataControllerIMPL().findAll();
            for (Catalog c:catalogList) {
                System.out.println(c);
            }
            System.out.println("Nhập id danh mục sản phẩm :");
            int idC = Integer.parseInt(Config.scanner().nextLine());
            for (Catalog c:catalogList) {
                if (c.getId()==idC){
                    catalogUpdate = c;
                }
            }
            System.out.println("Nhập giá sản phẩm cần thay đổi");
            Float priceUpdate = Float.parseFloat(Config.scanner().nextLine());
            System.out.println("Nhập trạng thái cần thay đổi:");
            Boolean statusUpdate = Boolean.parseBoolean(Config.scanner().nextLine());
            Product productUpdate = new Product(idUpdate,catalogUpdate,nameUpdate,priceUpdate,statusUpdate);
            productcontroller.save(productUpdate);
        }
    }

    public static void ProductManagement(){
        boolean check = true;
        while (check) {
//            System.out.println("*************PRODUCT MANAGEMENT*****************");
//            System.out.println("1. Show Danh sách sản phẩm");
//            System.out.println("2. Tạo sản phẩm mới");
//            System.out.println("3. Update sản phẩm");
//            System.out.println("4. Delete sản phẩm");
//            System.out.println("5. Tìm kiếm sản phẩm theo tên:");
//            System.out.println("6. Thoát");

            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                                                    Quản Lý Sản Phẩm                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                1. Show Danh sách sản phẩm                                                         ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                2. Tạo sản phẩm mới                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                3. Update sản phẩm                                                  ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                4. Delete sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                5. Tìm kiếm sản phẩm theo tên:                                                           ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                6. Thoát                                                                   ");
            System.out.println("\u001B[35m                                         ***************************************************************************************\u001B[0m");
            System.out.println("                                                 Lựa chọn cửa bạn là:                                                           ");

            int c = Integer.parseInt(Config.scanner().nextLine());
            switch (c){
                case 1:
                    new ProductView().showProduct();
                    break;
                case 2:
                    new ProductView().createProduct();
                    break;
                case 3:
                    new ProductView().updateProduct();
                    break;
                case 4:
                    new ProductView().deleteProduct();
                    break;
                case 5:
                    new ProductView().searchByName();
                    break;
                case 6:
                    check = false;
                    break;

            }
        }
    }


}
