package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.MemberDao;
import com.multi.miniproject.member.model.dto.MemberDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    private String loginUser = null;

    public void p01(){
//JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p01 : 메인 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");   // 첫페이지
        JButton b1 = new JButton("로그인: p02()으로 이동");
        JButton b2 = new JButton("회원가입: p01_1()으로 이동");

//        f.add(b0);
        f.add(b1);
        f.add(b2);

//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p01();
//            }
//        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p02();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01_1();
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void p01_1(){            //회원가입
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("회원가입 페이지");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p01_1 : 회원가입 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(19); //
        JButton b1 = new JButton("중복확인");

        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(30); // 10은 글자수
        JLabel l4 = new JLabel("비밀번호 확인");
        JTextField t4 = new JTextField(30); // 10은 글자수
        JLabel l5 = new JLabel("이름");
        JTextField t5 = new JTextField(10); // 10은 글자수
        JLabel l6 = new JLabel("이메일");
        JTextField t6 = new JTextField(15); // 10은 글자수
        JTextField t7 = new JTextField(15); // 10은 글자수 //'직접입력'일시 잠금해제 하는 기능 구현 필요.

        //combobox2: 이메일 도메인
        String[] g2 = {"@naver.com", "@gmail.com", "직접입력"};
        JComboBox combo2 = new JComboBox(g2);
        JButton b2 = new JButton("등록: p02()로 이동");


        f.add(b0);
        f.add(l2);
        f.add(t2);

        f.add(b1);
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

        f.add(b2);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() { //중복확인
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t2.getText();

                MemberDao memberDao = new MemberDao();
                if(memberDao.idCheck(id)){
                    JOptionPane.showMessageDialog(f, "이미 사용중인 아이디입니다.");
                }else{
                    JOptionPane.showMessageDialog(f, "사용 가능한 아이디입니다.");

                }
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() { //회원가입 버튼
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = t2.getText();
                String pw = t3.getText();
                String pw2 = t4.getText();
                String name = t5.getText();
                String emailID = t6.getText();
                String emailSite;


                String selectedValue = combo2.getSelectedItem().toString();
                if(selectedValue.equals("직접입력")){
                    emailSite = t7.getText();
                }else{
                    emailSite = selectedValue;
                }

                MemberDao memberDao = new MemberDao();
                boolean startsWithAt = emailSite.startsWith("@");

                if(pw.equals(pw2) && memberDao.idCheck(id)==false && startsWithAt==true){
                    MemberDto memberDto = new MemberDto(id, pw, name, emailID, emailSite);
                    int result = memberDao.joinMember(memberDto);
                    if(result == 1){
                        JOptionPane.showMessageDialog(f, "회원가입 완료. 로그인 해주세요.");
                        p02();
                    }else{
                        JOptionPane.showMessageDialog(f, "회원가입 실패. 재시도 해주세요.");
                    }

                }else if (!pw.equals(pw2)){
                    JOptionPane.showMessageDialog(f,"비밀번호 값이 일치하지 않습니다.");
                }else if (startsWithAt==false){
                    JOptionPane.showMessageDialog(f,"올바르지 않은 이메일 형식입니다(@필수)");
                }else{
                    JOptionPane.showMessageDialog(f,"중복된 아이디입니다.");
                }

            }
        }); //b2.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void p02(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p02 : 로그인 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(19); //
        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(30); // 10은 글자수

        JButton b1 = new JButton("로그인: p03()으로 이동");
        JButton b2 = new JButton("비밀번호 찾기");
        JButton b3 = new JButton("회원가입: p01_1()으로 이동");

        f.add(b0);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {     //로그인
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t2.getText();
                String pw = t3.getText();

                MemberDto memberDto = new MemberDto();
                memberDto.setId(id);
                memberDto.setPw(pw);

                MemberDao memberDao = new MemberDao();
                boolean result = memberDao.login(id,pw);

                if(result){
                    JOptionPane.showMessageDialog(f,"로그인 성공");
                    loginUser = memberDao.selectOne(id).getMemberNum();
                    p03();
                }else if(id.equals("")||pw.equals("")){
                    JOptionPane.showMessageDialog(f,"입력되지 않은 값이 있습니다");
                }else if(memberDao.idCheck(id)==false){
                    JOptionPane.showMessageDialog(f,"존재하지 않는 아이디 입니다.");
                }else{
                    JOptionPane.showMessageDialog(f,"아이디와 비밀번호가 일치하지 않습니다.");
                }


            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("아이디를 입력하세요");
                MemberDao memberDao = new MemberDao();
                if(memberDao.idCheck(id)){
                    String name = JOptionPane.showInputDialog("이름을 입력하세요");
                    boolean result = memberDao.findPw(id, name);
                    if(result==true){
                        JOptionPane.showMessageDialog(f,"ID : "+ id + " PW : " + (memberDao.selectOne(id)).getPw());
                    }else{
                        JOptionPane.showMessageDialog(f,"일치하는 회원정보가 없습니다.");
                    }
                }else{
                    JOptionPane.showMessageDialog(f,"존재하지 않는 아이디 입니다.");
                }

            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01_1();
            }
        }); //b3.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p02() 로그인페이지

    public void p03(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03 : 메뉴");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("마이 필터");
        JButton b2 = new JButton("상품 조회");
        JButton b3 = new JButton("리뷰");
        JButton b4 = new JButton("마이 페이지");

        b1.setBackground(Color.CYAN);
        b1.setOpaque(true);
        b2.setBackground(Color.CYAN);
        b2.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p02();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p04();
            }
        }); //b4.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p03() : 메뉴

    public void p04(){

        MemberDao dao = new MemberDao();

        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p04 : 마이페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("이름 " + dao.loginUser(loginUser).getName());
        JLabel l3 = new JLabel("아이디(이메일)" + dao.loginUser(loginUser).getId()+
                                                    "("+dao.loginUser(loginUser).getEmail()+")");
        //이미지
        JLabel img1 = new JLabel("이미지");
//        img1.setIcon(new ImageIcon("images/img.jpg"));
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
    public void p04_1(){

        MemberDao dao = new MemberDao();

        //JFrame 정의
        JFrame f = new JFrame();
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
        JLabel l21 = new JLabel(dao.loginUser(loginUser).getId()); //수정불가하므로
        JLabel l3 = new JLabel("현재 비밀번호");
        JTextField t3 = new JTextField(30); // 10은 글자수
        JLabel l4 = new JLabel("변경 비밀번호");
        JTextField t4 = new JTextField(30); // 10은 글자수
        JLabel l5 = new JLabel("이름");
        JTextField t5 = new JTextField(10); // 10은 글자수
        t5.setText(dao.loginUser(loginUser).getName());
        JLabel l6 = new JLabel("이메일");
        JTextField t6 = new JTextField(30); // 10은 글자수
        t6.setText(dao.loginUser(loginUser).getEmail());
        //combobox2: 이메일 도메인
        String[] g2 = {"@naver.com", "@gmail.com", "직접입력"};
        JComboBox combo2 = new JComboBox(g2);

        JButton b1 = new JButton("수정");

        f.add(b0);
        f.add(l2);
        f.add(l21);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);
        f.add(l6);
        f.add(t6);
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
                MemberDto memberDto = new MemberDto();
                MemberDao memberDao = new MemberDao();
//                int result = memberDao.updateUser();

                JOptionPane.showMessageDialog(f, "수정되었습니다.");
                p04();
            }
        }); //b1.addActionListener
        /////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p04_1() : 프로필 재설정
    public void p04_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p04_2 : 회원탈퇴");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("아이디");
        JLabel l21 = new JLabel("l21.setText()"); //수정불가하므로
        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(30); // 10은 글자수
        JLabel l4 = new JLabel("자동 입력방지");
        JLabel l41 = new JLabel("l41.setText()");
        JButton b1 = new JButton("새로고침");
        JTextField t4 = new JTextField(10); // 10은 글자수

        //checkbox 예제
        JLabel l5 = new JLabel("주의");
        JLabel l6 = new JLabel("탈퇴시 보유하고 있던 모든 혜택이 사라집니다. 이후 같은 아이디로 재가입이 불가합니다.");
        JCheckBox cb1 = new JCheckBox("주의사항을 확인했으며 이에 동의합니다.");

        JButton b2 = new JButton("탈퇴");

        f.add(b0);
        f.add(l2);
        f.add(l21);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(l41);
        f.add(b1);
        f.add(t4);
        f.add(l5);
        f.add(l6);
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
                //미구현 : 재미삼아 해보셔도...(난수 활용해서요)
                //l41.setText()
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb1.isSelected()){//checkbox 처리확인후
                    JOptionPane.showMessageDialog(f, "탈퇴처리되었습니다. 메인화면으로 돌아갑니다.");
                    p01();
                }
                else{
                    JOptionPane.showMessageDialog(f, "주의사항을 다시 확인해주세요.");
                }
            }
        }); //b2.addActionListener
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p04_2() : 회원탈퇴

    public void p05(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p05 : 리뷰");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("리뷰 상세: p05_2() 이동");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"아이디", "제목", "차종"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");
        JButton b2 = new JButton("리뷰 작성: p05_1() 이동");

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05_2();
            }
        }); //b1.addActionListener
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05_1();
            }
        }); //b2.addActionListener
        ////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p05() : 리뷰
    public void p05_1(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p05_1 : 리뷰 작성");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("제목");
        JTextField t2 = new JTextField(30); // 10은 글자수
        //combobox1: 내가 작성할 리뷰 선택
        String[] g1 = {"주문이력이 없음", "기타"};
        JComboBox combo1 = new JComboBox(g1);
        String[] g2 = {"5", "4", "3", "2", "1"};
        JComboBox combo2 = new JComboBox(g2);
        JTextField t3 = new JTextField(50); // 10은 글자수
        t3.setText("내용을 입력해주세요.내용을 입력해주세요.");

        JButton b1 = new JButton("리뷰 등록");

        f.add(b0);
        f.add(l2);
        f.add(t2);
        f.add(combo1);
        f.add(combo2);
        f.add(t3);
        f.add(b1);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "리뷰가 등록되었습니다.");
                p05();
            }
        }); //b1.addActionListener

        ////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p05_1() : 리뷰작성
    public void p05_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p05_2 : 리뷰 상세페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("제목");
        JTextField t2 = new JTextField(30); // 10은 글자수
        //combobox1: 내가 작성할 리뷰 선택
        String[] g1 = {"주문이력이 없음", "기타"};
        JComboBox combo1 = new JComboBox(g1);
        String[] g2 = {"5", "4", "3", "2", "1"};
        JComboBox combo2 = new JComboBox(g2);
        JTextField t3 = new JTextField(50); // 10은 글자수
        JButton b1 = new JButton("수정");
        JButton b2 = new JButton("삭제");

        f.add(b0);
        f.add(l2);
        f.add(t2);
        f.add(combo1);
        f.add(combo2);
        f.add(t3);
        f.add(b1);
        f.add(b2);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(true){
                    JOptionPane.showMessageDialog(f, "수정되었습니다.");
                    p05();
                }
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(true){
                    JOptionPane.showMessageDialog(f, "삭제되었습니다.");
                    p05();
                }
            }
        }); //b2.addActionListener
        ////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p05_2() : 리뷰상세페이지

    public void p06(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06 : 상품조회");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("필터 적용: p07()로 이동");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차종", "차종분류", "취향"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");

        JButton b4 = new JButton("차량X 상세: p07_2()로 이동");

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);

        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_2();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        }); //b3.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06() : 상품조회
    public void p06_1(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06_1 : 필터 등록 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("1. 승차감에 민감하신가요?");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r11 = new JRadioButton("맞습니다.");
        JRadioButton r12 = new JRadioButton("아닙니다.");

        JLabel l3 = new JLabel("2. 가진 짐이 많으신가요?");
        //radio
        ButtonGroup g2 = new ButtonGroup();
        JRadioButton r21 = new JRadioButton("맞습니다.");
        JRadioButton r22 = new JRadioButton("아닙니다.");

        JLabel l4 = new JLabel("3. 탑승인원이 3명을 넘어가나요?");
        //radio
        ButtonGroup g3 = new ButtonGroup();
        JRadioButton r31 = new JRadioButton("맞습니다.");
        JRadioButton r32 = new JRadioButton("아닙니다.");
        JButton b1 = new JButton("제출: p06()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r11);
        g1.add(r12);
        f.add(r11);
        f.add(r12);

        f.add(l3);
        g2.add(r21);
        g2.add(r22);
        f.add(r21);
        f.add(r22);

        f.add(l4);
        g3.add(r31);
        g3.add(r32);
        f.add(r31);
        f.add(r32);
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f,"필터가 등록되었습니다.");
                p06();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_1() : 필터등록페이지
    public void p06_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06_2 : 필터 수정 & 삭제 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
/////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("관리하실 필터를 선택해 주세요.");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("1번 필터");
        JRadioButton r2 = new JRadioButton("2번 필터");
        JRadioButton r3 = new JRadioButton("3번 필터");

        JButton b1 = new JButton("수정: p06_1()로 이동");
        JButton b2 = new JButton("삭제: p06()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r1);
        g1.add(r2);
        g1.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);

        f.add(b1);
        f.add(b2);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "선택하신 필터가 삭제되었습니다.");
                p06();
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_2() : 필터수정삭제페이지

    public void p07(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07 : 필터 적용");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("사용할 필터를 선택해 주세요");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("1번 필터");
        JRadioButton r2 = new JRadioButton("2번 필터");
        JRadioButton r3 = new JRadioButton("3번 필터");

        JLabel l3 = new JLabel("선택하신 필터의 조건입니다.");
        JLabel l4 = new JLabel("/ 승차감 l4.setText()");
        JLabel l5 = new JLabel("/ 적재량 l5.setText()");
        JLabel l6 = new JLabel("/ 탑승인원 l6.setText()");

        JButton b1 = new JButton("선택: p07_1()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r1);
        g1.add(r2);
        g1.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(b1);



        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_1();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07() : 필터적용
    public void p07_1(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07_1 : 필터 활성화시 상품조회");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("필터 적용해제: p06()로 이동");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차종", "차종분류", "취향"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");
        JButton b4 = new JButton("GV80 상세: p07_2()로 이동");

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b4);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_2();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        }); //b4.addActionListener


        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07_1() : 필터활성화 후 상품조회
    public void p07_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07_2 : 상품 정보 제공");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //이미지
        JLabel img1 = new JLabel("이미지");
