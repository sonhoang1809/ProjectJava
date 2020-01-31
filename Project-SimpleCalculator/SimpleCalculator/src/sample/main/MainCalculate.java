/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.main;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author sonho
 */
public class MainCalculate extends javax.swing.JFrame {

    ArrayList<String> listInput;
    ArrayList<Integer> memory;
    ArrayList<String> listMPlus;
    ArrayList<String> listMSub;
    boolean dotIsSelected = false;

    boolean btnShowResultIsSelected = false;
    boolean btnMPlusIsSelected = false;
    boolean btnMSubIsSelected = false;

    /**
     * Creates new form MainCalculate
     */
    public MainCalculate() {
        initComponents();
        listInput = new ArrayList<>();
        memory = new ArrayList<>();
        listMPlus = new ArrayList<>();
        listMSub = new ArrayList<>();
    }

    private boolean validToInputDot() {
        //kiem tra xem lastInput la number k
        if (Support.checkLastInputIsNumber(listInput)) {
            if (Support.checkInputNumContainsDot(listInput.get(listInput.size() - 1))) {
                return false;
            }
            ArrayList<String> InputNumber;
            //neu lastInput la number thi kiem tra xem listInput co chua calculator k
            if (Support.checkContainCalculator(listInput)) {
                //neu co chua calculator thi ta dang nhap secondNum
                InputNumber = Support.getInputSecondNum(listInput);
            } else {
                //neu k chua calculator thi ta dang nhap firstNum
                InputNumber = Support.getInputFirstNum(listInput);
            }
            if (Support.checkInputNumContainsDot(InputNumber)) {
                return false;
            }
            return true;
        } else if (Support.checkLastInputIsCalculation(listInput)) {
            return false;
        } else if (Support.checkLastInputIsDot(listInput)) {
            return false;
        }
        return true;
    }

    private void inputOpposite() {
        if (Support.checkFirstInputIsNumber(listInput)) {
            if (Support.checkNegativeNum(listInput.get(0))) {
                double result = Support.convertToOppositeNum(
                        Support.convertDouble(listInput.get(0)));
                if (Support.canHaveTypeInt(result)) {
                    int n = Support.convertIntNumberFromDouble(result);
                    listInput.set(0, n + "");
                } else {
                    listInput.set(0, result + "");
                }
            } else {
                listInput.add(0, "-");
                Support.modifyInputNumberWhenItIsANegativeNumber(listInput);
            }
        }
    }

    private void inputDot() {
        listInput.add(".");
        String s = lbShow.getText();
        lbShow.setText(s + ".");
    }

    private void inputN(JButton btn) {
        String s = lbShow.getText();
        //kiem tra xem listInput da nhap gi chua
        if (!listInput.isEmpty()) {
            //kiem tra xem last input co phai la mot calculation k
            //neu phai thi k lay lbShow.getText() vi day la firstNum
            //=> nhap so dau tien cua secondNum
            //hoac kiem tra xem nut = co vua dc chon hay k
            //
            if (Support.checkLastInputIsCalculation(listInput)) {
                s = "";
                s = btn.getText();
                lbShow.setText(s);
                return;
            }
        }
        //neu btnShowRsult vua dc nhan thi set s =""
        if (btnShowResultIsSelected || btnMPlusIsSelected || btnMSubIsSelected || Support.checkType(s).equalsIgnoreCase("string")) {
            s = "";
        }
        //khong can biet la s dang la firstNum hay secondNum
        //chi can so sanh s voi 0
        //neu bang 0 thi no bang btn.getText()
        //con khong thi la nhap tiep theo cua so do
        if (s.equalsIgnoreCase("0")) {
            s = btn.getText();
        } else {
            s = s + btn.getText();
        }

        lbShow.setText(s);
    }

