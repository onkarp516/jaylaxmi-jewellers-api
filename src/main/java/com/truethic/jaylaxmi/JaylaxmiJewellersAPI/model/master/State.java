package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state_tbl")
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long countryId;
    @Column(length = 2, columnDefinition = "char")
    private String countryCode;
    private String stateCode;

}