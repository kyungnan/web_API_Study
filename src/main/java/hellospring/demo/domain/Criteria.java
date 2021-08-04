package hellospring.demo.domain;

import lombok.*;

@AllArgsConstructor
@Data
public class Criteria {
    private int pageNum;
    private int amount;

    public Criteria(){
        this(1, 10);    // 1 page, 10개
    }

}
