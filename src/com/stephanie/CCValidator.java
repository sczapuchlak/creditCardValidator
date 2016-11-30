package com.stephanie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CCValidator extends JFrame{

    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;
    private boolean resetMessageOnKeyPress = false;

    public CCValidator(){
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = ccValid(ccNumber);

                if(valid) {
                    validMessageLabel.setText("Credit Card number is Valid!");
                }else {
                    validMessageLabel.setText("Call the police! It is not valid!");
                }
                resetMessageOnKeyPress = true;
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        creditCardNumberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (resetMessageOnKeyPress){
                    validMessageLabel.setText("~Valid or not valid displayed here");
                    resetMessageOnKeyPress = false;
                }
            }
        });
    }
    public static void main(String[] args) {

        //Example credit card numbers
        //These should be valid
        String cc1 = "4123123412341236";
        String cc2 = "4000111122223339";

        System.out.println(ccValid(cc1));
        System.out.println(ccValid(cc2));


        //Example credit card numbers
        //These should NOT be valid
        String not_cc1 = "4456567456745645";    //Check digit wrong
        String not_cc2 = "3534523423424234";    //Doesn't start with 4
        String not_cc3 = "35424234";            //Wrong length


        System.out.println(ccValid(not_cc1));
        System.out.println(ccValid(not_cc2));
        System.out.println(ccValid(not_cc3));
    }


    private static boolean ccValid(String cc) {

        if (!cc.startsWith("4") || (cc.length() != 16)){
            System.out.println("Doesnt start with 4 or length wrong");
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 16 ; i++ ) {
            int thisDigit = Integer.parseInt((cc.substring(i, i+1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9 ) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

        return false;

    }



}