package GUI.Server.Order;

import DTO.InvoiceDetailInputDTO;
import DTO.Product;
import GUI.Components.ProductCard;
import Utils.Helper;
import Utils.ServiceProvider;
import org.jdesktop.swingx.WrapLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FoodOrder extends javax.swing.JFrame {
    private final List<ProductCard> productCards = new java.util.ArrayList<>();
    public static List<Product> products;
    public void renderProductCards() {
        jPanelProduct.removeAll();
        productCards.forEach(p -> {
           if(p.isVisible()){
               jPanelProduct.add(p);
           }
        });
        jPanelProduct.revalidate();
        jPanelProduct.repaint();
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }
    public FoodOrder() {
        initComponents();

        var wrapLayout = new WrapLayout();
        wrapLayout.setAlignment(java.awt.FlowLayout.LEFT);
        wrapLayout.setHgap(10);
        wrapLayout.setVgap(20);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        products.forEach(p -> {
            var productCard = new ProductCard(p.getImage(), p.getName(), (float) p.getPrice());
            productCard.setProduct(p);
            productCards.add(productCard);
            productCard.setBounds(0, 0, 200, 200);
            jPanelProduct.add(productCard);
        });
        jPanelProduct.setLayout(wrapLayout);
        jPanelProduct.revalidate();
        jPanelProduct.repaint();
        var model = new DefaultComboBoxModel<TypeProductCbItem>();
        model.addElement(new TypeProductCbItem(-1, "Tất cả"));
        model.addElement(new TypeProductCbItem(Product.ProductType.FOOD.ordinal(), "Đồ ăn"));
        model.addElement(new TypeProductCbItem(Product.ProductType.DRINK.ordinal(), "Đồ uống"));
        model.addElement(new TypeProductCbItem(Product.ProductType.CARD.ordinal(), "Thẻ "));
        jComboBoxLoaiSp.setModel(model);
        //default
        jComboBoxLoaiSp.setSelectedIndex(0);
        var model2 = new  DefaultComboBoxModel<String>();
        model2.addElement("Giá tăng dần");
        model2.addElement("Giá giảm dần");
        model2.addElement("Tên A-Z");
        model2.addElement("Tên Z-A");
        jComboBoxOrderBy.setModel(model2);
        //default
        jComboBoxOrderBy.setSelectedIndex(2);
        jPanelProduct.revalidate();
        jPanelProduct.repaint();
        initEvent();

    }

    private record TypeProductCbItem(Integer value, String text) {
        @Override
        public String toString() {
            return text;
        }
    }

    public void initEvent() {
        jButton1.addActionListener(e->{
            if(jTextFieldName.getText().trim().length()>0){
                productCards.forEach(p -> p.setVisible(p.getProduct().getName().toLowerCase().contains(jTextFieldName.getText().trim().toLowerCase())));
            }
            else {
                productCards.forEach(p -> p.setVisible(true));
            }
            switch (jComboBoxOrderBy.getSelectedIndex()) {
                case 0 -> productCards.sort(Comparator.comparingDouble(ProductCard::getPrice));
                case 1 -> productCards.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                case 3 -> productCards.sort((p1, p2) -> p2.getProductName().compareTo(p1.getProductName()));
                default -> productCards.sort(Comparator.comparing(ProductCard::getProductName));
            }
            switch (jComboBoxLoaiSp.getSelectedIndex()) {
                case 1 -> productCards.forEach(p -> p.setVisible(p.getProduct().getType() == Product.ProductType.FOOD));
                case 2 -> productCards.forEach(p -> p.setVisible(p.getProduct().getType() == Product.ProductType.DRINK));
                case 3 -> productCards.forEach(p -> p.setVisible(p.getProduct().getType() == Product.ProductType.CARD));
                default ->  productCards.forEach(p -> p.setVisible(true));
            }

           renderProductCards();
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxLoaiSp = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxOrderBy = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelProduct = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Nunito SemiBold", 1, 26)); // NOI18N
        jLabel1.setText("Dịch vụ");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 10, 2));

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jLabel8.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel8.setText("Loại sản phẩm");
        jPanel6.add(jLabel8);

        jComboBoxLoaiSp.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBoxLoaiSp.setOpaque(true);
        jComboBoxLoaiSp.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel6.add(jComboBoxLoaiSp);

        jPanel2.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel4.setText("Lọc theo tên");
        jPanel7.add(jLabel4);

        jTextFieldName.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jTextFieldName.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel7.add(jTextFieldName);

        jPanel2.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel9.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel9.setText("Sắp xếp theo:");
        jPanel8.add(jLabel9);

        jComboBoxOrderBy.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBoxOrderBy.setOpaque(true);
        jComboBoxOrderBy.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel8.add(jComboBoxOrderBy);

        jPanel2.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 37, 5));

        jLabel6.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jPanel9.add(jLabel6);

        jPanel2.add(jPanel9);

        jPanel3.setPreferredSize(new java.awt.Dimension(1480, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 729, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(1480, 40));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 5));

        jButton2.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jButton2.setText("Clear");
        jButton2.setPreferredSize(new java.awt.Dimension(150, 34));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton1.setBackground(new java.awt.Color(66, 153, 225));
        jButton1.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lọc");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 34));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton4.setBackground(new java.awt.Color(66, 153, 225));
        jButton4.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xem giỏ hàng");
        jButton4.setPreferredSize(new java.awt.Dimension(150, 35));
        jButton4.setRolloverEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jPanel2.add(jPanel4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));
        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelProduct.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(jPanelProduct);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        var list = new ArrayList<InvoiceDetailInputDTO>();
        this.productCards.forEach((card) -> {
            if (card.isAddedToCart()) {
                list.add(InvoiceDetailInputDTO.builder().
                        product(card.getProduct()).
                        productId(card.getProduct().getId()).
                        quantity(card.getQuantity()).
                        build());
            }
        });
        Cart cartView = new Cart(list);

        cartView.setOnClearAll(() -> {
            this.productCards.forEach((card) -> {
                card.setAddedToCart(false);
            });
        });
        cartView.setOnDelete((productId) -> {
            this.productCards.forEach((card) -> {
                if (card.getProduct().getId().equals(productId)) {
                    card.setAddedToCart(false);
                    card.setQuantity(0);
                    card.update(card.getGraphics());
                }
            });
        });
        cartView.setVisible(true);
        cartView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartView.setLocationRelativeTo(null);


    }//GEN-LAST:event_jButton4ActionPerformed

    public static void main(String args[]) {
        ServiceProvider.init();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FoodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FoodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FoodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FoodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Helper.initUI();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FoodOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<TypeProductCbItem> jComboBoxLoaiSp;
    private javax.swing.JComboBox<String> jComboBoxOrderBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelProduct;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
