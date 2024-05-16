package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.PresetDao;
import com.multi.miniproject.member.model.dao.ProductDao;
import com.multi.miniproject.member.model.dto.CarDto;
import com.multi.miniproject.member.model.dto.PresetDto;
import com.multi.miniproject.member.model.dto.ProductDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProductPage extends UI {
    public void p06() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
        String[] g1 = {"차종", "차량가격", "주문가능여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        // JTable
        selectRowNoTmp = selectRowNo;
        p.removeAll();
        ProductDao dao = new ProductDao();
        ArrayList<ProductDto> list = dao.selectListAll();
        String[] header = {"차량고유번호", "차종", "상품가격", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getProductNum(),
                    dao.getCarDto(list.get(i)).getCarName(),
                    list.get(i).getProductPrice(),
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

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");

        JButton b4 = new JButton("[상세]");

        f.add(b0);
        f.add(b1);
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

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = t1.getText();
                String criteria = combo1.getSelectedItem().toString();
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
                                list.get(i).getProductPrice(),
                                "상세"
                        });
                    }
                } //if~else

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
                //상품 상세(R) 구현
                String tmp = selectRowNo;
                ProductDao productDao = new ProductDao();
                ProductDto rsDto = productDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else {
                    JOptionPane.showMessageDialog(f, rsDto);
                    p07_2();
                }
            }
        }); //b3.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06() : 상품조회

    public void p06_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
//                JOptionPane.showMessageDialog(f, "필터가 등록되었습니다.");

                PresetDao presetDao = new PresetDao(); // 쪽지 전달자!!
                PresetDto presetDto = new PresetDto(); // 쪽지
                //설문결과를 담아내는 작업 (빈쪽지를 채우는=set하는 작업)
                //presetDto.setPresetNum();         //안해도 된다! 어차피 오라클이 대신해주니까
                presetDto.setMemberNum(loginUser);
                presetDto.setPresetComfort(r11.isSelected() ? 1 : 0);
                presetDto.setPresetWeight(r21.isSelected() ? 1 : 0);
                presetDto.setPresetPassenger(r31.isSelected() ? 1 : 0);

                // DAO를 거친 후 result값 리턴받기
                int result = presetDao.insert(presetDto); //쪽지를 insert (DAO가 insert한다 DTO(쪽지)를 어디에? DB에), result에 성공여부만 알려드림.
                if (result == 1) JOptionPane.showMessageDialog(f, "필터가 등록되었습니다.");
                else JOptionPane.showMessageDialog(f, "필터가 등록되지 않았습니다.");

                p06();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_1() : 필터등록페이지

    public void p06_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
        JLabel l2 = new JLabel("수정/삭제 버튼을 클릭하세요 .");
        //radio
//        ButtonGroup g1 = new ButtonGroup();
//        JRadioButton r1 = new JRadioButton("1번 필터");
//        JRadioButton r2 = new JRadioButton("2번 필터");
//        JRadioButton r3 = new JRadioButton("3번 필터");
        // JTable
        p.removeAll();
        PresetDao dao = new PresetDao();
        ArrayList<PresetDto> list = dao.selectAllList();
        String[] header = {"필터번호", "승차감 좋음 / 보통", "적재량 많음 / 적음", "3인이상 승객 O / X"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getPresetNum(),
                    list.get(i).getPresetComfort(),
                    list.get(i).getPresetWeight(),
                    list.get(i).getPresetPassenger(),
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


        JButton b1 = new JButton("수정버튼 1=예/ 0=아니오");
        JButton b2 = new JButton("삭제: p06()로 이동");

        f.add(b0);
        f.add(l2);
        f.add(p, BorderLayout.CENTER);
//        g1.add(r1);
//        g1.add(r2);
//        g1.add(r3);
//        f.add(r1);
//        f.add(r2);
//        f.add(r3);

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
                // 수정버튼 기능
                String presetNum = JOptionPane.showInputDialog("수정하실 필터를 선택해 주세요 예)PR1");

                PresetDto presetDto = new PresetDto();
                PresetDao presetDao = new PresetDao();

                //
                presetDto = presetDao.selectOne(presetNum);
                presetDto.setPresetComfort(Integer.parseInt(JOptionPane.showInputDialog(null, "승차감 편함  / 보통 ", presetDto.getPresetComfort())));
                presetDto.setPresetWeight(Integer.parseInt(JOptionPane.showInputDialog(null, "적재량 많음 / 적음 ", presetDto.getPresetWeight())));
                presetDto.setPresetPassenger(Integer.parseInt(JOptionPane.showInputDialog(null, "승객3인 이상 O/X", presetDto.getPresetPassenger())));


                int result = presetDao.update(presetDto);

                if (result == 1) {
                    JOptionPane.showMessageDialog(f, "필터가 수정되었습니다.");
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<PresetDto> list = presetDao.selectAllList();
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getPresetNum(),
                                list.get(i).getPresetComfort(),
                                list.get(i).getPresetWeight(),
                                list.get(i).getPresetPassenger()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "필터 수정에 실패하였습니다.");
                }


                // p06_1();
            }
        }); //b1.addActionListener


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //삭제버튼 기능
                String presetNum = JOptionPane.showInputDialog("삭제하실 필터를 선택해 주세요. 예)PR1");

                PresetDao presetDao = new PresetDao();

                int result = presetDao.delete(presetNum);
                if (result == 1) {
                    JOptionPane.showMessageDialog(f, "해당 필터가 삭제되었습니다.");
                    model.setRowCount(0); // 기존 테이블 모델의 행 제거
                    ArrayList<PresetDto> list = presetDao.selectAllList();
                    for (int i=0; i<list.size(); i++) {
                        model.addRow(new Object[]{
                                list.get(i).getPresetNum(),
                                list.get(i).getPresetComfort(),
                                list.get(i).getPresetWeight(),
                                list.get(i).getPresetPassenger()
                        });
                    }
                }
                else JOptionPane.showMessageDialog(f, "필터삭제에 실패했습니다.");

