package framework.spring.pojo;

import lombok.Data;

@Data
public class Bartender {

    private String name;

    public Bartender(String name) {
        this.name = name;
    }
}
