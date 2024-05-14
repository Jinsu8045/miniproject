package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.ReviewDao;
import com.multi.miniproject.member.model.dto.ReviewDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ReviewPage {

    String selectRowNo = null;
    JFrame f = new JFrame();
    JPanel p = new JPanel();

    public void p05() {
        //JFrame 정의
//        f = new JFrame();
        ReviewDao dao = new ReviewDao();


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
        ArrayList<ReviewDto> list = dao.latestlist(); // ArrayList<MemberDTO>

        String[] header = {"no", "Title", "car", "writer"};
        Object[][] data = new Object[list.size()][4];

        if (list.size() == 0) {
            System.out.println("검색결과 없음. ");
        } else {
//            System.out.println("검색결과는 전체 " + list.size() + "개 입니다.");
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i).getReviewNum();
                data[i][1] = list.get(i).getTitle();
                data[i][2] = list.get(i).getCar_num();
                data[i][3] = list.get(i).getWriter();
            }
        }

//        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(data, header);

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

                System.out.println("선택된 행의 no 값: " + selectRowNo);
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
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p05_2();
                System.out.println(selectRowNo);
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

    public void p05_1() {
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
                if (true) {
                    JOptionPane.showMessageDialog(f, "수정되었습니다.");
                    p05();
                }
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (true) {
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

}



