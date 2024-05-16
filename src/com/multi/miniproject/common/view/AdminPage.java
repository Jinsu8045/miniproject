package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.MemberDao;
import com.multi.miniproject.member.model.dao.OrderDao;
import com.multi.miniproject.member.model.dao.ProductDao;
import com.multi.miniproject.member.model.dao.ReviewDao;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.OrderDto;
import com.multi.miniproject.member.model.dto.ProductDto;
import com.multi.miniproject.member.model.dto.ReviewDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminPage extends UI {
    // JTable 정의부 (전역으로 필요)
    String selectRowNo = "null";
    JPanel p = new JPanel();
    JTable table = null;
    DefaultTableModel model = null;
    Object[][] data = null;

    public void p03B() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l1 = new JLabel("p03B : 관리자메뉴");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(b0);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b1 = new JButton("[회원 관리]: p03B_1()으로 이동");
        JButton b2 = new JButton("[상품 관리]: p03B_2()으로 이동");
        JButton b3 = new JButton("[주문내역 관리]: p03B_3()으로 이동");
        JButton b4 = new JButton("[리뷰 관리]: p03B_4()으로 이동");

//        f.add(b0);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.GREEN);
        b1.setOpaque(true);
        b2.setBackground(Color.GREEN);
        b2.setOpaque(true);;
        b3.setBackground(Color.GREEN);
        b3.setOpaque(true);
        b4.setBackground(Color.GREEN);
        b4.setOpaque(true);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "관리자페이지 로그아웃");
                p02();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B() : 관리자메뉴

    public void p03B_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l1 = new JLabel("p03B_1 : 회원관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(b0);
        f.add(l1);

        /////////////////////////////////////////////////////////

        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"회원번호", "아이디", "이름", "이메일", "관리자여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(12); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택 해제: 미구현");
        JButton b3 = new JButton("[탈퇴]");
        // JTable
        p.removeAll();
        MemberDao dao = new MemberDao();
        ArrayList<MemberDto> list = dao.selectListAll();
        String[] header = {"ID", "성명", "이메일", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getId(),
                    list.get(i).getName(),
                    list.get(i).getEmailID()+list.get(i).getEmailSite(),
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
        JButton b91 = new JButton("[회원 관리]");
        JButton b92 = new JButton("[상품 관리]");
        JButton b93 = new JButton("[주문내역 관리]");
        JButton b94 = new JButton("[리뷰 관리]");

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
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

//        f.add(b0);
//        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(p, BorderLayout.CENTER);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                MemberDao memberDao = new MemberDao();
                ArrayList<MemberDto> list = memberDao.selectList(criteria, keyword);
                model.setRowCount(0); // 기존 테이블 모델의 행 제거
                if(list.isEmpty()) JOptionPane.showMessageDialog(f, "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]");
                else {
                    //검색결과 테이블에
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getId(),
                                list.get(i).getName(),
                                list.get(i).getEmailID()+list.get(i).getEmailSite(),
                                "상세"
                        });
                    }
                } //if~else
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductPage().p06_1();
                f.setVisible(false);
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원 탈퇴(D) 구현
                String memberNum = JOptionPane.showInputDialog("[1] 탈퇴처리할 회원번호를 입력해주세요. 예)M1");

                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                MemberDao memberDao = new MemberDao();

                // DAO를 거친 후 result값 리턴받기
                int result = memberDao.deleteMember(memberNum);
                if (result == 1) JOptionPane.showMessageDialog(f, "해당 회원이 탈퇴처리되었습니다.");
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 회원이 탈퇴처리되지 못했습니다.");
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원 상세(R) 구현
                String tmp = selectRowNo;
                MemberDao memberDao = new MemberDao();
                MemberDto rsDto = memberDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_1() : 회원관리

    public void p03B_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l1 = new JLabel("p03B_2 : 상품관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(b0);
        f.add(l1);

        /////////////////////////////////////////////////////////

        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차량고유번호", "차종번호", "차량상태", "상품가격", "주문가능여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(12); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[등록]");
        JButton b32 = new JButton("[삭제]");
        // JTable
        p.removeAll();
        ProductDao dao = new ProductDao();
        ArrayList<ProductDto> list = dao.selectListAll();
        String[] header = {"차량고유번호", "차종", "이용상태", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getProductNum(),
                    dao.getCarDto(list.get(i)).getCarName(),
                    list.get(i).getProductStatus(),
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

        JButton b4 = new JButton("[수정]");
        //
        JButton b91 = new JButton("[회원 관리]");
        JButton b92 = new JButton("[상품 관리]");
        JButton b93 = new JButton("[주문내역 관리]");
        JButton b94 = new JButton("[리뷰 관리]");

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
        b32.setBackground(Color.YELLOW);
        b32.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

//        f.add(b0);
//        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b32);
        f.add(p, BorderLayout.CENTER);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        //JTable
        f.revalidate();

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
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
                ProductDao productDao = new ProductDao();
                ArrayList<ProductDto> list = productDao.selectList(criteria, keyword);
                model.setRowCount(0); // 기존 테이블 모델의 행 제거
                if(list.isEmpty()) JOptionPane.showMessageDialog(f, "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]");
                else {
                    //검색결과 테이블에
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getProductNum(),
                                dao.getCarDto(list.get(i)).getCarName(),
                                list.get(i).getProductStatus(),
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
                // 상품 등록(C)
                String carNum = JOptionPane.showInputDialog("[1/4] 이 상품에 등록될 차종번호를 입력해주세요. 예)C1");
                String productStatus = JOptionPane.showInputDialog("[2/4] 이 상품(차량)의 현재 상태를 입력해주세요. 예)양호");
                String productPrice = JOptionPane.showInputDialog("[3/4] 이 상품의 (금액/일)의 값을 입력해주세요. 예)50000 쉼표를 붙이지 마세요.");
                String productAvailable = "1";

                // DAO, DTO 선언 및 셋
                ProductDao productDao = new ProductDao();
                ProductDto productDto = new ProductDto();
                productDto.setCarNum(carNum);
                productDto.setProductStatus(productStatus);
                productDto.setProductPrice(Integer.parseInt(productPrice));
                productDto.setProductAvailable(Integer.parseInt(productAvailable));

                // DAO를 거친 후 result값 리턴받기
                int result = productDao.insert(productDto);
                if (result == 1) {
                    JOptionPane.showMessageDialog(f, "상품이 등록되었습니다: " + productDto);
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<ProductDto> list = productDao.selectListAll();
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getProductNum(),
                                productDao.getCarDto(list.get(i)).getCarName(),
                                list.get(i).getProductStatus(),
                                "상세"
                        });
                    }
                }
                else JOptionPane.showMessageDialog(f, "상품이 등록되지 않았습니다.");
            }
        }); //b3.addActionListener
        b32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상품 삭제(D) //선택한것을 받아 다시 구현해야함
                String productNum = JOptionPane.showInputDialog("[1] 삭제하기를 원하는 상품번호(차량고유번호)를 입력해주세요. 예)P1");

                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                ProductDao productDao = new ProductDao();

                // DAO를 거친 후 result값 리턴받기
                int result = productDao.delete(productNum);
                if (result == 1) {
                    JOptionPane.showMessageDialog(f, "해당 상품이 삭제되었습니다.");
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<ProductDto> list = productDao.selectListAll();
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getProductNum(),
                                productDao.getCarDto(list.get(i)).getCarName(),
                                list.get(i).getProductStatus(),
                                "상세"
                        });
                    }
                }
                else JOptionPane.showMessageDialog(f, "해당 상품이 삭제되지 않았습니다.");
            }
        }); //b32.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상품 상세 수정(U) //행 파라미터 함수로 다시 구현해야함
                String tmp = selectRowNo;
                ProductDao productDao = new ProductDao();
                ProductDto rsDto = productDao.selectOne(tmp);
                //수정을 위해 다이얼로그를 띄움
                rsDto.setCarNum(JOptionPane.showInputDialog(null, "carNum: ", rsDto.getCarNum()));
                rsDto.setProductStatus(JOptionPane.showInputDialog(null, "productStatus: ", rsDto.getProductStatus()));
                rsDto.setProductPrice(Integer.parseInt(JOptionPane.showInputDialog(null, "productPrice: ", rsDto.getProductPrice())));
                rsDto.setProductAvailable(Integer.parseInt(JOptionPane.showInputDialog(null, "productAvailable: ", rsDto.getProductAvailable())));
                //rsDto를 다시 DAO를 통해 DB로보냄.
                int result = productDao.update(rsDto);
                if(result == 1) {
                    JOptionPane.showMessageDialog(f, "해당 상품에 대한 정보가 성공적으로 수정되었습니다."+rsDto);
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<ProductDto> list = productDao.selectListAll();
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getProductNum(),
                                productDao.getCarDto(list.get(i)).getCarName(),
                                list.get(i).getProductStatus(),
                                "상세"
                        });
                    }
                }
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 상품에 대한 정보 수정에 실패하였습니다.");
            }
        }); //b4.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_2() : 상품관리

    public void p03B_3() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l1 = new JLabel("p03B_3 : 주문관리");
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
        ArrayList<OrderDto> list = dao.selectListAll();
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
        JButton b91 = new JButton("[회원 관리]");
        JButton b92 = new JButton("[상품 관리]");
        JButton b93 = new JButton("[주문내역 관리]");
        JButton b94 = new JButton("[리뷰 관리]");

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
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

