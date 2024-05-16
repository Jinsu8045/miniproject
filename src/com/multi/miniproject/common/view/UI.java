package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.*;
import com.multi.miniproject.member.model.dto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class UI {
    JFrame f = new JFrame();
    String loginUser =null;

    String selectRowNo = "null";
    String selectRowNoTmp = "null";
    JPanel p = new JPanel();
    JTable table = null;
    DefaultTableModel model = null;
    Object[][] data = null;
    int prev = 0 ; // p07_2() 뒤로가기시 사용
    OrderDto orderDto = new OrderDto();


    public void p01() {
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

    public void p01_1() {            //회원가입
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("회원가입 페이지");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p01_1 : 회원가입  ");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(20); //
        JButton b1 = new JButton("중복확인");

        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(25); // 10은 글자수
        JLabel l4 = new JLabel("비밀번호 확인");
        JTextField t4 = new JTextField(25); // 10은 글자수
        JLabel l5 = new JLabel("이름");
        JTextField t5 = new JTextField(15); // 10은 글자수
        JLabel l6 = new JLabel("이메일");
        JTextField t6 = new JTextField(10); // 10은 글자수
        JTextField t7 = new JTextField(10); // 10은 글자수

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
                if (memberDao.idCheck(id)) {
                    JOptionPane.showMessageDialog(f, "이미 사용중인 아이디입니다.");
                } else {
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
                if (selectedValue.equals("직접입력")) {
                    emailSite = t7.getText();
                } else {
                    t7.setText("");
                    emailSite = selectedValue;
                }

                MemberDao memberDao = new MemberDao();
                boolean startsWithAt = emailSite.startsWith("@");
                if (id.equals("") || pw.equals("") || name.equals("") || emailID.equals("")) {
                    JOptionPane.showMessageDialog(f, "입력되지 않은 값이 존재합니다.");
                } else if (pw.equals(pw2) && memberDao.idCheck(id) == false && startsWithAt == true) {
                    MemberDto memberDto = new MemberDto(id, pw, name, emailID, emailSite);
                    int result = memberDao.joinMember(memberDto);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "회원가입 완료. 로그인 해주세요.");
                        p02();
                    } else {
                        JOptionPane.showMessageDialog(f, "회원가입 실패. 재시도 해주세요.");
                    }

                } else if (!pw.equals(pw2)) {
                    JOptionPane.showMessageDialog(f, "비밀번호 값이 일치하지 않습니다.");
                } else if (startsWithAt == false) {
                    JOptionPane.showMessageDialog(f, "올바르지 않은 이메일 형식입니다(@필수)");
                } else {
                    JOptionPane.showMessageDialog(f, "중복된 아이디입니다.");
                }

            }
        }); //b2.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p02() {
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
        JLabel l1 = new JLabel("p02 : 로그인 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(10); //
        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(10); // 10은 글자수

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
                boolean result = memberDao.login(id, pw);

                if (result) {
                    JOptionPane.showMessageDialog(f, "로그인 성공");
                    loginUser = memberDao.selectOne(id).getMemberNum();
                    if (memberDao.loginUser(loginUser).getAdmin() == 1) {
                        new AdminPage().p03B();
                        f.setVisible(false);
                    } else {
                        p03();
                    }
                } else if (id.equals("") || pw.equals("")) {
                    JOptionPane.showMessageDialog(f, "입력되지 않은 값이 있습니다");
                } else if (memberDao.idCheck(id) == false) {
                    JOptionPane.showMessageDialog(f, "존재하지 않는 아이디 입니다.");
                } else {
                    JOptionPane.showMessageDialog(f, "아이디와 비밀번호가 일치하지 않습니다.");
                }


            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("아이디를 입력하세요");
                MemberDao memberDao = new MemberDao();
                if (memberDao.idCheck(id)) {
                    String name = JOptionPane.showInputDialog("이름을 입력하세요");
                    boolean result = memberDao.findPw(id, name);
                    if (result == true) {
                        JOptionPane.showMessageDialog(f, "ID : " + id + " PW : " + (memberDao.selectOne(id)).getPw());
                    } else {
                        JOptionPane.showMessageDialog(f, "일치하는 회원정보가 없습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "존재하지 않는 아이디 입니다.");
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

    public void p03() {
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
                loginUser = null;
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductPage().p07();
                f.setVisible(false);
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductPage().p06();
                f.setVisible(false);
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewPage reviewPage = new ReviewPage();
                reviewPage.p05();
                f.setVisible(false);
//                    p05();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mypage mypage = new Mypage();
                mypage.p04();
                f.setVisible(false);
            }
        }); //b4.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p03() : 메뉴


    String productNum = "P1";
    String productAmount = "500000";


    public void p08() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
                new ProductPage().p07_2();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String card = combo2.getSelectedItem().toString();
                String install = combo3.getSelectedItem().toString();

//                System.out.println(card +" "+install);

                // DAO, DTO 선언 및 셋
                OrderDao orderDao = new OrderDao();
                orderDto.setMemberNum(loginUser);
                orderDto.setProductNum(productNum);
                orderDto.setOrderStatus(1);
                orderDto.setOrderRefundRequest(0);
                orderDto.setOrderRefundComplete(0);
//                orderDto.setPaymentNum("P123");
//                orderDto.setPaymentMethod(card);
                orderDto.setPaymentAmount(Integer.parseInt(productAmount));
//                orderDto.setPaymentInstallation(install);

                System.out.println(orderDto);

                // DAO를 거친 후 result값 리턴받기
//                orderDao.insertOrder(orderDto);
                int result = orderDao.insertOrder(orderDto);
                p09();
//                if (result == 1) {
//                    System.out.println("주문이 등록되었습니다.");
//
////                    JOptionPane.showMessageDialog(f, "주문이 등록되었습니다: " + orderDto);
//                }
//                else JOptionPane.showMessageDialog(f, "주문이 등록되지 않았습니다.");

            }
        });

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p08() : 결제

    public void p09() {

        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("결제 완료");
        f.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel l1 = new JLabel("결제 완료. 주문내역입니다.");
        l1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        f.add(l1, gbc);

        //
        OrderDao orderDao = new OrderDao();

        ProductDao productDao = new ProductDao();
        //
        gbc.gridwidth = 1;
        gbc.gridy++;
//        JLabel l9 = new JLabel("주문번호: "+orderDto.getOrderNum());
//        f.add(l9, gbc);
        gbc.gridy++;
        JLabel l11 = new JLabel("차종: "+ productDao.getCarDto(orderDao.getProductDto(orderDto)).getCarName());
        f.add(l11, gbc);
        gbc.gridy++;
        JLabel l12 = new JLabel("결제금액: "+ orderDto.getPaymentAmount());
        f.add(l12, gbc);

        gbc.gridy++;
        JButton b1 = new JButton("확인: p03() 이동");
        gbc.gridwidth = 2;
        f.add(b1, gbc);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        });

        gbc.gridy++;
        JButton b2 = new JButton("결제취소: p10() 이동");
        f.add(b2, gbc);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p10();
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p10() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.GREEN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("처음으로");
        JLabel l1 = new JLabel("p10 : 주문취소");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(b0);
        f.add(l1);
        /////////////////////////////////////////////////////////
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"주문번호", "회원번호", "차량고유번호", "주문상태", "환불요청여부", "환불처리여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(12); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[환불처리]");
        // JTable
        p.removeAll();
        OrderDao dao = new OrderDao();
        ArrayList<OrderDto> list = dao.selectList(1, orderDto.getMemberNum());
        String[] header = {"주문번호", "차량고유번호", "상태", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getOrderNum(),
                    list.get(i).getProductNum(),
                    list.get(i).getOrderStatus(),
                    "상세"
            });
        }
        final JScrollPane[] scroll = new JScrollPane[1];
        scroll[0] = new JScrollPane(table);
        scroll[0].setPreferredSize(new Dimension(320, 320));
        f.add(p, BorderLayout.CENTER);
        p.add(scroll[0]);
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e ) {
                int row = table.getSelectedRow(); //행
                selectRowNo = (String)table.getValueAt(row, 0); //열
            }
        });

        JButton b4 = new JButton("[상세]");
        //

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.RED);
        b1.setOpaque(true);
        b2.setBackground(Color.RED);
        b2.setOpaque(true);
        b11.setBackground(Color.YELLOW);
        b11.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);

