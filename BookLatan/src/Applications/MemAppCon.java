/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Applications;

import Componenents.Members.FineViewCon;
import Components.Login.LoginController;
import Model.User;
import Model.UserMemberDAO;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 *
 * @author Dinel
 */
public class MemAppCon {
    private MemberApplication view;
    private User user;
    private UserMemberDAO userDAO;
    
    private FineViewCon fineviewCon;

    public MemAppCon(MemberApplication view, User user) {
        this.view = view;
        this.user = user;
        this.userDAO = new UserMemberDAO();
        this.view.sidebar.addUserInfo(this.user.getUsername(), userDAO.getMemberIDByUSerID(user.getUserId()));
        
        this.fineviewCon = new FineViewCon(view.fines, userDAO.getMemberByID(userDAO.getMemberIDByUSerID(user.getUserId())));
        attachListeners();
        
    }

    public void openApp() {
        view.setVisible(true);
    }
    
    private void attachListeners() {
        Map<String, JButton> btns = view.sidebar.getMenuButtons();
        btns.get("Dashboard").addActionListener(e -> view.mainPanelLayout.show(view.mainPanel, "dashboard"));
        btns.get("Books").addActionListener(e -> view.mainPanelLayout.show(view.mainPanel, "books"));
        btns.get("Loans").addActionListener(e -> view.mainPanelLayout.show(view.mainPanel, "loans"));
        btns.get("Reservations").addActionListener(e -> view.mainPanelLayout.show(view.mainPanel, "reservations"));
        btns.get("Fines").addActionListener(e -> view.mainPanelLayout.show(view.mainPanel, "fines"));   
        btns.get("Logout").addActionListener(e -> {
           int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
           if(answer == JOptionPane.YES_OPTION) {
               new LoginController();
               view.dispose();
           }
        });
    }
}