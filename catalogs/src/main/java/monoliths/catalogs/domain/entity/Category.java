package monoliths.catalogs.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "parent" })
public class Category implements Serializable {

    private UUID id;

    private String code;
    private String text;

    private Category parent;
    private List<Category> children = new ArrayList<>();


    private Category() { }

    public Category child(String code, String text) {
        val child = create(code, text); {
            child.setParent(this);
        }

        children.add(child);
        return child;
    }


    public static Category create(String code, String text) {
        val category = new Category(); {
            category.setId(UUID.randomUUID());
            category.setCode(Objects.requireNonNull(code));
            category.setText(Objects.requireNonNull(text));
        }
        return category;
    }

}
