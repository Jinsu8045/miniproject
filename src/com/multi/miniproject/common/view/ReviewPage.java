package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.MemberDao;
import com.multi.miniproject.member.model.dao.ReviewDao;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.ReviewDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ReviewPage extends UI{

    String selectRowNo = "null";
    JPanel p = new JPanel();
    JTable table = null;
    DefaultTableModel model = null; //



    public void p05() {
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

        /////////////////////////////////////////////////////////
        p.removeAll();
        ReviewDao dao = new ReviewDao();
        ArrayList<ReviewDto> latestlist = dao.latestlist(); // ArrayList<MemberDTO>

        String[] header = {"no", "Title", "car", "writer"};
        Object[][] data = new Object[latestlist.size()][4];

        if (latestlist.size() == 0) {
            System.out.println("검색결과 없음. ");
        } else {
//            System.out.println("검색결과는 전체 " + list.size() + "개 입니다.");
            for (int i = 0; i < latestlist.size(); i++) {
                data[i][0] = latestlist.get(i).getReviewNum();
                data[i][1] = latestlist.get(i).getTitle();
                data[i][2] = latestlist.get(i).getCar_num();
                data[i][3] = latestlist.get(i).getWriter();
            }
        }

        model = new DefaultTableModel(data, header);
       table = new JTable(model);

        final JScrollPane[] scroll = new JScrollPane[1];
        scroll[0] = new JScrollPane(table);

        scroll[0].setPreferredSize(new Dimension(320, 320));
        f.add(p, BorderLayout.CENTER);
        p.add(scroll[0]);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow(); // 클릭된 행의 인덱스
                selectRowNo = (String) table.getValueAt(row, 0); // 클릭된 행의 no 열의 값

//                System.out.println("선택된 행의 no 값: " + selectRowNo);
            }
        });


        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);

        f.revalidate();


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI ui = new UI();
                ui.p03();
                f.setVisible(false);
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectRowNo.equals("null")){
                    JOptionPane.showMessageDialog(f,"선택된 값이 없습니다.");
                }else{
                    p05_2();
//                    System.out.println(selectRowNo);
                }

            }
        }); //b1.addActionListener



        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReviewDao dao = new ReviewDao();

                String criteria = combo1.getSelectedItem().toString();
                String keyword = t1.getText();

                ArrayList<ReviewDto> selectList = dao.selectList(criteria,keyword);

                model.setRowCount(0); // 기존 테이블 모델의 행 제거

                for (ReviewDto review : selectList) {
                    model.addRow(new Object[]{
                            review.getReviewNum(),
                            review.getTitle(),
                            review.getCar_num(),
                            review.getWriter()
                    });
                }

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

    public void p05_1() {
        //JFrame 정의
//        f = new JFrame();
        ReviewDao dao = new ReviewDao();
        MemberDao mDao = new MemberDao();

        f.getContentPane().removeAll();
        f.repaint();
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

        JComboBox combo1 = new JComboBox();
        ArrayList<ReviewDto> yetreviewlist = dao.userReviewlist(mDao.loginUser(loginUser).getId());
        if(yetreviewlist.isEmpty()) {
            combo1.addItem("주문이력이 없음");

        }else{
            for (ReviewDto review : yetreviewlist) {
                combo1.addItem(review.getCar_num());
                }
        }

        String[] g2 = {"5", "4", "3", "2", "1"};
        JComboBox combo2 = new JComboBox(g2);
        JTextField t3 = new JTextField(50); // 10은 글자수
        t3.setText("내용을 입력해주세요.");

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


                int rating = Integer.parseInt(combo2.getSelectedItem().toString());
                String selectOrder = combo1.getSelectedItem().toString();
                String title = t2.getText();
                String contents = t3.getText();

                if(contents.equals("내용을 입력해주세요.")||contents.equals("")){
                    JOptionPane.showMessageDialog(f, "내용이 작성되지 않았습니다.");
                }else if(title.equals("")) {
                    JOptionPane.showMessageDialog(f, "제목을 입력해주세요.");
                }else if(selectOrder.equals("주문이력이 없음")) {
                    JOptionPane.showMessageDialog(f, "작성 할 수 있는 리뷰가 없습니다.");
                }else {
                    String orderNum = dao.userReviewlist(mDao.loginUser(loginUser).getId(),selectOrder).getOrderNum();
                    //같은 차종이 여러개일 경우 먼저 order된 순서로 작성
                    int result = dao.reviewInsert(orderNum, rating, title, contents);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "리뷰가 등록되었습니다.");
                        p05();
                    } else {
                        JOptionPane.showMessageDialog(f, "리뷰 등록 실패. 재시도 해주세요.");
                    }
                }
            }
        }); //b1.addActionListener

        ////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p05_1() : 리뷰작성

    public void p05_2() {
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
        JLabel l1 = new JLabel("p05_2 : 리뷰 상세페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        ReviewDao dao = new ReviewDao();

        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("제목");
        JTextField t2 = new JTextField(30); // 10은 글자수
        t2.setText(dao.reviewDetail(selectRowNo).getTitle());
//        String[] g1 = {"주문이력이 없음", "기타"};
//        JComboBox combo1 = new JComboBox(g1);
        JTextField t4 = new JTextField(5); // 10은 글자수
        t4.setText(dao.reviewDetail(selectRowNo).getCar_num());
        t4.setEditable(false);//수정 못하게 막기
        String[] g2 = {"5", "4", "3", "2", "1"};
        JComboBox combo2 = new JComboBox(g2);
        combo2.setSelectedItem(String.valueOf(dao.reviewDetail(selectRowNo).getRating()));
        JTextField t3 = new JTextField(50);// 10은 글자수
        t3.setText(dao.reviewDetail(selectRowNo).getContents());
        JButton b1 = new JButton("수정");
        JButton b2 = new JButton("삭제");

        f.add(b0);
        f.add(l2);
        f.add(t2);
        f.add(t4);
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
                MemberDao mDao = new MemberDao();

//                String orderNum = dao.reviewDetail(selectRowNo).getOrderNum();
                int rating = Integer.parseInt(combo2.getSelectedItem().toString());
                String title = t2.getText();
                String contents = t3.getText();

                if (dao.reviewDetail(selectRowNo).getWriter().equals(mDao.loginUser(loginUser).getId())) {
                    int result = dao.reviewUpdate(selectRowNo, rating , title, contents);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "수정되었습니다.");
                        p05();
                    } else {
                        JOptionPane.showMessageDialog(f, "리뷰 수정 실패. 재시도 해주세요.");
                    }

                }else{
                    JOptionPane.showMessageDialog(f, "수정 권한이 없습니다.");
                }
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDao mDao = new MemberDao();
                if (dao.reviewDetail(selectRowNo).getWriter().equals(mDao.loginUser(loginUser).getId())) {
                    String anwser = JOptionPane.showInputDialog("정말 삭제하시겠습니다? 삭제된 리뷰는 복구가 불가합니다.(1/0)");
                    if(anwser.equals("1")){
                    int result = dao.reviewDelete(selectRowNo);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "삭제되었습니다.");
                        p05();
                    } else {
                        JOptionPane.showMessageDialog(f, "리뷰 삭제 실패. 재시도 해주세요.");
                    }}else{
                        JOptionPane.showMessageDialog(f, "삭제요청이 취소되었습니다.");
                    }
                }else{
                    JOptionPane.showMessageDialog(f, "삭제 권한이 없습니다.");
                }
            }
        }); //b2.addActionListener
        ////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p05_2() : 리뷰상세페이지

}