//        f.add(b0);
//        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(p, BorderLayout.CENTER);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                OrderDao orderDao = new OrderDao();
                ArrayList<OrderDto> list = orderDao.selectList(criteria, keyword);
                model.setRowCount(0); // 기존 테이블 모델의 행 제거
                if(list.isEmpty()) JOptionPane.showMessageDialog(f, "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]");
                else {
                    //검색결과 테이블에
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getOrderNum(),
                                list.get(i).getProductNum(),
                                list.get(i).getOrderStatus(),
                                "상세"
                        });
                    }
                } //if~else
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 주문 환불처리 (UorD)
                String orderNum = JOptionPane.showInputDialog("[1] 환불요청을 수락하여 삭제할 주문번호를 입력해주세요. 예)O1");
                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                OrderDao orderDao = new OrderDao();

                // DAO를 거친 후 result값 리턴받기
                int result = orderDao.delete(orderNum);
                if(result == 1) {
                    JOptionPane.showMessageDialog(f, "해당 주문이 환불처리 되어 목록에서 삭제되었습니다.");
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<OrderDto> list = orderDao.selectList(1, orderDto.getMemberNum());
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getOrderNum(),
                                list.get(i).getProductNum(),
                                list.get(i).getOrderStatus(),
                                "상세"
                        });
                    }

                }

                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 주문이 목록에서 삭제되지 않았습니다.");
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //주문 상세(R) 구현
                String tmp = selectRowNo;
                OrderDao orderDao = new OrderDao();
                OrderDto rsDto = orderDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null)
                    JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else {
                    JOptionPane.showMessageDialog(f, rsDto);
                }
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p11() {

        if (true) {
            JOptionPane.showMessageDialog(f, "결제가 성공적으로 취소되었습니다.");
            p03();
        } else {
            JOptionPane.showMessageDialog(f, "결제 취소에 실패하였습니다.");
        }

    } //p11()
}


