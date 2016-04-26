/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.hzins.utils.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.hzins.utils.Md5Utils;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2016年3月11日 上午9:19:24
 * @version
 */
@SuppressWarnings("serial")
public class UI extends JFrame {

    private JTextField keyText;

    private JTextArea contentText;

    private JTextField signOutText;

  

    private JButton signBtn;

    public UI() {
	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screensize.getWidth();
	int height = (int) screensize.getHeight();

	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("md5签名工具");
	setSize(600, 400);

	setLocation((width - 600) / 2, (height - 400) / 2);

	setLayout(null);
	setResizable(false);
	keyText = new JTextField("渠道密钥");
	contentText = new JTextArea("请求内容");
	signOutText = new JTextField();

	signBtn = new JButton("签名");

	keyText.setLocation(0, 0);
	keyText.setSize(600, 50);

	contentText.setLocation(0, 60);
	contentText.setSize(600, 200);

	signOutText.setLocation(0, 270);
	signOutText.setSize(600, 50);

	

	signBtn.setLocation(270, 330);
	signBtn.setSize(60, 40);

	TextUIFocusListner textUIFocusListner = new TextUIFocusListner();
	keyText.addFocusListener(textUIFocusListner);
	contentText.addFocusListener(textUIFocusListner);
	signBtn.addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {
		sign();
	    }

	});

	add(keyText);
	add(contentText);
	add(signOutText);
	add(signBtn);

    }

    class TextUIFocusListner implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {

	    Object source = e.getSource();
	    if (source instanceof JTextComponent) {
		String text = ((JTextComponent) source).getText();
		if ("渠道密钥".equals(text) || "请求内容".equals(text)) {
		    ((JTextComponent) source).setText(null);
		}
	    }

	}

	public boolean isBlank(String text) {
	    return text == null || text.trim().equals("");
	}

	@Override
	public void focusLost(FocusEvent e) {
	    /*
	     * 
	     * Object source = e.getSource(); if (source instanceof JTextField)
	     * { String text = ((JTextComponent) source).getText(); if
	     * (isBlank(text)) { ((JTextField) source).setText("渠道密钥"); }
	     * 
	     * } if (source instanceof JTextArea) { String text =
	     * ((JTextComponent) source).getText(); if (isBlank(text)) {
	     * ((JTextComponent) source).setText("请求内容"); } }
	     * 
	     */}

    }

    private void sign() {
	String key = keyText.getText();
	String content = contentText.getText();
	if (key == null) {
	    key = "";

	}
	if (content == null) {
	    content = "";
	}
	String sign = Md5Utils.getUtf8MD5String(key + content);
	signOutText.setText(sign);
    }

}
