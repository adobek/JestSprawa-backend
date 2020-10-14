package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.model.id.DepartmentCategoryId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DepartmentCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DepartmentCategoryId.class)
public class DepartmentCategory {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

}
