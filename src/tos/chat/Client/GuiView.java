package tos.chat.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiView {
  private final ClientGuiController controller;

  private volatile boolean clientConnected = false;


  private JFrame frame = new JFrame("Чат");
  private JTextField textField = new JTextField(50);
  private JTextArea messages = new JTextArea(10, 50);
  private JTextArea users = new JTextArea(10, 10);

  public GuiView(ClientGuiController controller) {
    this.controller = controller;
    initView();
  }

  private void initView() {
    textField.setEditable(true);
    messages.setEditable(false);
    users.setEditable(false);

    frame.getContentPane().add(textField, BorderLayout.NORTH);
    frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST);
    frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    textField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.sendTextMessage(textField.getText());
        textField.setText("");
      }
    });
  }

  public String getServerAddress() {
    return JOptionPane.showInputDialog(
        frame,
        "Введите адрес сервера:",
        "Конфигурация клиента",
        JOptionPane.QUESTION_MESSAGE);
  }

  public int getServerPort() {
    return 2020;
  }

  public String getUserName() {
    return JOptionPane.showInputDialog(
        frame,
        "Введите ваше имя:",
        "Конфигурация клиента",
        JOptionPane.QUESTION_MESSAGE);
  }

  public void notifyConnectionStatusChanged(boolean clientConnected) {
    textField.setEditable(clientConnected);
    if (clientConnected) {
      JOptionPane.showMessageDialog(
          frame,
          "Соединение с сервером установлено",
          "Чат",
          JOptionPane.INFORMATION_MESSAGE);
    }
    else {
      JOptionPane.showMessageDialog(
          frame,
          "Клиент не подключен к серверу",
          "Чат",
          JOptionPane.ERROR_MESSAGE);
    }


  }


  public void refreshMessages() {
    messages.append(controller.getModel().getNewMessage() + "\n");
  }

  public void refreshUsers() {
    ClientGuiModel model = controller.getModel();
    StringBuilder sb = new StringBuilder();
    for (String userName : model.getAllUserNames()) {
      sb.append(userName).append("\n");
    }
    users.setText(sb.toString());
  }
}
