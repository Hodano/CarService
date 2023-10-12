package pl.hodan.carservice.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private Client client;

    public Test() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1,"Mariusz","Hodana","dasdas",1,"dasdas"));
    }
}