    private void inputNumber(JButton btn) {
        //       String s = lbShow.getText();

        if (listInput.size() > 0) {
            if (btnShowResultIsSelected == true && !Support.checkLastInputIsCalculation(listInput)) {
                listInput.clear();
            }
            inputN(btn);
            listInput.add(btn.getText());
        } else {
            inputN(btn);
            listInput.add(btn.getText());
        }
        btnShowResultIsSelected = false;
        btnMPlusIsSelected = false;
        btnMSubIsSelected = false;
        // System.out.println(listInput.get(listInput.size() - 1));
    }

    private void calculateSpec() {
        Support.calculateSpec(listInput);
        String show = listInput.get(listInput.size() - 1);
        if (Support.checkType(show).equalsIgnoreCase("string")) {
            listInput.clear();
            lbShow.setText(show);
        } else {
            lbShow.setText(listInput.get(listInput.size() - 1));
        }
    }

    private void inputSpecCalculator(JButton btn) {
        if (listInput.size() > 0) {
            //neu last input la 1 calculation va input dau tien k phai la dau tru
            if (Support.checkLastInputIsCalculation(listInput)) {
                //neu nhap mot calculation khac
                //thi thay doi calculation cu the no bang calculation moi
                if (!Support.getLastInput(listInput).equalsIgnoreCase(btn.getText())) {
                    listInput.remove(listInput.size() - 1);
                    listInput.add(btn.getText());
                    Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
                }
                //kiem tra xem phan tu cuoi cung la mot number
            } else if (Support.checkLastInputIsNumber(listInput)) {
                //kiem tra xem trong listInput neu size lon hon 1 thi 
                //can modify
                if (Support.checkContainCalculator(listInput)) {
                    if (listInput.size() > 3) {
                        Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
                        Support.modifySecondNumWhenLastInputIsNumber(listInput);
                    }
                    Support.calculate(listInput);
                    //listInput.add(btn.getText());
                    if (listInput.size() > 0) {
                        System.out.println(listInput.get(listInput.size() - 1));
                    }
                }
                if (listInput.size() > 1) {
                    Support.modifyFirstNum(listInput);
                    listInput.add(btn.getText());
                } else {
                    listInput.add(btn.getText());
                }
            }
            calculateSpec();
            btnShowResultIsSelected = true;
        }
    }