//        img1.setIcon(new ImageIcon("images/img.jpg"));
        //
        JLabel l2 = new JLabel("차종 l2.setText()");
        JLabel l3 = new JLabel("/ 차종분류 l3.setText()");
        JLabel l4 = new JLabel("/ 차종특징 l4.setText()");
        JLabel l5 = new JLabel("/ 차량상태 l5.setText()");
        JLabel l6 = new JLabel("/ 상품금액 l6.setText()");

        JButton b1 = new JButton("결제 후 이용: p08() 이동");

        f.add(b0);
        f.add(img1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_1();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p08();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07_2() : 상품정보제공

    public void p08(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p08 : 결제페이지-카드/무통장입금");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        f.add(b0);
        JLabel l2 = new JLabel("카드 번호");
        JTextField t2 = new JTextField(19); // 1234-1234-1234-1234
        JLabel l3 = new JLabel("예금자명");
        JTextField t3 = new JTextField(10); // 10은 글자수
        JLabel l4 = new JLabel("CVC번호: 뒷면 서명란 끝 3자리 숫자");
        JTextField t4 = new JTextField(3); // 10은 글자수
        JLabel l5 = new JLabel("유효기간: 월/년 각 2자리");
        JTextField t5 = new JTextField(4); // 10은 글자수

        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("카드 결제");
        //combobox2: 카드 결제
        String[] g2 = {"국민", "비씨", "신한"};
        JComboBox combo2 = new JComboBox(g2);
        //combobox3: 할부개월수 선택
        String[] g3 = {"일시불", "2개월", "3개월", "4개월", "6개월", "12개월"};
        JComboBox combo3 = new JComboBox(g3);
        JRadioButton r2 = new JRadioButton("무통장 입금");
        //카드정보 입력

        //combobox4: 은행선택
        String[] g4 = {"국민", "비씨", "신한"};
        JComboBox combo4 = new JComboBox(g4);

        g1.add(r1);
        g1.add(r2);
        f.add(r1);
        f.add(combo2);
        f.add(combo3);
        f.add(r2);
        f.add(combo4);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);


        JButton b1 = new JButton("결제하기: p09() 이동");
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p09();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p08() : 결제

    public void p08A_1(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p08A_1 : 결제페이지-카드");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // <미사용>p08A_1() : 결제-카드/무통장입금 선택
    public void p08A_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p08A_2 : 결제페이지-카드선택");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // <미사용>p08A_2() : 결제-카드선택시
    public void p08A_3(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p08A_3 : 결제페이지 - 카드등록 및 카드 정보 입력");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // <미사용>p08A_3() : 결제-카드등록및

    public void p08B_2(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p08B_2 : 결제페이지-은행선택");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // <미사용>p08B_2() : 결제-은행선택시

    public void p09(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p09 : 결제페이지-결제완료");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");   //결제완료를 취소못하게끔
        JLabel l2 = new JLabel("결제가 완료되어, 주문이 접수되었습니다.");
        JLabel l3 = new JLabel("주문번호:");
        JLabel l4 = new JLabel("l4.setText()");
        JLabel l5 = new JLabel("차량고유번호:");
        JLabel l6 = new JLabel("l6.setText()");
        JLabel l7 = new JLabel("차종:");
        JLabel l8 = new JLabel("l8.setText()");
        JLabel l9 = new JLabel("결제금액:");
        JLabel l10 = new JLabel("l10.setText()");
        JLabel l11 = new JLabel("결제수단:");
        JLabel l12 = new JLabel("l12.setText()");

        JButton b1 = new JButton("확인: p03()이동");
        JButton b2 = new JButton("주문 취소: p10() 이동");

//        f.add(b0);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(l8);
        f.add(l9);
        f.add(l10);
        f.add(l11);
        f.add(l12);
        f.add(b1);
        f.add(b2);

//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p02();
//            }
//        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p10();
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p09() : 결제-결제완료
    public void p10(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p10 : 결제페이지-결제취소");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("결제 취소하시겠습니까?");
        JButton b1 = new JButton("확인: p11() 이동");

        f.add(b0);
        f.add(l2);
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p09();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p11();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p10() : 결제-결제취소
    public void p11(){
        //JFrame 정의
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p11 : 환불요청 접수");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");   //되돌리기 불가
        JLabel l2 = new JLabel("환불요청이 접수되었습니다.");
        JButton b1 = new JButton("확인: p03() 이동");

//        f.add(b0);
        f.add(l2);
        f.add(b1);

//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p09();
//            }
//        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
