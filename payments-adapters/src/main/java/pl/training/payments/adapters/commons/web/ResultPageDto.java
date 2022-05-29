package pl.training.payments.adapters.commons.web;

import lombok.Data;

import java.util.List;

@Data
public class ResultPageDto<T> {

    List<T> data;
    int pageNumber;
    long totalPages;

}
