/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.telas;

import engine.DataSource;
import engine.Group;
import java.awt.HeadlessException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import util.Yagami;
import view.Main;

/**
 *
 * @author Juan
 */
public class Menu extends javax.swing.JInternalFrame {
    protected Timer timer = new Timer();
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setBounds(0, 0, this.getWidth(), Main.desktop.getHeight() - 64);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Telas do Sistema");

        /**
         * create the child nodes verifica qual o tipo de usuário e escolhe
         * entra no caso expecifico para o mesmo, assim mostrando apenas as
         * funcilaidades do sistema disponiveis para ele;
         *
         */
        switch (Yagami.usuario.getNivelAcesso()) {
            //Caso seja admin
            case 1:
                for (Group g : DataSource.DS.adminTree) {
                    DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(g.getNomeGrupo());
                    for (JInternalFrame tela : g.getTelas()) {
                        gNode.add(new DefaultMutableTreeNode(tela.getName() + " - " + tela.getToolTipText()));
                    }
                    root.add(gNode);
                }
                break;
            //Caso seja Fisioterapeuta
            case 2:
                for (Group g : DataSource.DS.fisioterapeutaTree) {
                    DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(g.getNomeGrupo());
                    for (JInternalFrame tela : g.getTelas()) {
                        gNode.add(new DefaultMutableTreeNode(tela.getName() + " - " + tela.getToolTipText()));
                    }
                    root.add(gNode);
                }
                break;
            //Caso seja Fisioterapeuta
            case 3:
                for (Group g : DataSource.DS.funcionarioTree) {
                    DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(g.getNomeGrupo());
                    for (JInternalFrame tela : g.getTelas()) {
                        gNode.add(new DefaultMutableTreeNode(tela.getName() + " - " + tela.getToolTipText()));
                    }
                    root.add(gNode);
                }
                break;
            //Caso seja Fisioterapeuta
            case 4:
                for (Group g : DataSource.DS.alunoTree) {
                    DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(g.getNomeGrupo());
                    for (JInternalFrame tela : g.getTelas()) {
                        gNode.add(new DefaultMutableTreeNode(tela.getName() + " - " + tela.getToolTipText()));
                    }
                    root.add(gNode);
                }
                break;
        }
        
        
        /*
        tree.getSelectionModel().addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(selectedNode.isLeaf()) {
                JOptionPane.showMessageDialog(null, selectedNode.getUserObject().toString());
            }
            
        });
        */
        
        /*
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));
        
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
        */
        
        //create the tree by passing in the root node
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        //treeModel.setRoot(root);
        treeModel.reload();
        tree.setModel(treeModel);
        //tree.setRootVisible(false);
        // Loop que atualzia a cada 50 milisegundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Método para verificar preenchimento dos Text Fields
                //System.out.println(Menu.this.isSelected());
                if (Menu.this.isSelected() == false) {
                    Menu.this.dispose();
                }

            }
        }, 4, 50);

    }
    
    
    
    /*
    tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            selectedLabel.setText(e.getPath().toString());
        }
    });
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTela = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();

        jLabel1.setText("jLabel1");

        setClosable(true);
        setTitle("Menu - Telas");
        setName("Menu"); // NOI18N
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.setRowHeight(25);
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                treeMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTela, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTela, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:


    }//GEN-LAST:event_formFocusLost

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseReleased

    private void treeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseReleased

    }//GEN-LAST:event_treeMouseReleased

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            try {
                evt.consume();
                try {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (selectedNode.isLeaf()) {
                        String codTela;
                        codTela = Yagami.recortarString(selectedNode.getUserObject().toString(), 6);
                        Yagami.abrirTelaCodigo(codTela, Main.desktop);
                        
                        //JOptionPane.showMessageDialog(null, );
                        //Yagami.exibirTela((JInternalFrame) selectedNode.getUserObject(), getParent(), false);
                    }
                } catch (HeadlessException e) {
                    //JOptionPane.showMessageDialog(null, e);
                    Yagami.mensagemErro(e);
                }

            } catch (HeadlessException error) {
                JOptionPane.showMessageDialog(null, error);
            }

        }
    }//GEN-LAST:event_treeMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        Yagami.abrirTelaCodigo(txtTela.getText(), getParent());
    }//GEN-LAST:event_btnPesquisarActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree tree;
    private javax.swing.JTextField txtTela;
    // End of variables declaration//GEN-END:variables
}
