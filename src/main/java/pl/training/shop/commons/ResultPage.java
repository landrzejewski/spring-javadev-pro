package pl.training.shop.commons;

import lombok.Value;

import java.util.List;

@Value
public class ResultPage<T> {

    List<T> data;
    int pageNumber;
    long totalPages;

}
