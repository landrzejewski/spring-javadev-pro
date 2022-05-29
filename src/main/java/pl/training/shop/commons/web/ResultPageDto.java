package pl.training.shop.commons.web;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class ResultPageDto<T> {

    private List<T> data;
    private int pageNumber;
    private long totalPages;

}
