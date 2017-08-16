package com.warframe.mytmall.pojo;


/**
 * Created by warframe on 2017/6/2.
 */
public class User {
    /**
     * spring中的context其实就是给spring提供一个运行时的环境
     */
    //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");


    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //获取匿名昵称
    public String getAnonymousName() {
        if (name.length() <= 1) return "*";
        if (name.length() == 2) return name.substring(0, 1) + "*";

        char[] cs = name.toCharArray();
        for (int i = 1; i < name.length() - 1; i++) {
            cs[i] = '*';
        }
        return new String(cs);


    }
}
