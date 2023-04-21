package project.laundry.data.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class incomeDto {
    private List<Integer> incomes = new ArrayList<>();
}
