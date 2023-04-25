package project.laundry.data.response;

import lombok.Getter;
import lombok.Setter;
import project.laundry.data.response.common.dateDto;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class incomeDtoList {
    private List<dateDto> daily_income = new ArrayList<>();
    private List<dateDto> monthly_income = new ArrayList<>();

    // 당월 수입, 방문자 수
    private Integer current_month_income;
    private Integer current_month_visitor;

    // 당년 수입, 방문자 수
    private Integer current_year_income;
    private Integer current_year_visitor;

}