//        f.add(b0);
//        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(p, BorderLayout.CENTER);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
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
                if(result == 1) JOptionPane.showMessageDialog(f, "해당 주문이 환불처리 되어 목록에서 삭제되었습니다.");
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
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_3() : 주문내역관리

    public void p03B_4() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l1 = new JLabel("p03B_4 : 리뷰관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(b0);
        f.add(l1);

        /////////////////////////////////////////////////////////
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"리뷰번호", "주문번호", "차종평가별점", "후기제목", "후기내용"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(12); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[삭제]");
        // JTable
        p.removeAll();
        ReviewDao dao = new ReviewDao();
        ArrayList<ReviewDto> list = dao.selectListAll();
        String[] header = {"리뷰번호", "주문번호", "차종평가별점", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getReviewNum(),
                    list.get(i).getOrderNum(),
                    list.get(i).getRating(),
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
        JButton b91 = new JButton("[회원 관리]");
        JButton b92 = new JButton("[상품 관리]");
        JButton b93 = new JButton("[주문내역 관리]");
        JButton b94 = new JButton("[리뷰 관리]");

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
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

//        f.add(b0);
//        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(p, BorderLayout.CENTER);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
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
                ReviewDao reviewDao = new ReviewDao();
                ArrayList<ReviewDto> list = reviewDao.selectList(criteria, keyword);
                model.setRowCount(0); // 기존 테이블 모델의 행 제거
                if(list.isEmpty()) JOptionPane.showMessageDialog(f, "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]");
                else {
                    //검색결과 테이블에
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getReviewNum(),
                                list.get(i).getOrderNum(),
                                list.get(i).getRating(),
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
                // 리뷰 삭제 구현(D)
                String reviewNum = JOptionPane.showInputDialog("[1] 삭제할 리뷰번호를 입력해주세요. 예)R1");
                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                ReviewDao reviewDao = new ReviewDao();

                // DAO를 거친 후 result값 리턴받기
                int result = reviewDao.delete(reviewNum);
                if(result == 1) JOptionPane.showMessageDialog(f, "해당 리뷰가 목록에서 삭제되었습니다.");
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 리뷰가 목록에서 삭제되지 않았습니다.");

            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리뷰 상세(R) 구현
                String tmp = selectRowNo;
                ReviewDao reviewDao = new ReviewDao();
                ReviewDto rsDto = reviewDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_4() : 리뷰관리
}
//