    private void inputCalculator(JButton btn) {
        if (listInput.size() > 0) {

//            //kiem tra xem lastInput co phai la ERROR k
//            if (Support.checkType(listInput.get(0)).equalsIgnoreCase("string")) {
//                listInput.clear();
//            }
            //neu last input la 1 calculation va input dau tien k phai la dau tru
            if (Support.checkLastInputIsCalculation(listInput) && !Support.checkFirstInputIsSub(listInput)) {
                //neu nhap mot calculation khac
                //thi thay doi calculation cu the no bang calculation moi
                if (!Support.getLastInput(listInput).equalsIgnoreCase(btn.getText())) {
                    listInput.remove(listInput.size() - 1);
                    listInput.add(btn.getText());
                    Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
                }

            } //kiem tra xem neu ky tu dau tien la dau tru thi se modify so dau tien
            else if (Support.checkFirstInputIsSub(listInput)) {
                Support.modifyInputNumberWhenItIsANegativeNumber(listInput);
                listInput.add(btn.getText());
            } //kiem tra xem input cuoi cung la so
            else if (Support.checkLastInputIsNumber(listInput)) {
                //kiem tra xem trong listInput co calculation k
                if (Support.checkContainCalculator(listInput)) {
                    listInput.add(btn.getText());
                    Support.modifySecondNumWhenLastInputIsCalculator(listInput);
                    Support.calculate(listInput);
                } //neu khong co calculation
                //thi day la firstNum
                //add them calculation vao listInput roi modify firstNum
                else {
                    listInput.add(btn.getText());
                    Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
                }
            } else {
                listInput.add(btn.getText());
                //khi nhap dau thi se modify firstNum
                Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
            }
        } else {
            if (lbShow.getText().equalsIgnoreCase("0")) {
                listInput.add("0");
                listInput.add(btn.getText());
            }
        }
        if (listInput.size() > 0) {
            lbShow.setText(Support.getNearLastInput(listInput));
            // System.out.println(listInput.get(listInput.size() - 1));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMC = new javax.swing.JButton();
        btnMR = new javax.swing.JButton();
        btnMPlus = new javax.swing.JButton();
        btnMSub = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnDivide = new javax.swing.JButton();
        btnSquare = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnMulti = new javax.swing.JButton();
        btnPercent = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnSub = new javax.swing.JButton();
        btnInverse = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnOpposite = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnShowResult = new javax.swing.JButton();
        lbShow = new javax.swing.JLabel();
        sp = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(5, 5, 20, 20));

        btnMC.setBackground(new java.awt.Color(255, 255, 102));
        btnMC.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnMC.setText("MC");
        btnMC.setPreferredSize(new java.awt.Dimension(80, 80));
        btnMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMCActionPerformed(evt);
            }
        });
        jPanel1.add(btnMC);

        btnMR.setBackground(new java.awt.Color(255, 255, 102));
        btnMR.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnMR.setText("MR");
        btnMR.setPreferredSize(new java.awt.Dimension(80, 80));
        btnMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMRActionPerformed(evt);
            }
        });
        jPanel1.add(btnMR);

        btnMPlus.setBackground(new java.awt.Color(255, 255, 102));
        btnMPlus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnMPlus.setText("M+");
        btnMPlus.setPreferredSize(new java.awt.Dimension(80, 80));
        btnMPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPlusActionPerformed(evt);
            }
        });
        jPanel1.add(btnMPlus);

        btnMSub.setBackground(new java.awt.Color(255, 255, 102));
        btnMSub.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnMSub.setText("M-");
        btnMSub.setPreferredSize(new java.awt.Dimension(80, 80));
        btnMSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMSubActionPerformed(evt);
            }
        });
        jPanel1.add(btnMSub);

        btnC.setBackground(new java.awt.Color(255, 102, 51));
        btnC.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnC.setText("C");
        btnC.setPreferredSize(new java.awt.Dimension(80, 80));
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });
        jPanel1.add(btnC);

        btn7.setBackground(new java.awt.Color(0, 204, 255));
        btn7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn7.setText("7");
        btn7.setPreferredSize(new java.awt.Dimension(80, 80));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn7);

        btn8.setBackground(new java.awt.Color(0, 204, 255));
        btn8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn8.setText("8");
        btn8.setPreferredSize(new java.awt.Dimension(80, 80));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(btn8);

        btn9.setBackground(new java.awt.Color(0, 204, 255));
        btn9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn9.setText("9");
        btn9.setPreferredSize(new java.awt.Dimension(80, 80));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9);

        btnDivide.setBackground(new java.awt.Color(153, 255, 153));
        btnDivide.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnDivide.setText("÷");
        btnDivide.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDivide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivideActionPerformed(evt);
            }
        });
        jPanel1.add(btnDivide);

        btnSquare.setBackground(new java.awt.Color(153, 255, 153));
        btnSquare.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnSquare.setText("√");
        btnSquare.setPreferredSize(new java.awt.Dimension(80, 80));
        btnSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSquareActionPerformed(evt);
            }
        });
        jPanel1.add(btnSquare);

        btn4.setBackground(new java.awt.Color(0, 204, 255));
        btn4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn4.setText("4");
        btn4.setPreferredSize(new java.awt.Dimension(80, 80));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(btn4);

        btn5.setBackground(new java.awt.Color(0, 204, 255));
        btn5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn5.setText("5");
        btn5.setPreferredSize(new java.awt.Dimension(80, 80));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5);

        btn6.setBackground(new java.awt.Color(0, 204, 255));
        btn6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn6.setText("6");
        btn6.setPreferredSize(new java.awt.Dimension(80, 80));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6);

        btnMulti.setBackground(new java.awt.Color(153, 255, 153));
        btnMulti.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnMulti.setText("x");
        btnMulti.setPreferredSize(new java.awt.Dimension(80, 80));
        btnMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiActionPerformed(evt);
            }
        });
        jPanel1.add(btnMulti);

        btnPercent.setBackground(new java.awt.Color(153, 255, 153));
        btnPercent.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnPercent.setText("%");
        btnPercent.setPreferredSize(new java.awt.Dimension(80, 80));
        btnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPercentActionPerformed(evt);
            }
        });
        jPanel1.add(btnPercent);

        btn1.setBackground(new java.awt.Color(0, 204, 255));
        btn1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn1.setText("1");
        btn1.setPreferredSize(new java.awt.Dimension(80, 80));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1);

        btn2.setBackground(new java.awt.Color(0, 204, 255));
        btn2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn2.setText("2");
        btn2.setPreferredSize(new java.awt.Dimension(80, 80));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn2);

        btn3.setBackground(new java.awt.Color(0, 204, 255));
        btn3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn3.setText("3");
        btn3.setPreferredSize(new java.awt.Dimension(80, 80));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(btn3);

        btnSub.setBackground(new java.awt.Color(153, 255, 153));
        btnSub.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnSub.setText("-");
        btnSub.setPreferredSize(new java.awt.Dimension(80, 80));
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubActionPerformed(evt);
            }
        });
        jPanel1.add(btnSub);

        btnInverse.setBackground(new java.awt.Color(153, 255, 153));
        btnInverse.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnInverse.setText("1/x");
        btnInverse.setPreferredSize(new java.awt.Dimension(80, 80));
        btnInverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInverseActionPerformed(evt);
            }
        });
        jPanel1.add(btnInverse);

        btn0.setBackground(new java.awt.Color(0, 204, 255));
        btn0.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btn0.setText("0");
        btn0.setPreferredSize(new java.awt.Dimension(80, 80));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        jPanel1.add(btn0);

        btnDot.setBackground(new java.awt.Color(0, 204, 255));
        btnDot.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnDot.setText(".");
        btnDot.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        jPanel1.add(btnDot);

        btnOpposite.setBackground(new java.awt.Color(0, 204, 255));
        btnOpposite.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnOpposite.setText("+/-");
        btnOpposite.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOpposite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOppositeActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpposite);

        btnPlus.setBackground(new java.awt.Color(153, 255, 153));
        btnPlus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnPlus.setText("+");
        btnPlus.setPreferredSize(new java.awt.Dimension(80, 80));
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });
        jPanel1.add(btnPlus);

        btnShowResult.setBackground(new java.awt.Color(153, 255, 153));
        btnShowResult.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        btnShowResult.setText("=");
        btnShowResult.setPreferredSize(new java.awt.Dimension(80, 80));
        btnShowResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowResultActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowResult);

        lbShow.setBackground(new java.awt.Color(153, 255, 255));
        lbShow.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 40)); // NOI18N
        lbShow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbShow.setText("0");
        lbShow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbShow.setOpaque(true);

        sp.setForeground(new java.awt.Color(153, 153, 153));
        sp.setToolTipText("A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbShow, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbShow, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMCActionPerformed
        // TODO add your handling code here:
        listMPlus.clear();
        listMSub.clear();
    }//GEN-LAST:event_btnMCActionPerformed

    private void btnMRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMRActionPerformed
        // TODO add your handling code here:
        String result = "";
        if (!listMPlus.isEmpty() && !listMSub.isEmpty()) {
            result = Support.sumListM(listMSub, listMPlus);
        } else if (!listMPlus.isEmpty()) {
            result = Support.sumList(listMPlus);
        } else if (!listMSub.isEmpty()) {
            result = Support.sumList(listMSub);
        }
        listInput.clear();
        if (!result.equalsIgnoreCase("")) {
            lbShow.setText(result);
            listInput.add(result);
        } else {
            lbShow.setText("0");
            listInput.add("");
        }
    }//GEN-LAST:event_btnMRActionPerformed

    private void btnMPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPlusActionPerformed
        // TODO add your handling code here:
        String s = lbShow.getText();
        if (!Support.checkType(s).equalsIgnoreCase("string")) {
            Support.addToListMPlus(listMPlus, s);
            btnMPlusIsSelected = true;
            String tmp = listInput.get(listInput.size() - 1);
            listInput.clear();
            listInput.add(tmp);
        }
    }//GEN-LAST:event_btnMPlusActionPerformed

    private void btnMSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMSubActionPerformed
        // TODO add your handling code here:
        String s = lbShow.getText();
        if (!Support.checkType(s).equalsIgnoreCase("string")) {
            Support.addToListMSub(listMSub, s);
            btnMSubIsSelected = true;
            String tmp = listInput.get(listInput.size() - 1);
            listInput.clear();
            listInput.add(tmp);
        }
    }//GEN-LAST:event_btnMSubActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
        lbShow.setText("0");
        listInput.clear();
    }//GEN-LAST:event_btnCActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        inputNumber(btn7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        inputNumber(btn8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        inputNumber(btn9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btnDivideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivideActionPerformed
        inputCalculator(btnDivide);
    }//GEN-LAST:event_btnDivideActionPerformed

    private void btnSquareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSquareActionPerformed
        // TODO add your handling code here:
        inputSpecCalculator(btnSquare);
    }//GEN-LAST:event_btnSquareActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        inputNumber(btn6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btnMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiActionPerformed
        // TODO add your handling code here:
        inputCalculator(btnMulti);
    }//GEN-LAST:event_btnMultiActionPerformed

    private void btnPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPercentActionPerformed
        // TODO add your handling code here:
        inputSpecCalculator(btnPercent);
    }//GEN-LAST:event_btnPercentActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btnSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubActionPerformed
        inputCalculator(btnSub);
    }//GEN-LAST:event_btnSubActionPerformed
