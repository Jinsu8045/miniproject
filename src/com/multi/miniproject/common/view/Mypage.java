package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.MemberDao;
import com.multi.miniproject.member.model.dto.MemberDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Mypage extends UI{

    public void p04() {

        MemberDao dao = new MemberDao();

        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p04 : 마이페이지    ");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("이름 : " + dao.loginUser(loginUser).getName());
        JLabel l3 = new JLabel("아이디(이메일) :" + dao.loginUser(loginUser).getId() +
                "(" + dao.loginUser(loginUser).getEmail() + ")");
        //이미지
        JLabel img1 = new JLabel("          ");
        img1.setIcon(new ImageIcon("images/img.png"));
        //
        JLabel l4 = new JLabel("//계정 관리");
        JButton b1 = new JButton("프로필 재설정");
        JButton b2 = new JButton("회원탈퇴");
        JButton b3 = new JButton("로그아웃");

        f.add(b0);
        f.add(l2);
        f.add(l3);
        f.add(img1);
        f.add(l4);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p04_1();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p04_2();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "로그아웃되었습니다.");
                loginUser = null;
                p01();
            }
        }); //b3.addActionListener
        /////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p04() : 마이페이지

    public void p04_1() {

        MemberDao dao = new MemberDao();

        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p04_1 : 프로필 재설정");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("아이디");
        JTextField t8 = new JTextField(20);
        t8.setText(dao.loginUser(loginUser).getId());
        t8.setEditable(false);
//        JLabel l21 = new JLabel(dao.loginUser(loginUser).getId()); //수정불가하므로
        JLabel l3 = new JLabel("현재 비밀번호");
        JTextField t3 = new JTextField(25); // 10은 글자수
        JLabel l4 = new JLabel("변경 비밀번호");
        JTextField t4 = new JTextField(25); // 10은 글자수
        JLabel l5 = new JLabel("이름");
        JTextField t5 = new JTextField(10); // 10은 글자수
        t5.setText(dao.loginUser(loginUser).getName());
        JLabel l6 = new JLabel("이메일");
        JTextField t6 = new JTextField(15); // 10은 글자수
        t6.setText(dao.loginUser(loginUser).getEmailID());
        JTextField t7 = new JTextField(15);


        //combobox2: 이메일 도메인
        ArrayList<String> g2 = new ArrayList<>();
        g2.add("@naver.com");
        g2.add("@gmail.com");
        g2.add("직접입력");
        JComboBox<String> combo2 = new JComboBox<>(g2.toArray(new String[0]));
        if (g2.contains(dao.loginUser(loginUser).getEmailSite())) {
            combo2.setSelectedItem(dao.loginUser(loginUser).getEmailSite());
        } else {
            combo2.setSelectedItem("직접입력");
            t7.setText(dao.loginUser(loginUser).getEmailSite());
        }


        JButton b1 = new JButton("수정");

        f.add(b0);
        f.add(l2);
        f.add(t8);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);
        f.add(l6);
        f.add(t6);
        f.add(t7);
        f.add(combo2);
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p04();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() { // 수정
            @Override
            public void actionPerformed(ActionEvent e) {
                String pw = t3.getText();
                String pwNew = t4.getText();
                String name = t5.getText();
                String emailID = t6.getText();
                String emailSite;

                String selectedValue = combo2.getSelectedItem().toString();
                if (selectedValue.equals("직접입력")) {
                    emailSite = t7.getText();
                } else {
                    t7.removeAll();
                    emailSite = selectedValue;
                }
                if (dao.loginUser(loginUser).getPw().equals(pw)) {

                    MemberDto memberDto = new MemberDto(t8.getText(), pwNew, name, emailID, emailSite);
                    MemberDao memberDao = new MemberDao();
                    int result = memberDao.updateUser(memberDto);

                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "수정되었습니다.");
                        p04();
                    } else {
                        JOptionPane.showMessageDialog(f, "업데이트 실패. 다시 시도해주세요.");
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "비밀번호가 올바르지 않습니다");
                }

            }
        }); //b1.addActionListener
        /////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p04_1() : 프로필 재설정

    public void p04_2() {
        //JFrame 정의
//        f = new JFrame();

        MemberDao dao = new MemberDao();

        Random r = new Random();
        StringBuilder rString = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digit = r.nextInt(16); // 0부터 15 사이의 난수 생성
            rString.append(Integer.toHexString(digit)); // 16진수로 변환하여 추가
        }


        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p04_2 : 회원탈퇴   ");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("아이디");
        JTextField t5 = new JTextField(30);
        t5.setText(dao.loginUser(loginUser).getId());
        t5.setEditable(false);
        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(25); // 10은 글자수
        JLabel l4 = new JLabel("자동 입력방지");
        JLabel l41 = new JLabel(String.format(rString.toString()));
        JButton b1 = new JButton("새로고침");
        JTextField t4 = new JTextField(13); // 10은 글자수

        //checkbox 예제
        JLabel l5 = new JLabel("주의");
        JLabel l6 = new JLabel("      탈퇴시 보유하고 있던 모든 혜택이 사라집니다.      ");
        JLabel l7 = new JLabel( "이후 같은 아이디로 재가입이 불가합니다.");
        JCheckBox cb1 = new JCheckBox("주의사항을 확인했으며 이에 동의합니다.");

        JButton b2 = new JButton("탈퇴");

        f.add(b0);
        f.add(l2);
        f.add(t5);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(l41);
        f.add(b1);
        f.add(t4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(cb1);
        f.add(b2);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p04();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                StringBuilder rString = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    int digit = r.nextInt(16); // 0부터 15 사이의 난수 생성
                    rString.append(Integer.toHexString(digit)); // 16진수로 변환하여 추가
                }

                l41.setText(rString.toString());
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cb1.isSelected()) {//checkbox 처리확인후
                    String pw = t3.getText();
                    String answerR = t4.getText();
                    String rString = l41.getText();

                    boolean pwMatch = dao.loginUser(loginUser).getPw().equals(pw);
                    boolean rResult = answerR.equals(rString);

                    if (pwMatch && rResult) {

                        int result = dao.deleteMember(loginUser);
                        if (result == 1) {

                            JOptionPane.showMessageDialog(f, "탈퇴 처리 되었습니다. 메인화면으로 돌아갑니다.");
                            p01();
                        } else {
                            JOptionPane.showMessageDialog(f, "처리 실패. 다시 시도해주세요.");
                        }
                    } else if (pwMatch == false) {
                        JOptionPane.showMessageDialog(f, "비밀번호 오류. 다시 입력해주세요");
                    } else {
                        JOptionPane.showMessageDialog(f, "자동입력방지 값이 일치하지 않습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "주의사항을 다시 확인해주세요.");
                }
            }
        }); //b2.addActionListener
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p04_2() : 회원탈퇴
}