//                p06();        //삭제후에 굳이 이동을 할 필요가 있을까 싶습니다!
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_2() : 필터수정삭제페이지

    public void p07() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
//        ButtonGroup g1 = new ButtonGroup();
//        JRadioButton r1 = new JRadioButton("1번 필터");
//        JRadioButton r2 = new JRadioButton("2번 필터");
//        JRadioButton r3 = new JRadioButton("3번 필터");
        // 라디오 버튼을 비활성화
        JLabel l3 = new JLabel("선택 버튼 클릭 후 적용할 필터를 입력해주세요");
//        JLabel l4 = new JLabel("/ 승차감 l4.setText()");
//        JLabel l5 = new JLabel("/ 적재량 l5.setText()");
//        JLabel l6 = new JLabel("/ 탑승인원 l6.setText()");

        JButton b1 = new JButton("선택: p07_1()로 이동");
        // JTable
        p.removeAll();
        PresetDao dao = new PresetDao();
        ArrayList<PresetDto> list = dao.selectAllList();
        String[] header = {"필터번호", "승차감 좋음 / 보통", "적재량 많음 / 적음", "3인이상 승객 O / X"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getPresetNum(),
                    list.get(i).getPresetComfort(),
                    list.get(i).getPresetWeight(),
                    list.get(i).getPresetPassenger(),
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

        f.add(b0);
        f.add(l2);
//        g1.add(r1);
//        g1.add(r2);
//        g1.add(r3);
//        f.add(r1);
//        f.add(r2);
//        f.add(r3);
        f.add(l3);
//        f.add(l4);
//        f.add(l5);
//        f.add(l6);

        f.add(b1);
        f.add(p, BorderLayout.CENTER);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String presetNum = JOptionPane.showInputDialog("적용하실 필터를 입력해 주세요 예)PR1");
                String presetNum = selectRowNo;
                PresetDao presetDao = new PresetDao();
                PresetDto rsDto = presetDao.selectOne(presetNum);


                if (rsDto == null) JOptionPane.showMessageDialog(f, presetNum + " 필터 조건을 불러오는데 실패하였습니다");
                else {
                    JOptionPane.showMessageDialog(f, "선택하신 필터의 내용입니다." + rsDto);
                    p07_1();
                }


            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07() : 필터적용

    public void p07_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
        String[] g1 = {"차종", "차량가격", "주문가능여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        // JTable
        p.removeAll();
        ProductDao dao = new ProductDao();
        ArrayList<ProductDto> list = dao.selectListByPreset(selectRowNo);
        selectRowNoTmp = selectRowNo;
        String[] header = {"차량고유번호", "차종", "상품가격", "상세"};
        model = new DefaultTableModel(data, header);
        table = new JTable(model);
        model.setRowCount(0); // 기존 테이블 모델의 행 제거
        for (int i=0; i<list.size(); i++) {
            model.addRow(new Object[]{
                    list.get(i).getProductNum(),
                    dao.getCarDto(list.get(i)).getCarName(),
                    list.get(i).getProductPrice(),
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

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");

        JButton b4 = new JButton("[상세]");

        f.add(b0);
        f.add(b1);
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

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = t1.getText();
                String criteria = combo1.getSelectedItem().toString();
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
                                list.get(i).getProductPrice(),
                                "상세"
                        });
                    }
                } //if~else

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
                //상품 상세(R) 구현
                String tmp = selectRowNo;
                ProductDao productDao = new ProductDao();
                ProductDto rsDto = productDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else {
                    prev = 1;
                    JOptionPane.showMessageDialog(f, rsDto);
                    p07_2();
                }
            }
        }); //b3.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07_1() : 필터활성화 후 상품조회

    public void p07_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
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
        //p07_1()의 selectRowNo와 연계부분
        ProductDao productDao = new ProductDao();
        ProductDto productDto = productDao.selectOne(selectRowNo);
        CarDto carDto = productDao.getCarDto(productDto);