//    public void p08() {
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.GREEN);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p08 : 결제페이지-카드/무통장입금");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        f.add(b0);
//        JLabel l2 = new JLabel("카드 번호");
//        JTextField t2 = new JTextField(19); // 1234-1234-1234-1234
//        JLabel l3 = new JLabel("예금자명");
//        JTextField t3 = new JTextField(10); // 10은 글자수
//        JLabel l4 = new JLabel("CVC번호: 뒷면 서명란 끝 3자리 숫자");
//        JTextField t4 = new JTextField(3); // 10은 글자수
//        JLabel l5 = new JLabel("유효기간: 월/년 각 2자리");
//        JTextField t5 = new JTextField(4); // 10은 글자수
//
//        //radio
//        ButtonGroup g1 = new ButtonGroup();
//        JRadioButton r1 = new JRadioButton("카드 결제");
//        //combobox2: 카드 결제
//        String[] g2 = {"국민", "비씨", "신한"};
//        JComboBox combo2 = new JComboBox(g2);
//        //combobox3: 할부개월수 선택
//        String[] g3 = {"일시불", "2개월", "3개월", "4개월", "6개월", "12개월"};
//        JComboBox combo3 = new JComboBox(g3);
//        JRadioButton r2 = new JRadioButton("무통장 입금");
//        //카드정보 입력
//
//        //combobox4: 은행선택
//        String[] g4 = {"국민", "비씨", "신한"};
//        JComboBox combo4 = new JComboBox(g4);
//
//        g1.add(r1);
//        g1.add(r2);
//        f.add(r1);
//        f.add(combo2);
//        f.add(combo3);
//        f.add(r2);
//        f.add(combo4);
//        f.add(l2);
//        f.add(t2);
//        f.add(l3);
//        f.add(t3);
//        f.add(l4);
//        f.add(t4);
//        f.add(l5);
//        f.add(t5);
//
//
//        JButton b1 = new JButton("결제하기: p09() 이동");
//        f.add(b1);
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new ProductPage().p07_2();
//                f.setVisible(false);
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p09();
//            }
//        }); //b1.addActionListener
//
//        /////////////////////////////////////////////////////////
//
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } // p08() : 결제
//
//    public void p09() {
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.GREEN);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p09 : 결제페이지-결제완료");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//
//        /////////////////////////////////////////////////////////
////        JButton b0 = new JButton("<-뒤로가기");   //결제완료를 취소못하게끔
//        JLabel l2 = new JLabel("결제가 완료되어, 주문이 접수되었습니다.");
//        JLabel l3 = new JLabel("주문번호:");
//        JLabel l4 = new JLabel("l4.setText()");
//        JLabel l5 = new JLabel("차량고유번호:");
//        JLabel l6 = new JLabel("l6.setText()");
//        JLabel l7 = new JLabel("차종:");
//        JLabel l8 = new JLabel("l8.setText()");
//        JLabel l9 = new JLabel("결제금액:");
//        JLabel l10 = new JLabel("l10.setText()");
//        JLabel l11 = new JLabel("결제수단:");
//        JLabel l12 = new JLabel("l12.setText()");
//
//        JButton b1 = new JButton("확인: p03()이동");
//        JButton b2 = new JButton("주문 취소: p10() 이동");
//
////        f.add(b0);
//        f.add(l2);
//        f.add(l3);
//        f.add(l4);
//        f.add(l5);
//        f.add(l6);
//        f.add(l7);
//        f.add(l8);
//        f.add(l9);
//        f.add(l10);
//        f.add(l11);
//        f.add(l12);
//        f.add(b1);
//        f.add(b2);
//
////        b0.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                p02();
////            }
////        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p03();
//            }
//        }); //b1.addActionListener
//
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p10();
//            }
//        }); //b2.addActionListener
//
//        /////////////////////////////////////////////////////////
//
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } // p09() : 결제-결제완료

//    public void p10() {
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.GREEN);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p10 : 결제페이지-결제취소");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        JLabel l2 = new JLabel("결제 취소하시겠습니까?");
//        JButton b1 = new JButton("확인: p11() 이동");
//
//        f.add(b0);
//        f.add(l2);
//        f.add(b1);
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p09();
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p11();
//            }
//        }); //b1.addActionListener
//
//        /////////////////////////////////////////////////////////
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } // p10() : 결제-결제취소
//
//    public void p11() {
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.GREEN);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p11 : 환불요청 접수");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//        /////////////////////////////////////////////////////////
////        JButton b0 = new JButton("<-뒤로가기");   //되돌리기 불가
//        JLabel l2 = new JLabel("환불요청이 접수되었습니다.");
//        JButton b1 = new JButton("확인: p03() 이동");
//
////        f.add(b0);
//        f.add(l2);
//        f.add(b1);
//
////        b0.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                p09();
////            }
////        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p03();
//            }
//        }); //b1.addActionListener
//
//        /////////////////////////////////////////////////////////
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } //p11() : 환불요청 접수
//
//}
//