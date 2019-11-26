/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import bodies.Body;
import bodies.MovingBody;
import editor.controller.EntityClicker;
import images.GameImages;
import images.animations.GameAnimations;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import shapes.GameShape;
import sidescrollerproto.SideScrollerProto;

/**
 *
 * @author christian
 */
public class EntityPropertyScreen extends javax.swing.JFrame {

    GameShape entity;
    Body body = null;
    List<Body> bodies;
    EntityClicker entityClicker;
    private boolean enableSelect;
    private SideScrollerProto frameReference;

    /**
     * Creates new form EntityPropertyScreen
     */
    public EntityPropertyScreen(GameShape entity, EntityClicker entityClicker, List<Body> bodies, SideScrollerProto frameReference) {
        enableSelect = false;
        initComponents();
        this.frameReference = frameReference;
        this.entity = entity;
        txtParalax.setText(String.valueOf(entity.getParalax()));
        this.entityClicker = entityClicker;
        this.bodies = bodies;

        chkAffectedByGravity.setVisible(false);
        cmbStandingAnimation.setVisible(false);
        chkFallingAnimation.setVisible(false);
        cmbFallingAnimation.setVisible(false);
        chkMovingAnimation.setVisible(false);
        cmbMovingAnimation.setVisible(false);
        btnMakePlayer.setVisible(false);

        if (entity.getBody() != null) {
            this.body = entity.getBody();
            pnlBody.setVisible(true);
            if (body instanceof MovingBody) {
                chkAffectedByGravity.setVisible(true);
                chkAffectedByGravity.setSelected(((MovingBody) body).isAfectedByGravity());
                btnMakePlayer.setVisible(true);
            }
        } else {
            pnlBody.setVisible(false);
        }
        loadImageDropDown();
        loadAnimationsDropDown();
        enableSelect = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtParalax = new javax.swing.JTextField();
        cmbImagenes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        chkMovingBody = new javax.swing.JCheckBox();
        btnDeleteBody = new javax.swing.JButton();
        chkAffectedByGravity = new javax.swing.JCheckBox();
        btnMakePlayer = new javax.swing.JButton();
        chkStandingAnimation = new javax.swing.JCheckBox();
        cmbStandingAnimation = new javax.swing.JComboBox<>();
        chkMovingAnimation = new javax.swing.JCheckBox();
        cmbMovingAnimation = new javax.swing.JComboBox<>();
        chkFallingAnimation = new javax.swing.JCheckBox();
        cmbFallingAnimation = new javax.swing.JComboBox<>();
        btnAddBody = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Paralax");

        txtParalax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtParalaxFocusLost(evt);
            }
        });
        txtParalax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtParalaxKeyPressed(evt);
            }
        });

        cmbImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImagenesActionPerformed(evt);
            }
        });

        jLabel2.setText("Imagen");

        chkMovingBody.setText("Is moving body");
        chkMovingBody.setToolTipText("");
        chkMovingBody.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMovingBodyActionPerformed(evt);
            }
        });

        btnDeleteBody.setText("Delete Body");

        chkAffectedByGravity.setText("Affected by gravity");
        chkAffectedByGravity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAffectedByGravityActionPerformed(evt);
            }
        });

        btnMakePlayer.setText("Make this the Player");
        btnMakePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakePlayerActionPerformed(evt);
            }
        });

        chkStandingAnimation.setText("Standing Animation");
        chkStandingAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkStandingAnimationActionPerformed(evt);
            }
        });

        chkMovingAnimation.setText("Moving Animation");

        chkFallingAnimation.setText("Falling Animation");

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMovingAnimation)
                    .addComponent(chkFallingAnimation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbFallingAnimation, javax.swing.GroupLayout.Alignment.LEADING, 0, 131, Short.MAX_VALUE)
                    .addComponent(cmbMovingAnimation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDeleteBody))
                    .addComponent(chkStandingAnimation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbStandingAnimation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMakePlayer)
                    .addComponent(chkAffectedByGravity)
                    .addComponent(chkMovingBody))
                .addGap(15, 15, 15))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteBody)
                    .addComponent(chkMovingBody))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAffectedByGravity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMakePlayer))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(chkStandingAnimation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStandingAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkMovingAnimation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMovingAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(chkFallingAnimation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFallingAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        btnAddBody.setText("Add Body");
        btnAddBody.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBodyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtParalax, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddBody)
                        .addGap(0, 352, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtParalax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddBody)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtParalaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtParalaxFocusLost
        entity.setParalax(Float.valueOf(txtParalax.getText()));
    }//GEN-LAST:event_txtParalaxFocusLost

    private void txtParalaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParalaxKeyPressed
        if (evt.getKeyChar() < 48 || evt.getKeyChar() > 57) {
            System.out.println((int) evt.getKeyChar());
            evt.consume();
        }
    }//GEN-LAST:event_txtParalaxKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        entityClicker.setEnabled(true);
        entityClicker.Unselect();
        entity.setColor(Color.YELLOW);

    }//GEN-LAST:event_formWindowClosing

    private void cmbImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImagenesActionPerformed
        if (enableSelect) {
            entity.setObjectImage((String) cmbImagenes.getSelectedItem());
            System.out.println(cmbImagenes.getSelectedItem());
        }


    }//GEN-LAST:event_cmbImagenesActionPerformed

    private void btnAddBodyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBodyActionPerformed
        Body newBody = new Body();
        newBody.setShape(entity);
        entity.setBody(newBody);
        bodies.add(newBody);
        body = newBody;
        pnlBody.setVisible(true);
    }//GEN-LAST:event_btnAddBodyActionPerformed

    private void chkMovingBodyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMovingBodyActionPerformed
        if (enableSelect) {
            if (chkMovingBody.isSelected()) {
                MovingBody newMovingBody = new MovingBody();
                bodies.remove(body);
                newMovingBody.setShape(entity);
                entity.setBody(newMovingBody);
                body = newMovingBody;
                bodies.add(newMovingBody);
                chkAffectedByGravity.setVisible(true);
                btnMakePlayer.setVisible(true);
            } else {
                bodies.remove(body);
                Body newBody = new Body();
                newBody.setShape(entity);
                entity.setBody(newBody);
                bodies.add(newBody);
                body = newBody;
                chkAffectedByGravity.setVisible(false);
                btnMakePlayer.setVisible(false);
            }
        }
    }//GEN-LAST:event_chkMovingBodyActionPerformed

    private void chkAffectedByGravityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAffectedByGravityActionPerformed
        if (enableSelect) {
            if (chkAffectedByGravity.isSelected()) {
                ((MovingBody) body).setAfectedByGravity(true);
            } else {
                ((MovingBody) body).setAfectedByGravity(false);
            }
        }
    }//GEN-LAST:event_chkAffectedByGravityActionPerformed

    private void btnMakePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakePlayerActionPerformed
        if (enableSelect) {
            frameReference.setPlayer((MovingBody) this.body);
        }
    }//GEN-LAST:event_btnMakePlayerActionPerformed

    private void chkStandingAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkStandingAnimationActionPerformed
        if(chkStandingAnimation.isSelected())
        {
            cmbFallingAnimation.setVisible(true);
            if(entity.getBody() != null)
            {
                entity.getBody().setStandingAnimation(GameAnimations.getAnimation((String)cmbStandingAnimation.getSelectedItem()));
                System.out.println("set!");
            }
        }
        else
        {
            cmbFallingAnimation.setVisible(false);
            if(entity.getBody() != null)
            {
                entity.getBody().setStandingAnimation(null);
            }
        }
    }//GEN-LAST:event_chkStandingAnimationActionPerformed

    private final void loadImageDropDown() {
        List<String> imageNames = GameImages.getImagesNames();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String imageName : imageNames) {
            model.addElement(imageName);
            if (imageName.equals(GameImages.getImageName(entity.getObjectImage()))) {
                model.setSelectedItem(imageName);
            }
        }
        cmbImagenes.setModel(model);
    }

    private final void loadAnimationsDropDown() {
        List<String> animationNames = GameAnimations.getImagesNames();
        DefaultComboBoxModel<String> fallingModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> standingModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> movingModel = new DefaultComboBoxModel<>();
        for (String animationName : animationNames) {
            fallingModel.addElement(animationName);
            standingModel.addElement(animationName);
            movingModel.addElement(animationName);
            if (entity.getBody() != null) {
                if (entity.getBody().getStandingAnimation() != null
                        && animationName.equals(GameAnimations.getImageName(entity.getBody().getStandingAnimation()))) {
                    standingModel.setSelectedItem(animationName);
                }
                if (entity.getBody() instanceof MovingBody) {
                    if (((MovingBody) entity.getBody()).getMovingAnimation() != null
                            && animationName.equals(GameAnimations.getImageName(((MovingBody) entity.getBody()).getMovingAnimation()))) {
                        movingModel.setSelectedItem(animationName);
                    }
                    if (((MovingBody) entity.getBody()).getFallingAnimation() != null
                            && animationName.equals(GameAnimations.getImageName(((MovingBody) entity.getBody()).getFallingAnimation()))) {
                        fallingModel.setSelectedItem(animationName);
                    }
                }
            }
        }
        cmbStandingAnimation.setModel(standingModel);
        cmbMovingAnimation.setModel(movingModel);
        cmbFallingAnimation.setModel(fallingModel);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBody;
    private javax.swing.JButton btnDeleteBody;
    private javax.swing.JButton btnMakePlayer;
    private javax.swing.JCheckBox chkAffectedByGravity;
    private javax.swing.JCheckBox chkFallingAnimation;
    private javax.swing.JCheckBox chkMovingAnimation;
    private javax.swing.JCheckBox chkMovingBody;
    private javax.swing.JCheckBox chkStandingAnimation;
    private javax.swing.JComboBox<String> cmbFallingAnimation;
    private javax.swing.JComboBox<String> cmbImagenes;
    private javax.swing.JComboBox<String> cmbMovingAnimation;
    private javax.swing.JComboBox<String> cmbStandingAnimation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtParalax;
    // End of variables declaration//GEN-END:variables
}