//        UI().productAmount= productDto.getProductPrice();

        System.out.println(carDto.getCarImageAddress());
        ImageIcon ic = new ImageIcon("img/" + carDto.getCarImageAddress()); // 예시 이미지
        JLabel img = new JLabel(ic);


        JLabel l2 = new JLabel("차종 " + carDto.getCarName());
        JLabel l3 = new JLabel("차종분류 " + carDto.getCarCategory());
        JLabel l4 = new JLabel("차종특징 " + carDto.getCarFeature());
        JLabel l5 = new JLabel("차량상태 " + productDto.getProductAvailable());
        JLabel l6 = new JLabel("상품금액 " + productDto.getProductPrice());

        JButton b1 = new JButton("결제 후 이용: p08() 이동");

        f.add(b0);
        f.add(img);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(prev == 1) {
                    selectRowNo = selectRowNoTmp;
                    p07_1();
                }
                else{
                    p06();
                }

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
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.CYAN);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//
//        //페이지제목
//        JLabel l1 = new JLabel("p07_2 : 상품 정보 제공");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//
//
//        ImageIcon ic = new ImageIcon("img/img001.png"); // 예시 이미지
//        JLabel img = new JLabel(ic);
//        f.add(img);
//
//
//        String[] g1 = {"차량고유번호", "차량이름", "차량유형"};
//        JComboBox combo1 = new JComboBox(g1);
//        JTextField t1 = new JTextField(20); // 10은 글자수
//        JButton b11 = new JButton("상세정보 조회");
//
//        p.removeAll();
//        CarDao dao = new CarDao();
//        ArrayList<CarDto> list = dao.selectListAll();
//        String[] header = {"차량고유번호", "차량이름", "차량유형"};
//        model = new DefaultTableModel(data, header);
//        table = new JTable(model);
//        model.setRowCount(0); // 기존 테이블 모델의 행 제거
//        for (int i = 0; i < list.size(); i++) {
//            model.addRow(new Object[]{
//                    list.get(i).getCarNum(),
//                    list.get(i).getCarName(),
//                    list.get(i).getCarCategory(),
//                    "상세"
//            });
//        }
//        final JScrollPane[] scroll = new JScrollPane[1];
//        scroll[0] = new JScrollPane(table);
//        scroll[0].setPreferredSize(new Dimension(320, 320));
//        f.add(p, BorderLayout.CENTER);
//        p.add(scroll[0]);
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int row = table.getSelectedRow(); //행
//                selectRowNo = (String) table.getValueAt(row, 0); //열
//            }
//        });
//
//
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        JButton b1 = new JButton("결제 후 이용: p08() 이동");
//
//
//        f.add(b0);
//        f.add(b1);
//        f.add(b11);
//        f.add(t1);
//        f.add(combo1);
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                p07_1();
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p08();
//            }
//        }); //b1.addActionListener
//
//        b11.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String keyword = t1.getText();
//                String criteria = combo1.getSelectedItem().toString();
//                CarDao carDao = new CarDao();
//                ArrayList<CarDto> li = carDao.selectList(criteria, keyword);
//                JOptionPane.showMessageDialog(f, li.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]" + li));
//            }
//        });
//
//        /////////////////////////////////////////////////////////
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
    } // p07_2() : 상품정보제공
}
