package pl.training.shop.commons.web;

import lombok.Data;

import java.util.List;

@Data
public class ResultPageViewModel<T> {

    private List<T> data;
    private int pageNumber;
    private long totalPages;

}