// 1/x
    private void btnInverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInverseActionPerformed
        // TODO add your handling code here:
        inputSpecCalculator(btnInverse);
    }//GEN-LAST:event_btnInverseActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        // TODO add your handling code here:
        inputNumber(btn0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        // TODO add your handling code here:
        if (validToInputDot()) {
            inputDot();
            //System.out.println(".");
        }
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnOppositeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOppositeActionPerformed
        inputOpposite();
        String s = Support.getInputNum(listInput);
        lbShow.setText(s);
    }//GEN-LAST:event_btnOppositeActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        inputCalculator(btnPlus);
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnShowResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowResultActionPerformed
        // TODO add your handling code here:
        //kiem tra xem listInput da co it nhat 3 phan tu chua
        //vi can co it nhat 3 (1+2)
        if (listInput.size() >= 3) {
            //kiem tra xem lastInput co phai la 1 so k (1+2)
            if (Support.checkLastInputIsNumber(listInput)) {
                //kiem tra xem trong listInput co chua calculation k
                if (Support.checkContainCalculator(listInput)) {
                    //neu listInput lon hon 3 thi can modify so secondNum
                    if (listInput.size() > 3) {
                        Support.modifySecondNumWhenLastInputIsNumber(listInput);
                    }
                    Support.calculate(listInput);
                    lbShow.setText(listInput.get(0));
                }
            } //kiem tra xem lastInput co phai la 1 calculation k
            else if (Support.checkLastInputIsCalculation(listInput)) {
                //problem ??
                //neu la 1 calculation thi day la 1 firstNum
                //=>> modify firstNum lai
                Support.modifyFirstNumWhenLastInputIsCalculator(listInput);
            }
            btnShowResultIsSelected = true;
        }
    }//GEN-LAST:event_btnShowResultActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainCalculate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCalculate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCalculate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCalculate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainCalculate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnDivide;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnInverse;
    private javax.swing.JButton btnMC;
    private javax.swing.JButton btnMPlus;
    private javax.swing.JButton btnMR;
    private javax.swing.JButton btnMSub;
    private javax.swing.JButton btnMulti;
    private javax.swing.JButton btnOpposite;
    private javax.swing.JButton btnPercent;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnShowResult;
    private javax.swing.JButton btnSquare;
    private javax.swing.JButton btnSub;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbShow;
    private javax.swing.JSeparator sp;
    // End of variables declaration//GEN-END:variables
}
